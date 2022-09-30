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

  const getUserData = (userData) => ({
    uid: userData[1],
    nickname: userData[0],
    userEmoji: userData[2],
  });

  useEffect(() => {
    if (window.location.search) {
      dispatch(
        getUserInfo(
          getUserData(
            decodeURIComponent(
              escape(window.atob(window.location.search.slice(3)))
            ).split(",")
          )
        )
      );
      dispatch(handleLogin());
    }
    navigate(redirectPath);
  }, [dispatch, navigate, redirectPath]);

  return <></>;
}

export default LoginData;
