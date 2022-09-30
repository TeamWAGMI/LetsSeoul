import { createSlice } from "@reduxjs/toolkit";
import { PURGE } from "redux-persist";

const initialState = { value: false };

export const isLoginSlice = createSlice({
  name: "isLogin",
  initialState,
  reducers: {
    handleLogin: (state) => {
      state.value = true;
    },
    handleLogout: (state) => {
      state.value = false;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(PURGE, () => initialState);
  },
});

export const { handleLogin, handleLogout } = isLoginSlice.actions;
export default isLoginSlice.reducer;
