import Button from "components/Button";
import Header from "components/Header";
import { useState } from "react";
import { buttonStyles } from "styles/props";

function Request() {
  const [request, setRequest] = useState({ title: "", content: "" });
  const { title, content } = request;

  const { smGreenButton, smWhiteButton } = buttonStyles;

  const handleRequestChange = (e) => {
    const { name, value } = e.target;
    setRequest({ ...request, [name]: value });
  };

  const handleRequestSubmit = () => {
    console.log(request);
  };

  return (
    <div className="relative">
      <Header />
      <div className="padding-container">
        <div className="font-semibold text-center mb-6">
          새로운 테마를 등록하고 싶으신가요?
        </div>
        <div className="bg-white rounded-lg border border-borderGray p-[13px] mb-5">
          <input
            className="text-sm w-full"
            type="text"
            placeholder="테마의 이름을 추천해주세요."
            name="title"
            value={title}
            onChange={(e) => handleRequestChange(e)}
          />
        </div>
        <div className="bg-white rounded-lg border border-borderGray p-[13px] mb-5">
          <textarea
            className="text-sm w-full h-60 resize-none"
            name="content"
            placeholder="나만의 장소를 소개할 수 있는 유용한 테마를 추가할 수 있습니다.&#10;테마를 설명할 수 있는 소개글을 적어주세요.&#10;ㅤ&#10;• 요청하신 테마는 확인 후 빠르게 등록됩니다.&#10;• 약간 변경되어 등록될 수 있는 점 양해해주세요!"
            value={content}
            onChange={(e) => handleRequestChange(e)}
          />
        </div>
        <div className="text-center">
          <div className="inline-grid grid-cols-2 gap-3">
            <Button name="취소하기" style={smWhiteButton} />
            <Button
              name="테마 추천하기"
              handleButtonClick={handleRequestSubmit}
              style={smGreenButton}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default Request;
