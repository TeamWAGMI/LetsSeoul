import axios from "axios";
import Card from "components/common/Card";
import Header from "components/common/Header";
import Review from "components/Review";
import { scrollToTop } from "lib/utils/scrollToTop";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Map, MapMarker } from "react-kakao-maps-sdk";
import { useDispatch, useSelector } from "react-redux";
import { checkSession } from "lib/utils/checkSession";

// 좌표로 지도 마크 찍는 기능은 지도 불러온 다음 구현하기
function StoreInfo() {
  const isLogin = useSelector((state) => state.isLogin.value);
  const [storeInfo, setStoreInfo] = useState({
    name: "",
    address: "",
    latitude: "37.566769",
    longitude: "126.921459443831",
  });
  const [storeThemes, setStoreThemes] = useState([]);
  const [storeReviews, setStoreReviews] = useState([]);
  // const [pageInfo, setPageInfo] = useState([]);
  const [isWished, setIsWished] = useState(false);
  const { sid } = useParams();
  const dispatch = useDispatch();

  useEffect(() => {
    scrollToTop();
    axios
      .all([
        axios.get(`/api/v1/stores/${sid}`),
        axios.get(`/api/v1/stores/${sid}/themes`),
        axios.get(`/api/v1/stores/${sid}/reviews`),
      ])
      .then(
        axios.spread((res1, res2, res3) => {
          setStoreInfo((prev) => ({ ...prev, ...res1.data }));
          setStoreThemes(res2.data);
          setStoreReviews(res3.data.content);
        })
      )
      .catch((err) => console.error(err.message));
  }, [sid]);

  useEffect(() => {
    if (isLogin) {
      // 비로그인 유저가 유저 페이지 접속하자마자 로그인 모달이 뜨지 않도록 설정
      const nextAPICall = () => {
        axios
          .get(`/api/v1/stores/${sid}/follows`)
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
  }, [isLogin, sid, dispatch]);

  const handleWishButtonClick = () => {
    const nextAPICall = () => {
      if (isWished) {
        axios
          .delete(`/api/v1/stores/${sid}/wishes`)
          .then(() => setIsWished(false))
          .catch((err) => console.error(err.message));
      } else {
        axios
          .post(`/api/v1/stores/${sid}/wishes`)
          .then(() => setIsWished(true))
          .catch((err) => console.error(err.message));
      }
    };
    checkSession(dispatch, nextAPICall);
  };

  return (
    <>
      <Header
        hasBackButton={true}
        storeName={storeInfo.name}
        storeAddress={storeInfo.address}
        hasWishButton={true}
        isWished={isWished}
        handleWishButtonClick={handleWishButtonClick}
      />
      <div className="padding-container">
        <div className="mb-[30px]">
          <div className="smHeadline">여기는 어떤 곳인가요?</div>
          <ul className="grid gap-2">
            {storeThemes.map(({ id, emoji, title }) => {
              return (
                <Card
                  key={id}
                  id={id}
                  emoji={emoji}
                  name={title}
                  isOneLine={true}
                />
              );
            })}
          </ul>
        </div>
        <div className="mb-[30px]">
          <div className="smHeadline">어디에 있나요?</div>
          <Map
            center={{ lat: storeInfo.latitude, lng: storeInfo.longitude }}
            style={{
              width: "100%",
              height: "144px",
              borderRadius: "10px",
            }}
            level={9}
          >
            <MapMarker
              position={{ lat: storeInfo.latitude, lng: storeInfo.longitude }}
            />
          </Map>
        </div>
        <div className="mb-[30px]">
          <div className="smHeadline">이 장소에 대한 이야기들</div>
          <ul className="grid gap-2">
            {storeReviews.map(
              ({
                reviewId,
                userId,
                userEmoji,
                userNickname,
                reviewScore,
                reviewContent,
                createDatetime,
                modifiedDatetime,
              }) => {
                return (
                  <Review
                    key={reviewId}
                    reviewId={reviewId}
                    userId={userId}
                    emoji={userEmoji}
                    nickname={userNickname}
                    score={reviewScore}
                    content={reviewContent}
                    createdAt={createDatetime}
                    modifiedAt={modifiedDatetime}
                    storeReviews={storeReviews}
                    setStoreReviews={setStoreReviews}
                  />
                );
              }
            )}
          </ul>
        </div>
      </div>
    </>
  );
}

export default StoreInfo;
