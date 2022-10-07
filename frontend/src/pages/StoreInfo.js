import axios from "axios";
import Card from "components/common/Card";
import Header from "components/common/Header";
import Review from "components/Review";
import { scrollToTop } from "lib/utils/scrollToTop";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { storeTheme } from "static/dummyData";

// 좌표로 지도 마크 찍는 기능은 지도 불러온 다음 구현하기
function StoreInfo() {
  const [storeInfo, setStoreInfo] = useState({});
  const [storeReviews, setStoreReviews] = useState([]);
  // const [pageInfo, setPageInfo] = useState([]);
  const { sid } = useParams();

  // themeId가 왜 필요? 확인 필요!
  const tid = 1;

  useEffect(() => {
    scrollToTop();
    axios.get(`/api/v1/stores/${sid}`).then((res) => setStoreInfo(res.data));
    axios.get(`/api/v1/themes/${tid}/stores/${sid}/review`).then((res) => {
      setStoreReviews(res.data.content);
      // setPageInfo(res.data.pageInfo);
    });
  }, [sid]);

  return (
    <>
      <Header
        hasBackButton={true}
        storeName={storeInfo.name}
        storeAddress={storeInfo.address}
      />
      <div className="padding-container">
        <div className="mb-[30px]">
          <div className="smHeadline">여기는 어떤 곳인가요?</div>
          <ul className="grid gap-2">
            {storeTheme.map(({ id, emoji, name }) => {
              return (
                <Card
                  key={id}
                  id={id}
                  emoji={emoji}
                  name={name}
                  isOneLine={true}
                />
              );
            })}
          </ul>
        </div>
        <div className="mb-[30px]">
          <div className="smHeadline">어디에 있나요?</div>
          <div className="h-36 rounded-lg bg-white"></div>
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
