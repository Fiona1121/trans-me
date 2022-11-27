import { Grid } from "@mui/material";
import React from "react";
import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";
import { selectGlobal } from "../../slices/globalSlice";
import { selectSession } from "../../slices/sessionSlice";
import Board from "./board";
import Term from "./term";

export default function Main() {
  const { username, expirationTime } = useSelector(selectSession);
  const { blocks } = useSelector(selectGlobal);
  const isLoggedIn = username && expirationTime > Date.now();

  if (!isLoggedIn) {
    return <Navigate to={"/login"} />;
  }
  return (
    <Grid container spacing={2} sx={{ height: "100vh" }}>
      <Grid item xs></Grid>
      <Grid item xs={8}>
        <Board blocks={blocks} />
      </Grid>
      <Grid item xs>
        <Term />
      </Grid>
    </Grid>
  );
}
