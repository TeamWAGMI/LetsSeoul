import Button from "components/common/Button";
import Alert from "components/common/Alert";
import { useState } from "react";
import { buttonStyles } from "lib/styles";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function RequestForm() {
  const [request, setRequest] = useState({ themeTitle: "", themeContent: "" });
  const [response, setResponse] = useState(null);
  const [error, setError] = useState(null);
  const { themeTitle, themeContent } = request;

  const navigate = useNavigate();
  const { smGreenButton, smWhiteButton } = buttonStyles;

  const handleRequestChange = (e) => {
    const { name, value } = e.target;
    setRequest({ ...request, [name]: value });
  };

  const backToSearchPage = () => {
    navigate("/search");
  };

  const handleRequestSubmit = async () => {
    if (themeTitle !== "" && themeContent !== "") {
      try {
        const res = await axios.post("/api/v1/themes", request);
        setResponse(res.data.success);
      } catch (err) {
        setError(err.response.status);
      }
    }
  };

  return (
    <div className="padding-container">
      <div className="font-semibold text-center mb-6">
        새로운 테마를 등록하고 싶으신가요?
      </div>
      <div className="bg-white rounded-lg border border-borderGray p-[13px] mb-5">
        <input
          className="text-sm w-full"
          type="text"
          placeholder="테마의 이름을 추천해주세요."
          name="themeTitle"
          value={themeTitle}
          onChange={(e) => handleRequestChange(e)}
          required
        />
      </div>
      <div className="bg-white rounded-lg border border-borderGray p-[13px] mb-5">
        <textarea
          className="text-sm w-full h-60 resize-none"
          name="themeContent"
          placeholder="나만의 장소를 소개할 수 있는 유용한 테마를 추가할 수 있습니다.&#10;테마를 설명할 수 있는 소개글을 적어주세요.&#10;ㅤ&#10;• 요청하신 테마는 확인 후 빠르게 등록됩니다.&#10;• 약간 변경되어 등록될 수 있는 점 양해해주세요!"
          value={themeContent}
          onChange={(e) => handleRequestChange(e)}
          required
        />
      </div>
      <div className="text-center">
        <div className="inline-grid grid-cols-2 gap-3">
          <Button
            name="취소하기"
            styles={smWhiteButton}
            handleButtonClick={backToSearchPage}
          />
          <Button
            name="테마 추천하기"
            handleButtonClick={handleRequestSubmit}
            styles={smGreenButton}
          />
        </div>
      </div>
      {response && <Alert name="성공" handleButtonClick={backToSearchPage} />}
      {response === false && (
        <Alert name="실패" handleButtonClick={() => setResponse(null)} />
      )}
      {error && <Alert name="실패" handleButtonClick={() => setError(null)} />}
    </div>
  );
}

export default RequestForm;
