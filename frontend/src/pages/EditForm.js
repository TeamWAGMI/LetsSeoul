import axios from "axios";
import Button from "components/common/Button";
import Header from "components/common/Header";
import Score from "components/Score";
import { buttonStyles } from "lib/styles";
import { checkSession } from "lib/utils/checkSession";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { useLocation, useNavigate } from "react-router-dom";

function EditForm() {
  const { state } = useLocation();
  const [review, setReview] = useState({
    content: state.content,
    score: state.score,
  });
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { smGreenButton, smWhiteButton } = buttonStyles;

  const handleReviewSubmit = () => {
    if (review.content === "" || review.score === null) {
      return window.alert("별점과 함께 리뷰를 남겨주세요.");
    }

    const data = {
      userId: state.userId,
      reviewScore: review.score,
      reviewContent: review.content,
    };

    const nextAPICall = () => {
      axios
        .patch(`/api/v1/stores/reviews/${state.reviewId}`, data)
        .then(() => navigate(`/store/${state.sid}`))
        .catch((err) => console.error(err.message));
    };

    checkSession(dispatch, nextAPICall);
  };

  return (
    <>
      <Header
        hasBackButton={true}
        storeName={state.storeName}
        storeAddress={state.storeAddress}
      />
      <div className="padding-container">
        <div className="mb-[30px]">
          <div className="smHeadline">이 장소에 대한 이야기를 들려주세요.</div>
          <div className="flex flex-col items-center bg-white rounded-lg p-5 mb-5">
            <div className="mb-2">
              <span className="mr-3">추천 장소의 별점은요?</span>
              <Score score={review.score} handleReviewChange={setReview} />
            </div>
            <div className="bg-white rounded-lg border border-borderGray p-[13px] w-full text-sm focus-within:ring focus-within:ring-wagmiLightGreen">
              <textarea
                className="text-sm w-full h-60 resize-none"
                name="content"
                value={review.content}
                onChange={(e) =>
                  setReview((prev) => ({ ...prev, content: e.target.value }))
                }
              />
            </div>
          </div>
          <div className="text-center">
            <div className="inline-grid grid-cols-2 gap-3">
              <Button
                name="취소하기"
                styles={smWhiteButton}
                handleButtonClick={() => navigate(-1)}
              />
              <Button
                name="장소 추천하기"
                styles={smGreenButton}
                handleButtonClick={handleReviewSubmit}
              />
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default EditForm;
