import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { handleLogin } from "slice/isLoginSlice";
import { getUserInfo } from "slice/userInfoSlice";
import { useSelector } from "react-redux";

function LoginData() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const redirectPath = useSelector((state) => state.prevPath.value);
  const isLogin = useSelector((state) => state.isLogin.value);
  const getUserData = (userData) => ({
    userId: userData[1],
    userNickname: userData[0],
    userEmoji: userData[2],
  });

  useEffect(() => {
    const userInfo = async () => {
      await dispatch(
        getUserInfo(
          getUserData(
            decodeURIComponent(
              escape(window.atob(window.location.search.slice(3)))
            ).split(",")
          )
        )
      );
      await dispatch(handleLogin());
    };
    userInfo();
    if (isLogin) navigate(redirectPath, { replace: true });
  }, [isLogin, dispatch, navigate, redirectPath]);

  return <></>;
}

export default LoginData;
