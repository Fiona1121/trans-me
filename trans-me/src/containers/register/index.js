import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import {useState} from "react";
import { Alert } from '@mui/material';
import { Snackbar } from "@mui/material";

function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      <Link color="inherit" href="register">
        Trans-me
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}



export default function Register() {
  const theme = createTheme();
  const [alert, setAlert] = useState({});
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [repeat, setRepeat] = useState("");

  const handleUsername = (event) => {
    setUsername(event.target.value);
  };
  const handlePassword = (event) => {
    setPassword(event.target.value)
  };
  const handleRepeat = (event) => {
    setRepeat(event.target.value)
  };
  const handleValidation = (value) => {
    const reg = new RegExp("[A-Za-z0-9_]");
    return reg.test(value)
  };

  const handleRegister = (event) => {
    const validUsername = handleValidation(username);
    const validPassword = handleValidation(password);
    const validRepeat = handleValidation(repeat);

    if (username === "" || password === "" || repeat === "") {
      setAlert({
        open: true,
        severity: "warning",
        msg: "Username, password, or repeat password cannot be empty.",
      });
    
    }else if (!validUsername || !validPassword || !validRepeat) {
      setAlert({
        open: true,
        severity: "warning",
        msg: "The format of the username or password is wrong, please enter characters or numbers within 6~12 yards.",
      });
    }else if (password !== repeat){
      setAlert({
        open: true,
        severity: "warning",
        msg: "The two entered passwords do not match.",
      });
    }else {
      window.location.href = "/login";
      setAlert({
        open: true,
        severity: "success",
        msg: "Register successfully.",
      });
      /*
      if (判斷註冊API){
        setAlert({
          open: true,
          severity: "success",
          msg: "Register" successfully.",
        });
        // 切換至login page

      }else{
        setAlert({
          open: true,
          severity: "warning",
          msg: "The entered username is already taken.",
        });
      }*/
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 10,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            border: '15px',
            border: 1
          }}
        >
          <Typography component="h1" variant="h5" sx={{mt: 5 }}>
            Create Account
          </Typography>

          <Box component="form" textAlign='center' onSubmit={handleRegister} noValidate   sx={{ ml: 5, mr: 5, mb: 3, mt: 5 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              autoFocus
              label="Username"
              autoComplete="given-name"
              name="username"
              id="username"
              value = {username}
              onChange= {handleUsername}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              id= "password"
              label= "Password"
              name="password"
              type= "password"
              autoComplete="new-password"
              value = {password}
              onChange= {handlePassword}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name= "repeatPassword"
              label= "Repeat Password"
              type= "password"
              id="repeatPassword"
              autoComplete="current-password"
              value = {repeat}
              onChange= {handleRepeat}
            />
            <Button
              type="submit"
              size="large"
              variant="outlined"
              alignItems="center"
              justifyContent="center"
              sx={{ color: 'black', borderColor: 'black', mx: 5, mt: 4, mb: 2 }}
            >
              Register
            </Button>

            <Grid container>
            <Grid item sx={{ ml: 11, fontSize: 12 }}>
                Already have an account? 
                <br />
                <Link href="login" variant="body2" sx={{ fontSize: 12 }}>
                  Log in
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
        <Copyright sx={{ mt: 5 }} />
      </Container>
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
    </ThemeProvider>
  );
}