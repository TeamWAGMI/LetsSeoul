import { combineReducers } from "@reduxjs/toolkit";
import isLoginSlice from "./isLoginSlice";
import isLoginModalOpenSlice from "./isLoginModalOpenSlice";
import prevPathSlice from "./prevPathSlice";
import userInfoSlice from "./userInfoSlice";

const rootReducer = combineReducers({
  isLogin: isLoginSlice,
  userInfo: userInfoSlice,
  prevPath: prevPathSlice,
  isLoginModalOpen: isLoginModalOpenSlice,
});

export default rootReducer;
