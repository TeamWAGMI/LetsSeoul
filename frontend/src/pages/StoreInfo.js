import Card from "components/Card";
import Header from "components/Header";
import Review from "components/Review";
import { storeReview, storeTheme } from "static/dummyData";

function StoreInfo() {
  const { content } = storeReview;

  return (
    <div className="relative">
      <Header
        hasBackButton={true}
        storeName="우리가게 대흥역점"
        storeAddress="서울 성북구 동선동2가 150"
      />
      <div className="padding-container">
        <div className="mb-[30px]">
          <div className="smHeadline">여기는 어떤 곳인가요?</div>
          <ul className="grid gap-2">
            {storeTheme.map((theme) => {
              const { id, emoji, name } = theme;

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
            {content.map((review) => {
              const {
                userId,
                userEmoji,
                userNickname,
                reviewId,
                reviewContent,
                reviewScore,
                createdDatetime,
              } = review;

              return (
                <Review
                  key={reviewId}
                  userId={userId}
                  emoji={userEmoji}
                  nickname={userNickname}
                  reviewId={reviewId}
                  score={reviewScore}
                  content={reviewContent}
                  createdAt={createdDatetime}
                />
              );
            })}
          </ul>
        </div>
      </div>
    </div>
  );
}

export default StoreInfo;
