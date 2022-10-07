import Button from "components/common/Button";
import Card from "components/common/Card";
import Header from "components/common/Header";
import Score from "components/Score";
import { buttonStyles } from "lib/styles";
import { useLocation } from "react-router-dom";
// import ImageUpload from "components/ImageUpload";
// import { useState } from "react";

function ReviewForm() {
  const { smGreenButton, smWhiteButton } = buttonStyles;
  const { state } = useLocation();
  // const [imageSrc, setImageSrc] = useState("");
  // const handleChangeImage = (e) => {
  //   const file = URL.createObjectURL(e.target.files[0]);
  //   setImageSrc(file);
  // };

  console.log(state);

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
              id="1"
              emoji="💻"
              name="혼자 노트북 들고 가기 좋은 카페"
              isOneLine={true}
            />
          </ul>
        </div>
        <div className="mb-[30px]">
          <div className="smHeadline">이 장소에 대한 이야기를 들려주세요.</div>
          <div className="flex flex-col items-center bg-white rounded-lg p-5 mb-5">
            <div className="mb-2">
              <span className="mr-3">추천 장소의 별점은요?</span>
              <Score />
            </div>
            {/* <div className="grid grid-cols-3 gap-3 my-4">
              <ImageUpload id="file_1" />
              <ImageUpload id="file_2" />
              <ImageUpload id="file_3" />
            </div> */}
            <div className="bg-white rounded-lg border border-borderGray p-[13px] w-full text-sm">
              <textarea
                className="text-sm w-full h-60 resize-none"
                name="content"
                // value={content}
                // onChange={(e) => handleRequestChange(e)}
              />
            </div>
          </div>
          <div className="text-center">
            <div className="inline-grid grid-cols-2 gap-3">
              <Button name="취소하기" styles={smWhiteButton} />
              <Button
                name="장소 추천하기"
                // handleButtonClick={handleRequestSubmit}
                styles={smGreenButton}
              />
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default ReviewForm;
