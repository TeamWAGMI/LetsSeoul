import { Link, redirect, useNavigate, Navigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { React, useState } from "react";
import { buttonStyles } from "styles/props";
import { actionCreators as userActions } from "react-redux";
import axios from "axios";

import Main from "pages/Main";
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
  const CLIENT_ID = "1111";
  const REDIRECT_URI = "http://localhost:3000/login/oauth2/authurl/kakao";
  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;
  const navigate = useNavigate();

  console.log(KAKAO_AUTH_URL);

  const handleLogin = () => {
    setIsLoginModalOpen((prev) => !prev);
    // // navigate(KAKAO_AUTH_URL);
    window.location.href = KAKAO_AUTH_URL;
    navigate("/");
    // window.Navigator("/");

    return;
  };

  // const OAuth2RedirectHandler = (props) => {
  //   const dispatch = useDispatch();

  //   // 인가코드
  //   let code = new URL(window.location.href).searchParams.get("code");

  //   React.useEffect(async () => {
  //     await dispatch(userActions.kakaoLogin(code));
  //   }, []);

  //   return <Spinner />;
  // };

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
