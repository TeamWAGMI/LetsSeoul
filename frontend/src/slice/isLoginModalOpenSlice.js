import { createSlice } from "@reduxjs/toolkit";

const initialState = { value: false };

export const isLoginModalOpenSlice = createSlice({
  name: "isLoginModalOpen",
  initialState,
  reducers: {
    handleLoginModalOpen: (state, action) => {
      state.value = action.payload;
    },
  },
});

export const { handleLoginModalOpen } = isLoginModalOpenSlice.actions;
export default isLoginModalOpenSlice.reducer;
