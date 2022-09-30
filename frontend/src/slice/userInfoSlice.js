import { createSlice } from "@reduxjs/toolkit";
import { PURGE } from "redux-persist";

const initialState = { value: {} };

export const userInfoSlice = createSlice({
  name: "userInfo",
  initialState,
  reducers: {
    getUserInfo: (state, action) => {
      state.value = action.payload;
    },
    removeUserInfo: (state) => {
      state.value = {};
    },
  },
  extraReducers: (builder) => {
    builder.addCase(PURGE, () => initialState);
  },
});

export const { getUserInfo, removeUserInfo } = userInfoSlice.actions;
export default userInfoSlice.reducer;
