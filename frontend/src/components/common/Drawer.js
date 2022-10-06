import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

const Drawer = ({ isOpen, handleButtonClick }) => {
  const isLogin = useSelector((state) => state.isLogin.value);
  const userInfo = useSelector((state) => state.userInfo.value);

  return (
    <>
      {isOpen ? (
        <div className="delay-700 opacity-1 z-30 absolute w-[60%] right-0 flex justify-center ">
          <div
            className="fixed top-[10%] grid grid-cols-1 font-semibold"
            onClick={() => handleButtonClick(false)}
          >
            <Link to="/">
              <div className="py-3">홈</div>
            </Link>
            <Link to="/search">
              <div className="py-3">테마지도 모아보기</div>
            </Link>
            {!isLogin ? (
              <div className="py-3">로그인</div>
            ) : (
              <>
                <Link to={`/user/${userInfo.userId}`}>
                  <div className="py-3">마이페이지</div>
                </Link>
                <div className="py-3">로그아웃</div>
              </>
            )}
          </div>
        </div>
      ) : (
        <div className=" opacity-0"></div>
      )}
      <div
        className={
          "transform ease-in-out absolute inset-0 bg-modalBg z-20 " +
          (isOpen ? "w-full" : " translate-x-full delay-700 opacity-0 w-0")
        }
      >
        <div
          className={
            "absolute bg-[#ededed] h-full w-[50%] right-0 transition-all ease-in-out duration-700 " +
            (isOpen ? "translate-x-0 opacity-1" : "translate-x-full opacity-0")
          }
        ></div>
        <div
          className="w-full h-full cursor-pointer"
          onClick={() => handleButtonClick(false)}
        ></div>
      </div>
    </>
  );
};
export default Drawer;
