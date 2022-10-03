import Footer from "components/Footer";
import Header from "components/Header";
import { useSelector } from "react-redux";
import { Outlet } from "react-router-dom";

// basic header && footer
function Layout1() {
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
      <Footer />
    </>
  );
}

export default Layout1;
