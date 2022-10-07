import { useNavigate } from "react-router-dom";
// import { useDispatch, useSelector } from "react-redux";
// import { handleDrawerOpen } from "slice/isDrawerOpenSlice";
import Button from "./Button";
// import Drawer from "./common/Drawer";

// user, theme : emoji, name
function MapNav({ isUsers, emoji, name, isLiked, handleLikeButtonClick }) {
  // const isDrawerOpen = useSelector((state) => state.isDrawerOpen.value);
  // const dispatch = useDispatch();
  const navigate = useNavigate();

  return (
    <nav className="relative">
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
                isLiked ? "text-heartRed" : "text-textDarkGray"
              }`}
              onClick={handleLikeButtonClick}
            >
              {isLiked ? "♥️" : "♡"}
            </span>
          )}
        </div>
      </div>
      {/* <Button
        styles="absolute top-3 right-0 z-30 h-12 px-[14px] bg-bgGray rounded-tl-lg rounded-bl-lg drop-shadow-md "
        icon="hamburger_gray"
        handleButtonClick={() => dispatch(handleDrawerOpen(true))}
      />
      <Drawer isOpen={isDrawerOpen} handleButtonClick={handleDrawerOpen} /> */}
    </nav>
  );
}

export default MapNav;
