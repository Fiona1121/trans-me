import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  // audio operation
  audioFiles: [],
  currentPlaying: null,
  isPlaying: false,

  // block operation
  blocks: [],
  isRecording: false,
};

export const globalSlice = createSlice({
  name: "global",
  initialState,
  reducers: {
    // audio operation
    setAudioFiles: (state, action) => {
      state.audioFiles = action.payload;
    },
    setCurrentPlaying: (state, action) => {
      state.currentPlaying = action.payload;
    },
    setIsPlaying: (state, action) => {
      state.isPlaying = action.payload;
    },
    setBlocks: (state, action) => {
      state.blocks = action.payload;
    },
    setIsRecording: (state, action) => {
      state.isRecording = action.payload;
    },
  },
});

export const {
  setAudioFiles,
  setCurrentPlaying,
  setIsPlaying,
  setBlocks,
  setIsRecording,
} = globalSlice.actions;

export const selectGlobal = (state) => state.global;

export default globalSlice.reducer;
