import { useNavigate } from "react-router-dom";
import Button from "./Button";

// user, theme : emoji, name
function MapNav({ isUsers, emoji, name, isLiked, handleLikeButtonClick }) {
  const navigate = useNavigate();

  return (
    <nav className="relative">
      <div className="absolute top-3 z-30 flex bg-bgGray rounded-tr-lg rounded-br-lg drop-shadow-md">
        <Button
          styles="px-[14px] py-3 border-r-[1px] border-[#bdbdbd] leading-none"
          icon="back_gray"
          handleButtonClick={() => navigate(-1)}
        />
        <div className="flex px-[14px] py-3">
          <div
            className="cursor-pointer whitespace-nowrap"
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

      <Button
        styles="absolute top-3 right-0 z-30 h-12 px-[14px] bg-bgGray rounded-tl-lg rounded-bl-lg drop-shadow-md "
        icon="hamburger_gray"
      />
    </nav>
  );
}

export default MapNav;
