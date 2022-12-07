import { createSlice } from "@reduxjs/toolkit";

// Todo: current initial state is just for testing
const initialState = {
  username: "user0",
  password: "00000000",
  expirationTime: 1769303561038,
};

// const initialState = {
//   username: null,
//   expirationTime: null,
// };

export const sessionSlice = createSlice({
  name: "session",
  initialState: initialState,
  reducers: {
    setLogin: (state, action) => {
      state.username = action.payload.username;
      state.password = action.payload.password;
      state.expirationTime = Date.now() + 48 * 60 * 60 * 1000; // 48 hours
    },
    setLogout: (state) => {
      state.username = null;
      state.password = null;
      state.expirationTime = null;
    },
  },
});

export const { setLogin, setLogout } = sessionSlice.actions;

export const selectSession = (state) => state.session;

export default sessionSlice.reducer;
