const express = require("express");
const app = express();
const port = 8080;

app.get("/", (req, res) => {
  res.send("<h1>Welcome to Express!</h1>");
});

app.listen(port, () => {
  console.log(`TransMe app listening at http://localhost:${port}`);
});