import React from "react";
import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";
import { selectGlobal } from "../../slices/globalSlice";
import { selectSession } from "../../slices/sessionSlice";
import Board from "./board";

export default function Main() {
  const { username, expirationTime } = useSelector(selectSession);
  const { blocks } = useSelector(selectGlobal);
  const isLoggedIn = username && expirationTime > Date.now();

  if (!isLoggedIn) {
    return <Navigate to={"/login"} />;
  }
  return (
    <div>
      <Board blocks={blocks} />
    </div>
  );
}
