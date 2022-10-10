import { Link, useLocation, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import Button from "./Button";
import Drawer from "./Drawer";
import Alert from "./Alert";
import { buttonStyles } from "lib/styles";
import { getPrevPath } from "slice/prevPathSlice";
import { handleLoginModalOpen } from "slice/isLoginModalOpenSlice";
import { handleDrawerOpen } from "slice/isDrawerOpenSlice";

function Header({
  hasBackButton = false,
  isLogin = false,
  userId,
  userEmoji,
  storeName,
  storeAddress,
}) {
  const isLoginModalOpen = useSelector((state) => state.isLoginModalOpen.value);
  const isDrawerOpen = useSelector((state) => state.isDrawerOpen.value);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const location = useLocation();

  const { loginButton, hamburgerButton } = buttonStyles;

  const handleLogin = () => {
    dispatch(handleLoginModalOpen(false));
    dispatch(getPrevPath(location.pathname));
    window.location.replace(
      `${process.env.REACT_APP_SERVER}/oauth2/authorization/kakao`
    );
  };

  return (
    <>
      <header
        className={`aboslute sticky top-0 py-[14px] px-[18px] bg-wagmiGreen z-10 ${
          hasBackButton ? "h-[84px]" : "h-[60px]"
        }`}
      >
        <div className="flex flex-row justify-between h-[100%]">
          {hasBackButton && (
            <Button
              styles="pr-[18px]"
              icon="back"
              handleButtonClick={() => navigate(-1)}
            />
          )}
          <div
            className={`text-white w-min ${
              hasBackButton
                ? "grow text-center flex flex-col justify-around"
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
          {hasBackButton ? (
            <div className="w-[28px] flex justify-center items-center text-heartRed">
              ♥️
            </div>
          ) : (
            <div className="basis-1/4 flex justify-end items-center py-[4px]">
              {isLogin ? (
                <Link to={`/user/${userId}`}>
                  <span className="text-2xl mr-3">{userEmoji}</span>
                </Link>
              ) : (
                <Button
                  styles={loginButton}
                  name="로그인"
                  handleButtonClick={() => dispatch(handleLoginModalOpen(true))}
                />
              )}
              <Button
                styles={hamburgerButton}
                icon="hamburger"
                handleButtonClick={() => dispatch(handleDrawerOpen(true))}
              />
            </div>
          )}
        </div>
      </header>
      {isLoginModalOpen && (
        <Alert
          name="카카오로 계속하기"
          handleModalBg={() => dispatch(handleLoginModalOpen(false))}
          handleButtonClick={handleLogin}
        />
      )}
      <Drawer isOpen={isDrawerOpen} handleButtonClick={handleDrawerOpen} />
    </>
  );
}

export default Header;
