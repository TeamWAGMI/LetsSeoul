import { createSlice } from "@reduxjs/toolkit";
import { PURGE } from "redux-persist";

const initialState = { value: "" };

export const prevPathSlice = createSlice({
  name: "prevPath",
  initialState,
  reducers: {
    getPrevPath: (state, action) => {
      state.value = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(PURGE, () => initialState);
  },
});

export const { getPrevPath } = prevPathSlice.actions;
export default prevPathSlice.reducer;
