import { buttonStyles } from "lib/styles";
import Button from "./Button";

function Modal({ name, handleModalBg, handleButtonClick }) {
  const { kakaoLoginButton, mdGreenButton } = buttonStyles;

  let firstPar = null;
  let secondPar = null;

  if (name === "카카오로 계속하기") {
    firstPar = "반갑습니다.";
    secondPar = "로그인 하고 모든 기능을 이용해보세요.";
  } else if (name === "성공") {
    name = "확인";
    firstPar = "요청하신 테마가 접수 되었습니다. 🥳";
  } else if (name === "실패") {
    name = "확인";
    firstPar = "요청이 정상적으로 전달되지 않았습니다. 😥";
  }

  return (
    <div
      className="fixed top-0 left-0 right-0 bottom-0 flex justify-center items-center bg-modalBg z-50"
      onClick={name === "카카오로 계속하기" ? handleModalBg : undefined}
    >
      <div
        className="bg-white py-10 px-12 rounded-lg text-center font-semibold"
        onClick={(e) => e.stopPropagation()}
      >
        <div className="whitespace-nowrap mb-7">
          <p>{firstPar}</p>
          <p>{secondPar}</p>
        </div>
        {name === "카카오로 계속하기" ? (
          <Button
            style={kakaoLoginButton}
            isFull={true}
            icon="kakaotalk"
            name={name}
            handleButtonClick={handleButtonClick}
          />
        ) : (
          <Button
            style={mdGreenButton}
            isFull={true}
            name={name}
            handleButtonClick={handleButtonClick}
          />
        )}
      </div>
    </div>
  );
}

export default Modal;
