import Header from "components/common/Header";
import { useSelector } from "react-redux";
import { Outlet } from "react-router-dom";

// basic header
function Layout1() {
  const isLogin = useSelector((state) => state.isLogin.value);
  const loginUserInfo = useSelector((state) => state.userInfo.value);
  const { userId, userEmoji } = loginUserInfo;

  return (
    <>
      <Header isLogin={isLogin} userId={userId} userEmoji={userEmoji} />
      <Outlet context={{ userId }} />
    </>
  );
}

export default Layout1;
