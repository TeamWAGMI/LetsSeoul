import { createSlice } from "@reduxjs/toolkit";

const initialState = { value: false };

export const isDrawerOpenSlice = createSlice({
  name: "isDrawerOpen",
  initialState,
  reducers: {
    handleDrawerOpen: (state, action) => {
      state.value = action.payload;
    },
  },
});

export const { handleDrawerOpen } = isDrawerOpenSlice.actions;
export default isDrawerOpenSlice.reducer;
