import Header from "components/Header";
import { useSelector } from "react-redux";
import { Outlet } from "react-router-dom";

// basic header only
function Layout2() {
  const isLogin = useSelector((state) => state.isLogin.value);
  const userInfo = useSelector((state) => state.userInfo.value);

  return (
    <>
      <Header
        isLogin={isLogin}
        userId={userInfo.userId}
        userEmoji={userInfo.userEmoji}
      />
      <Outlet />
    </>
  );
}

export default Layout2;
