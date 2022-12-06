import {
  Box,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  TextField,
} from "@mui/material";
import React, { useEffect, useRef, useState } from "react";
import SpeechRecognition, {
  useSpeechRecognition,
} from "react-speech-recognition";

import { BlockAPI } from "../../../../../api";
import { insertBlock } from "../../../../../slices/globalSlice";
import { useDispatch } from "react-redux";
import useAudioRecorder from "../../../../../components/audioRecorder/hooks";
import AudioRecorder from "../../../../../components/audioRecorder";

export default function RecordingOperator({ index, open, setOpen }) {
  const dispatch = useDispatch();
  const audioBoxRef = useRef();
  const [isListening, setIsListening] = useState(false);
  const { transcript, resetTranscript } = useSpeechRecognition();

  const recorderControls = useAudioRecorder();
  const controls = {
    ...recorderControls,
    startRecording: () => {
      recorderControls.startRecording();
      handleListing();
    },
    stopRecording: () => {
      recorderControls.stopRecording();
      handleStopListening();
    },
    togglePauseResume: () => {
      if (isListening) {
        handleStopListening();
      } else {
        handleListing();
      }
      recorderControls.togglePauseResume();
    },
  };

  const addAudioElement = (blob) => {
    const url = URL.createObjectURL(blob);
    const audio = document.createElement("audio");
    audio.src = url;
    audio.controls = true;
    audioBoxRef.current.appendChild(audio);
  };

  const handleListing = () => {
    setIsListening(true);
    SpeechRecognition.startListening({
      continuous: true,
    });
  };
  const handleStopListening = () => {
    setIsListening(false);
    SpeechRecognition.stopListening();
  };

  const handleCloseRecord = () => {
    controls.stopRecording();
    audioBoxRef.current.replaceChildren();
    setOpen(false);
  };

  const handleFinishRecord = async () => {
    const response = await BlockAPI.postBlock({
      content: `<p>${transcript}</p>`,
      hidden: false,
    });
    dispatch(insertBlock({ index: index + 1, block: response.data.data }));
    handleCloseRecord();
  };

  useEffect(() => {
    resetTranscript();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [open]);

  return (
    <div>
      <Dialog open={open} maxWidth="md" fullWidth>
        <DialogTitle>Recording Conversion</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Please allow the access to your microphone and speak into it. Chrome
            Browser is recommended for this feature.
          </DialogContentText>

          <Box
            sx={{
              display: "grid",
              gridTemplateColumns: "auto auto",
              justifyContent: "center",
              p: 2,
              gap: 1,
              maxHeight: "30vh",
            }}
            ref={audioBoxRef}
          />
          <Box
            sx={{
              display: "flex",
              justifyContent: "center",
              m: 2,
            }}
          >
            <AudioRecorder
              onRecordingComplete={(blob) => addAudioElement(blob)}
              recorderControls={controls}
            />
          </Box>
          <TextField
            id="recording-content"
            fullWidth
            InputProps={{
              readOnly: true,
            }}
            value={transcript}
            error={!SpeechRecognition.browserSupportsSpeechRecognition()}
            helperText={
              !SpeechRecognition.browserSupportsSpeechRecognition() ??
              "Your browser does not support speech recognition"
            }
          />
        </DialogContent>
        <DialogActions sx={{ pr: 3, pb: 2 }}>
          <Button onClick={handleCloseRecord}>Cancel</Button>
          <Button
            onClick={handleFinishRecord}
            color="secondary"
            variant="outlined"
          >
            Insert
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
