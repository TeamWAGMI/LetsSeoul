import axios from "axios";
import Button from "components/common/Button";
import Card from "components/common/Card";
import Header from "components/common/Header";
import Score from "components/Score";
import { buttonStyles } from "lib/styles";
import { checkSession } from "lib/utils/checkSession";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { useLocation, useNavigate } from "react-router-dom";
// import ImageUpload from "components/ImageUpload";
// import { useState } from "react";

function ReviewForm() {
  const [review, setReview] = useState({ content: "", score: null });
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { state } = useLocation();
  const { smGreenButton, smWhiteButton } = buttonStyles;
  // const [imageSrc, setImageSrc] = useState("");
  // const handleChangeImage = (e) => {
  //   const file = URL.createObjectURL(e.target.files[0]);
  //   setImageSrc(file);
  // };

  const handleReviewSubmit = () => {
    if (review.content === "" || review.score === null) {
      return window.alert("별점과 함께 리뷰를 남겨주세요.");
    }

    const data = {
      store: {
        itemid: state.itemid,
        title: state.content,
        address: state.address,
        lat: state.position.lat,
        lng: state.position.lng,
      },
      review: {
        ...review,
        images: [],
      },
    };

    const nextAPICall = () => {
      axios
        .post(`/api/v1/themes/${state.themeInfo.themeId}`, data)
        .then(() => navigate(`/theme/${state.themeInfo.themeId}`))
        .catch((err) => console.error(err.message));
    };

    checkSession(dispatch, nextAPICall);
  };

  return (
    <>
      <Header
        hasBackButton={true}
        storeName={state.content}
        storeAddress={state.address}
      />
      <div className="padding-container">
        <div className="mb-[30px]">
          <div className="smHeadline">여기는 어떤 곳인가요?</div>
          <ul>
            <Card
              id={state.themeInfo.themeId}
              emoji={state.themeInfo.themeEmoji}
              name={state.themeInfo.themeTitle}
              isOneLine={true}
              path={`/theme/${state.themeInfo.themeId}`}
            />
          </ul>
        </div>
        <div className="mb-[30px]">
          <div className="smHeadline">이 장소에 대한 이야기를 들려주세요.</div>
          <div className="flex flex-col items-center bg-white rounded-lg p-5 mb-5">
            <div className="mb-2">
              <span className="mr-3">추천 장소의 별점은요?</span>
              <Score score={review.score} handleReviewChange={setReview} />
            </div>
            {/* <div className="grid grid-cols-3 gap-3 my-4">
              <ImageUpload id="file_1" />
              <ImageUpload id="file_2" />
              <ImageUpload id="file_3" />
            </div> */}
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

export default ReviewForm;
