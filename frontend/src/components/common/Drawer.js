const Drawer = ({ isOpen, handleButtonClick }) => {
  const list = ["홈", "테마지도 모아보기", "마이페이지", "로그아웃"];

  // const list = [{title: "홈", path: "/"}, {title: "테마지도 모아보기", path: "/search"}, {title: "로그인"}, {title: "마이페이지", path: `/`}, "로그아웃"];;

  return (
    <>
      {isOpen ? (
        <div className="delay-700 opacity-1 z-30 absolute w-[60%] right-0 flex justify-center ">
          <div className="fixed top-[10%] grid grid-cols-1 gap-8">
            {list.map((page, idx) => (
              <div key={idx}>{page}</div>
            ))}
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
