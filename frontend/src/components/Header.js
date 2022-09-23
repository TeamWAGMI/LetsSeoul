import { Link } from "react-router-dom";
import { useState } from "react";
import { buttonStyles } from "styles/props";
import Button from "./Button";
import Modal from "./Modal";

function Header({
  hasBackButton = false,
  isLogin = false,
  userImoji,
  storeName,
  storeAddress,
}) {
  const [isLoginModalOpen, setIsLoginModalOpen] = useState(false);
  const { backButton, loginButton, hamburgerButton } = buttonStyles;

  const handleLogin = () => {
    setIsLoginModalOpen((prev) => !prev);
  };

  return (
    <>
      <header
        className={`aboslute sticky top-0 py-[14px] px-[18px] bg-wagmiGreen z-30 ${
          hasBackButton ? "h-[84px]" : "h-[60px]"
        }`}
      >
        <div className="flex flex-row justify-between h-[100%]">
          {hasBackButton && <Button style={backButton} icon="back" />}
          <div
            className={`text-white w-min whitespace-nowrap ${
              hasBackButton
                ? "grow text-center flex flex-col justify-around"
                : "basis-3/4"
            }`}
          >
            {hasBackButton ? (
              <>
                <div className="text-xl font-medium">{storeName}</div>
                <div className="text-sm">{storeAddress}</div>
              </>
            ) : (
              <Link to="/">
                <img className="py-[3px]" src="images/logo.svg" alt="logo" />
              </Link>
            )}
          </div>
          {!hasBackButton && (
            <div className="basis-1/4 flex justify-end items-center py-[4px]">
              {isLogin ? (
                <span className="text-2xl mr-3">{userImoji}</span>
              ) : (
                <Button
                  style={loginButton}
                  name="로그인"
                  handleButtonClick={() => setIsLoginModalOpen((prev) => !prev)}
                />
              )}
              <Button style={hamburgerButton} icon="hamburger" />
            </div>
          )}
        </div>
      </header>
      {isLoginModalOpen && (
        <Modal
          name="카카오로 계속하기"
          handleModalBg={() => setIsLoginModalOpen((prev) => !prev)}
          handleButtonClick={handleLogin}
        />
      )}
    </>
  );
}

export default Header;
