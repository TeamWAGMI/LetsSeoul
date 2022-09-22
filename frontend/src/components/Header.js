import { Link } from "react-router-dom";
import Button from "./Button";
import { buttonStyles } from "styles/props";

function Header({
  hasBackButton = false,
  isLogin = false,
  userImoji,
  storeName,
  storeAddress,
}) {
  const { backButton, loginButton, hamburgerButton } = buttonStyles;

  return (
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
              <Button style={loginButton} name="로그인" />
            )}
            <Button style={hamburgerButton} icon="hamburger" />
          </div>
        )}
      </div>
    </header>
  );
}

export default Header;
