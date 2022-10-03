import Footer from "components/Footer";
import Header from "components/Header";
import { useSelector } from "react-redux";
import { Outlet } from "react-router-dom";

// basic header && footer
function Layout1() {
  const isLogin = useSelector((state) => state.isLogin.value);
  const loginUserInfo = useSelector((state) => state.userInfo.value);

  return (
    <>
      <Header
        isLogin={isLogin}
        userId={loginUserInfo.userId}
        userEmoji={loginUserInfo.userEmoji}
      />
      <Outlet />
      <Footer />
    </>
  );
}

export default Layout1;
