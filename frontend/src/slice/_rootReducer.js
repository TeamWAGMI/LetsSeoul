import { combineReducers } from "@reduxjs/toolkit";
import isLoginSlice from "./isLoginSlice";
import userInfoSlice from "./userInfoSlice";

const rootReducer = combineReducers({
  isLogin: isLoginSlice,
  userInfo: userInfoSlice,
});

export default rootReducer;
