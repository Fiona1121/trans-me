import {
  Alert,
  AppBar,
  Avatar,
  Box,
  Button,
  Container,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Grid,
  IconButton,
  Snackbar,
  Toolbar,
  Tooltip,
  Typography,
} from "@mui/material";
import moment from "moment";
import TurndownService from "turndown";
import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Navigate } from "react-router-dom";
import { resetState, selectGlobal } from "../../slices/globalSlice";
import { selectSession } from "../../slices/sessionSlice";
import Board from "./board";
import Term from "./term";

export default function Main() {
  const dispatch = useDispatch();
  const { username, expirationTime } = useSelector(selectSession);
  const { blocks } = useSelector(selectGlobal);
  const isLoggedIn = username && expirationTime > Date.now();

  const [alert, setAlert] = useState({});
  const [dialog, setDialog] = useState({ open: false, title: "", content: "" });

  if (!isLoggedIn) {
    return <Navigate to={"/login"} />;
  }

  const handleExport = () => {
    const stringContent = blocks
      .filter((block) => !block.isHidden)
      .map((block) => block.content)
      .join("");
    if (stringContent === "") {
      setAlert({
        open: true,
        severity: "warning",
        msg: "No content or no unhidden block to export.",
      });
    } else {
      const turndownService = new TurndownService();
      const markdown = turndownService.turndown(stringContent);
      const element = window.document.createElement("a");
      element.href = window.URL.createObjectURL(
        new Blob([markdown], { type: "text/plain" })
      );
      element.download =
        "transme-export_" + moment().format("YYYY-MM-DD_HH-mm-ss") + ".md";
      document.body.appendChild(element);
      element.click();
      document.body.removeChild(element);
    }
  };

  const handleNewProject = () => {
    setDialog({
      open: true,
      title: "New Project",
      content:
        "All the blocks and audio files from current project will be deleted and cannot be recovered. Are you sure to create a new project?",
      onConfirm: () => {
        dispatch(resetState());
        setDialog({ ...dialog, open: false });
      },
      onCancel: () => {
        setDialog({ ...dialog, open: false });
      },
    });
  };

  return (
    <div>
      <Box sx={{ display: "flex" }}>
        <AppBar sx={{}}>
          <Container maxWidth="xl">
            <Toolbar disableGutters>
              <Typography
                variant="h4"
                noWrap
                sx={{
                  mr: 2,
                  display: "flex",
                  flexGrow: 1,
                  fontFamily: "Playfair Display SC",
                  fontWeight: 500,
                  color: "inherit",
                  textDecoration: "none",
                }}
              >
                TransMe
              </Typography>
              <Box sx={{ display: "flex", mr: 2 }}>
                <Button
                  key={"export-button"}
                  sx={{ my: 2, color: "white", display: "block" }}
                  onClick={handleExport}
                >
                  Export
                </Button>
                <Button
                  key={"new-project-button"}
                  sx={{ my: 2, color: "white", display: "block" }}
                  onClick={handleNewProject}
                >
                  New Project
                </Button>
              </Box>

              <Box sx={{ flexGrow: 0 }}>
                <Tooltip title={"Hello, " + username}>
                  <IconButton sx={{ p: 0 }}>
                    <Avatar alt={username}>{username[0].toUpperCase()}</Avatar>
                  </IconButton>
                </Tooltip>
              </Box>
            </Toolbar>
          </Container>
        </AppBar>
        <Box sx={{ width: "100%" }}>
          <Toolbar />
          <Grid container spacing={2} sx={{ pt: 3, pr: 2, pl: 2 }}>
            <Grid item xs></Grid>
            <Grid item xs={8}>
              <Board blocks={blocks} />
            </Grid>
            <Grid item xs>
              <Term />
            </Grid>
          </Grid>
        </Box>
      </Box>
      <Dialog open={dialog?.open} onClose={dialog?.onCancel}>
        <DialogTitle>{dialog?.title}</DialogTitle>
        <DialogContent>
          <DialogContentText>{dialog?.content}</DialogContentText>
        </DialogContent>
        <DialogActions sx={{ pr: 3, pb: 2 }}>
          <Button onClick={dialog?.onCancel}>Cancel</Button>
          <Button
            onClick={dialog?.onConfirm}
            color="secondary"
            variant="outlined"
          >
            Confirm
          </Button>
        </DialogActions>
      </Dialog>
      <Snackbar
        anchorOrigin={{ vertical: "top", horizontal: "center" }}
        open={alert?.open}
        autoHideDuration={5000}
        onClose={() => setAlert({ ...alert, open: false })}
      >
        <Alert variant="filled" severity={alert?.severity}>
          {alert?.msg}
        </Alert>
      </Snackbar>
    </div>
  );
}
