import { Map, CustomOverlayMap, MapMarker } from "react-kakao-maps-sdk";
import { useState, useRef, useEffect } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import MapNav from "components/common/MapNav";
import Button from "components/common/Button";
import { checkSession } from "lib/utils/checkSession";
import { useDispatch, useSelector } from "react-redux";
import axios from "axios";
const { kakao } = window;

function SearchMap() {
  const isLogin = useSelector((state) => state.isLogin.value);
  const [isWished, setIsWished] = useState(false);
  const [markers, setMarkers] = useState([]);
  const [map, setMap] = useState();
  const [keyword, setKeyWord] = useState("");
  const [openMarker, setOpenMarker] = useState(null);
  const [pagination, setPagination] = useState({});
  const [center, setCenter] = useState({
    lat: 37.566769,
    lng: 126.978323,
  });
  const ref = useRef();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { state } = useLocation();
  const { tid } = useParams();

  const ps = new kakao.maps.services.Places();

  useEffect(() => {
    if (isLogin) {
      // 비로그인 유저가 유저 페이지 접속하자마자 로그인 모달이 뜨지 않도록 설정
      const nextAPICall = () => {
        axios
          .get(`/api/v1/themes/${tid}/users/me/follows`)
          .then((res) => {
            setIsWished(res.data.isWishing);
          })
          .catch((err) => console.error(err.message));
      };
      checkSession(dispatch, nextAPICall);
    } else {
      // 비로그인 유저 디폴트 설정
      setIsWished(false);
    }
  }, [isLogin, tid, dispatch]);

  const handleWishButtonClick = () => {
    const nextAPICall = () => {
      if (isWished) {
        axios
          .delete(`/api/v1/themes/${tid}/users/me/unwish`)
          .then(() => setIsWished(false))
          .catch((err) => console.error(err.message));
      } else {
        axios
          .post(`/api/v1/themes/${tid}/users/me/wishes`)
          .then(() => setIsWished(true))
          .catch((err) => console.error(err.message));
      }
    };
    checkSession(dispatch, nextAPICall);
  };

  const handleSearchSubmit = (e) => {
    e.preventDefault();
    if (!keyword.replace(/^\s+|\s+$/g, "")) {
      window.alert("키워드를 입력해주세요.");
      return false;
    }
    ps.keywordSearch(keyword, placesSearchCB);
  };

  const placesSearchCB = (data, status, pagination) => {
    if (status === kakao.maps.services.Status.OK) {
      // 정상적으로 검색이 완료됐으면 검색 목록과 마커를 나타냄
      displayPlaces(data);
      // 페이지 번호를 나타냄
      const { ...rest } = pagination;
      setPagination(rest);
      displayPagination(pagination);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
      alert("검색 결과가 존재하지 않습니다.");
      return;
    } else if (status === kakao.maps.services.Status.ERROR) {
      alert("검색 결과 중 오류가 발생했습니다.");
      return;
    }
  };

  const displayPlaces = (data) => {
    const bounds = new kakao.maps.LatLngBounds();
    let markers = [];
    for (let i = 0; i < data.length; i++) {
      markers.push({
        position: {
          lat: data[i].y,
          lng: data[i].x,
        },
        content: data[i].place_name,
        address: data[i].address_name,
        idx: i,
        itemid: data[i].id,
      });

      bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
    }
    setMarkers(markers);
    markers = [];
    // 검색된 장소 위치를 기준으로 지도 범위를 재설정함
    map.setBounds(bounds);
  };

  const displayPagination = (pagination) => {
    const { ...rest } = pagination;
    let pages = [];
    for (let i = 1; i <= pagination.last; i++) {
      pages.push(i);
    }
    setPagination({ ...rest, pages });
  };

  const handleRegistrationButton = (marker) => {
    if (window.confirm("이 장소에 대한 리뷰를 등록 하시겠습니까?")) {
      navigate("/store/review", {
        state: { ...marker, themeInfo: state.themeInfo },
      });
    }
    return;
  };

  return (
    <div className="relative w-full h-[100vh]">
      <MapNav
        emoji={state.themeInfo.themeEmoji}
        name={state.themeInfo.themeTitle}
        isWished={isWished}
        handleWishButtonClick={handleWishButtonClick}
      />
      <Map // 로드뷰를 표시할 Container
        center={center}
        className="absolute w-full h-full z-0"
        level={3}
        onCreate={setMap}
      >
        <menu
          ref={ref}
          className="absolute bottom-0 left-0 right-0 h-2/5 max-w-[300px] w-full bg-white rounded-t-lg mx-auto z-[99] px-4 overflow-y-auto no-scrollbar"
        >
          <div className="sticky top-0 left-0 right-0 w-full py-4 z-[100] bg-white">
            <form
              onSubmit={handleSearchSubmit}
              className="p-2 flex justify-between border border-borderGray rounded-lg focus-within:ring focus-within:ring-wagmiLightGreen"
            >
              <input
                className="pr-[13px] grow text-sm"
                placeholder="추천할 장소를 검색해보세요."
                onChange={(e) => setKeyWord(e.target.value)}
                value={keyword}
              />
              <Button
                styles="flex items-center p-2 m-[-8px]"
                icon="search"
                handleButtonClick={handleSearchSubmit}
              />
            </form>
          </div>
          <ul className="cursor-pointer">
            {markers.map((marker) => (
              <li
                key={marker.idx}
                onMouseOver={() => {
                  setOpenMarker(marker.idx);
                }}
                onMouseOut={() => setOpenMarker(null)}
                onClick={() => setCenter(marker.position)}
              >
                <span className={`markerbg marker_${marker.idx + 1}`}></span>
                <div className="py-[13px] pl-[55px] text-ellipsis overflow-hidden whitespace-nowrap m-0">
                  <h5 className="font-semibold text-base leading-6 m-0 p-0">
                    {marker.content}
                  </h5>
                  <span className="mt-[7px] text-xs font-normal leading-none text-gray-600 block p-0">
                    {marker.address}
                  </span>
                  <div
                    className="px-[18px] py-1 mt-4 border border-borderGray text-sm font-light text-center item-center text-textDarkGray rounded-md"
                    onClick={() => handleRegistrationButton(marker)}
                  >
                    등록하기
                  </div>
                </div>
              </li>
            ))}
          </ul>
          <div className="sticky bottom-0 bg-white py-2 mx-auto flex justify-center items-center">
            {pagination.pages &&
              pagination.pages.map((page, idx) =>
                page === pagination.current ? (
                  <div
                    key={idx}
                    className="border-box border-2 border-wagmiGreen rounded-full font-black px-2 mx-3 cursor-pointer text-wagmiGreen text-center"
                  >
                    {page}
                  </div>
                ) : (
                  <div
                    key={idx}
                    className="mx-3 px-2 cursor-pointer text-wagmiGreen"
                    onClick={() => {
                      pagination.gotoPage(page);
                      ref.current.scrollTo(0, 0);
                    }}
                  >
                    {page}
                  </div>
                )
              )}
          </div>
        </menu>
        {markers.map((marker) => (
          <div key={marker.idx}>
            <MapMarker
              key={`marker-${marker.content}-${marker.position.lat},${marker.position.lng}`}
              image={{
                src: "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png",
                size: { width: 36, height: 37 },
                options: {
                  spriteSize: { width: 36, height: 691 },
                  spriteOrigin: { x: 0, y: marker.idx * 46 + 10 },
                  offset: { x: 13, y: 37 },
                },
              }}
              position={marker.position}
              clickable={true} // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하infoWindowOptions
              // 마커에 마우스오버 이벤트를 등록함
              onMouseOver={
                // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시함
                () => setOpenMarker(marker.content)
              }
              // 마커에 마우스아웃 이벤트를 등록함
              onMouseOut={
                // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거함
                () => setOpenMarker(null)
              }
              infoWindow={false}
            ></MapMarker>

            {openMarker === marker.idx && (
              <CustomOverlayMap
                position={marker.position}
                yAnchor={2.1}
                xAnchor={0.5}
              >
                <div className="relative bg-white rounded-3xl py-3 px-8 text-center shadow-xl shadow-black-10 font-semibold leading-6 text-wagmiGreen">
                  {marker.content}
                </div>
                <div className="absolute border border-t-[10px] border-t-white bottom-[-10px] border-x-transparent border-x-[10px] border-b-transparent border-b-0 w-fit left-[calc(50%-10px)]"></div>
              </CustomOverlayMap>
            )}
          </div>
        ))}
      </Map>
    </div>
  );
}
export default SearchMap;
