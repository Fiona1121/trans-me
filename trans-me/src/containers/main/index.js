import {
  AppBar,
  Avatar,
  Box,
  Button,
  Container,
  Grid,
  IconButton,
  Toolbar,
  Tooltip,
  Typography,
} from "@mui/material";
import React from "react";
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

  if (!isLoggedIn) {
    return <Navigate to={"/login"} />;
  }

  const handleExport = () => {
    // const stringContent = blocks.map((block) => block.content).join("");
  };

  const handleNewProject = () => {
    dispatch(resetState());
  };

  return (
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
  );
}
