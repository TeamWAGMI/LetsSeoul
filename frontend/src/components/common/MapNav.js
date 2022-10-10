import { useLocation, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import Alert from "components/common/Alert";
import Button from "./Button";
import { handleLoginModalOpen } from "slice/isLoginModalOpenSlice";
import { getPrevPath } from "slice/prevPathSlice";

// user, theme : emoji, name
function MapNav({ isUsers, emoji, name, isWished, handleWishButtonClick }) {
  const isLoginModalOpen = useSelector((state) => state.isLoginModalOpen.value);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const location = useLocation();

  const handleLogin = () => {
    dispatch(handleLoginModalOpen(false));
    dispatch(getPrevPath(location.pathname));
    window.location.replace(
      `${process.env.REACT_APP_SERVER}/oauth2/authorization/kakao`
    );
  };

  return (
    <nav className="relative">
      {isLoginModalOpen && (
        <div className="absolute">
          <Alert
            name="카카오로 계속하기"
            handleModalBg={() => dispatch(handleLoginModalOpen(false))}
            handleButtonClick={handleLogin}
          />
        </div>
      )}
      <div className="absolute top-3 z-10 flex bg-white rounded-tr-lg rounded-br-lg drop-shadow-md">
        <Button
          styles="px-[14px] py-3 border-r-[1px] border-borderGray leading-none"
          icon="back_gray"
          handleButtonClick={() => navigate(-1)}
        />
        <div className="flex px-[14px] py-3">
          <div
            className="cursor-pointer"
            onClick={() => window.location.reload()}
          >
            <span>{emoji}</span>
            <span className="ml-2 text-textDarkGray font-semibold">{name}</span>
          </div>
          {!isUsers && (
            <span
              className={`cursor-pointer ml-3 ${
                isWished ? "text-heartRed" : "text-textDarkGray"
              }`}
              onClick={handleWishButtonClick}
            >
              {isWished ? "♥" : "♡"}
            </span>
          )}
        </div>
      </div>
      {/* <Button
        styles="absolute top-3 right-0 z-30 h-12 px-[14px] bg-bgGray rounded-tl-lg rounded-bl-lg drop-shadow-md "
        icon="hamburger_gray"
        handleButtonClick={() => dispatch(handleDrawerOpen(true))}
      />*/}
    </nav>
  );
}

export default MapNav;
