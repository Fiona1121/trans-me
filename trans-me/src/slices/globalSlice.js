import { createSlice } from "@reduxjs/toolkit";
import { nanoid } from "nanoid";

// Todo: current initial state is just for testing
const initialState = {
  // audio operation
  audioFiles: [],
  currentPlaying: null,
  isPlaying: false,

  // block operation
  blocks: [
    {
      id: 0,
      content: "<h1>Hello World</h1>",
      isHidden: false,
    },
    {
      id: 1,
      content: "<h2>I am a block!</h2>",
      isHidden: false,
    },
  ],
  isRecording: false,
};

// const initialState = {
//   // audio operation
//   audioFiles: [],
//   currentPlaying: null,
//   isPlaying: false,

//   // block operation
//   blocks: [],
//   isRecording: false,
// };

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
    addEmptyBlock: (state, action) => {
      state.blocks.push({
        id: nanoid(),
        content: "",
        isHidden: false,
      });
    },
    moveBlockUp: (state, action) => {
      const { id } = action.payload;
      const index = state.blocks.findIndex((block) => block.id === id);
      if (index > 0) {
        // immutable swap
        let blocksCopy = [...state.blocks];
        const temp = state.blocks[index - 1];
        blocksCopy[index - 1] = state.blocks[index];
        blocksCopy[index] = temp;
        state.blocks = blocksCopy;
      }
    },
    moveBlockDown: (state, action) => {
      const { id } = action.payload;
      const index = state.blocks.findIndex((block) => block.id === id);
      if (index < state.blocks.length - 1) {
        const temp = state.blocks[index + 1];
        state.blocks[index + 1] = state.blocks[index];
        state.blocks[index] = temp;
      }
    },
    insertBlock: (state, action) => {
      const { index } = action.payload;
      const id = nanoid(6);
      state.blocks = [
        ...state.blocks.slice(0, index),
        { id, content: "", isHidden: false },
        ...state.blocks.slice(index),
      ];
    },
    updateBlockContent: (state, action) => {
      const { id, content } = action.payload;
      const index = state.blocks.findIndex((block) => block.id === id);
      state.blocks[index].content = content;
    },
    deleteBlock: (state, action) => {
      const { id } = action.payload;
      const index = state.blocks.findIndex((block) => block.id === id);
      state.blocks = [
        ...state.blocks.slice(0, index),
        ...state.blocks.slice(index + 1),
      ];
    },
    duplicateBlock: (state, action) => {
      const { id } = action.payload;
      const index = state.blocks.findIndex((block) => block.id === id);
      const newBlock = {
        id: nanoid(6),
        content: state.blocks[index].content,
        isHidden: false,
      };
      state.blocks = [
        ...state.blocks.slice(0, index + 1),
        newBlock,
        ...state.blocks.slice(index + 1),
      ];
    },
    toggleBlockVisibility: (state, action) => {
      const { id } = action.payload;
      const index = state.blocks.findIndex((block) => block.id === id);
      state.blocks[index].isHidden = !state.blocks[index].isHidden;
    },
    mergeBlocks: (state, action) => {
      const { ids } = action.payload;
      const index = state.blocks.findIndex((block) => block.id === ids[0]);
      const newBlock = {
        id: nanoid(6),
        content: ids
          .map((id) => state.blocks.find((block) => block.id === id).content)
          .join(""),
        isHidden: false,
      };
      state.blocks = [
        ...state.blocks.slice(0, index),
        newBlock,
        ...state.blocks.slice(index + ids.length),
      ];
    },
    hideBlocks: (state, action) => {
      const { ids } = action.payload;
      state.blocks = state.blocks.map((block) => {
        if (ids.includes(block.id)) {
          return { ...block, isHidden: true };
        } else {
          return block;
        }
      });
    },
    showBlocks: (state, action) => {
      const { ids } = action.payload;
      state.blocks = state.blocks.map((block) => {
        if (ids.includes(block.id)) {
          return { ...block, isHidden: false };
        } else {
          return block;
        }
      });
    },
    deleteBlocks: (state, action) => {
      const { ids } = action.payload;
      state.blocks = state.blocks.filter((block) => !ids.includes(block.id));
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
  moveBlockUp,
  moveBlockDown,
  insertBlock,
  updateBlockContent,
  deleteBlock,
  duplicateBlock,
  toggleBlockVisibility,
  mergeBlocks,
  hideBlocks,
  showBlocks,
  deleteBlocks,
  addEmptyBlock,
} = globalSlice.actions;

export const selectGlobal = (state) => state.global;

export default globalSlice.reducer;
