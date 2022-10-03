import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import { buttonStyles } from "lib/styles";
import Button from "./Button";
import Modal from "./Modal";
import { useDispatch } from "react-redux";
import { getPrevPath } from "slice/prevPathSlice";

function Header({
  hasBackButton = false,
  isLogin = false,
  userId,
  userEmoji,
  storeName,
  storeAddress,
}) {
  const [isLoginModalOpen, setIsLoginModalOpen] = useState(false);
  const { backButton, loginButton, hamburgerButton } = buttonStyles;
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogin = () => {
    setIsLoginModalOpen((prev) => !prev);
    dispatch(getPrevPath(window.location.pathname));
    window.location.href = `${process.env.REACT_APP_SERVER}/oauth2/authorization/kakao`;
  };

  const handleBackButton = () => {
    navigate(-1);
  };

  return (
    <>
      <header
        className={`aboslute sticky top-0 py-[14px] px-[18px] bg-wagmiGreen z-30 ${
          hasBackButton ? "h-[84px]" : "h-[60px]"
        }`}
      >
        <div className="flex flex-row justify-between h-[100%]">
          {hasBackButton && (
            <Button
              styles={backButton}
              icon="back"
              handleButtonClick={handleBackButton}
            />
          )}
          <div
            className={`text-white w-min whitespace-nowrap ${
              hasBackButton
                ? "grow text-center flex flex-col justify-around pr-[26px]"
                : "basis-3/4"
            }`}
          >
            {hasBackButton ? (
              <>
                <div className="text-xl font-semibold">{storeName}</div>
                <div className="text-sm">{storeAddress}</div>
              </>
            ) : (
              <Link to="/">
                <img className="py-[3px]" src="/images/logo.svg" alt="logo" />
              </Link>
            )}
          </div>
          {!hasBackButton && (
            <div className="basis-1/4 flex justify-end items-center py-[4px]">
              {isLogin ? (
                <Link to={`/user/${userId}`}>
                  <span className="text-2xl mr-3">{userEmoji}</span>
                </Link>
              ) : (
                <Button
                  styles={loginButton}
                  name="로그인"
                  handleButtonClick={() => setIsLoginModalOpen((prev) => !prev)}
                />
              )}
              <Button styles={hamburgerButton} icon="hamburger" />
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
