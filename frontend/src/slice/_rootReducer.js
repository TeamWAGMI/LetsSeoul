import { combineReducers } from "@reduxjs/toolkit";
import isLoginSlice from "./isLoginSlice";
import prevPathSlice from "./prevPathSlice";
import userInfoSlice from "./userInfoSlice";

const rootReducer = combineReducers({
  isLogin: isLoginSlice,
  userInfo: userInfoSlice,
  prevPath: prevPathSlice,
});

export default rootReducer;
