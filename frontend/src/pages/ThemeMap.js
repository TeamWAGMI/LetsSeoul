import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useDispatch } from "react-redux";
import axios from "axios";
import Button from "components/common/Button";
import MapNav from "components/common/MapNav";
import { Map, MapMarker } from "react-kakao-maps-sdk";
import { buttonStyles } from "lib/styles";
import { checkSession } from "lib/utils/checkSession";

function ThemeMap() {
  const [themeInfo, setThemeInfo] = useState({
    themeId: "",
    themeEmoji: "",
    themeTitle: "",
  });
  const [mapOption, setMapOption] = useState({
    centerLat: 37.566769,
    centerLng: 126.978323,
    level: 9,
  });
  const [storeList, setStoreList] = useState([]);
  const { tid } = useParams();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { lgGreenSemiRoundButton } = buttonStyles;

  console.log(mapOption);

  useEffect(() => {
    axios
      .all([
        axios.get(`/api/v1/themes/${tid}/info`),
        axios.get(`/api/v1/themes/${tid}`),
      ])
      .then(
        axios.spread((res1, res2) => {
          setThemeInfo(res1.data);
          setMapOption(res2.data.mapInfo);
          setStoreList(res2.data.stores);
        })
      )
      .catch((err) => console.error(err.message));
  }, [tid]);

  const handleRecommendButton = () => {
    const nextCallBack = () => {
      navigate(`/theme/search/${tid}`, { state: { themeInfo } });
    };
    checkSession(dispatch, nextCallBack);
  };

  return (
    <>
      <div className="sticky z-10 desktop:top-[90%] desktop:ml-[61%] top-[90%] ml-[50%] h-0 cursor-pointer">
        <Button
          name="장소 추천하기"
          emoji="📌"
          styles={`${lgGreenSemiRoundButton} py-4`}
          handleButtonClick={handleRecommendButton}
        />
      </div>
      <MapNav emoji={themeInfo.themeEmoji} name={themeInfo.themeTitle} />
      <Map
        center={{ lat: 37.566769, lng: 126.978323 }}
        style={{ width: "100%", height: "100vh" }}
        level={9}
      >
        {storeList.map((store) => (
          <MapMarker
            key={store.storeId}
            position={{ lat: store.lat, lng: store.lng }}
          />
        ))}
      </Map>
    </>
  );
}
export default ThemeMap;
