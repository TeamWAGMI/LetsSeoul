import axios from "axios";
import { handleLoginModalOpen } from "slice/isLoginModalOpenSlice";
import { handleLogout } from "slice/isLoginSlice";
import { removeUserInfo } from "slice/userInfoSlice";

export const checkSession = (dispatch, nextAPICall) => {
  axios
    .get("/oauth2/users")
    .then((res) => {
      if (res.data === "") {
        dispatch(handleLogout());
        dispatch(removeUserInfo());
        dispatch(handleLoginModalOpen(true));
      } else {
        nextAPICall();
      }
    })
    .catch((err) => console.error(err.message));
};
