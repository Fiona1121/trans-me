import React from "react";
import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";
import { selectSession } from "../../slices/sessionSlice";

export default function Main() {
  const { username, expirationTime } = useSelector(selectSession);
  const isLoggedIn = username && expirationTime > Date.now();

  if (!isLoggedIn) {
    return <Navigate to={"/login"} />;
  }
  return <div>Main</div>;
}
