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
import { useState } from "react";
import { Alert } from '@mui/material';
import MergeRoundedIcon from "@mui/icons-material/MergeRounded";
import Input from '@mui/material/Input';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import { Snackbar } from "@mui/material";
import { useNavigate } from "react-router-dom";

function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      <Link color="inherit" href="/">
        Trans-me
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}


export default function Login() {
  const navigate = useNavigate();
  const theme = createTheme();
  const [alert, setAlert] = useState({});
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [link, setLink] = useState(false);

  const handleUsername = (event) => {
    setUsername(event.target.value);
  };
  const handlePassword = (event) => {
    setPassword(event.target.value)
  };
  const handleValidation = (value) => {
    const reg = new RegExp("[A-Za-z0-9_]");
    return reg.test(value)
  };

  const handleLogin = (e) => {
    const validUsername = handleValidation(username);
    const validPassword = handleValidation(password);

    if (username === "" || password === "") {
      setAlert({
        open: true,
        severity: "warning",
        msg: "Username or password cannot be empty.",
      });
    
    }else if (!validUsername || !validPassword) {
      setAlert({
        open: true,
        severity: "warning",
        msg: "The format of the account number or password is wrong, please enter characters or numbers within 8~12 yards.",
      });

    }else {
      window.location.href = "/";
      setAlert({
        open: true,
        severity: "success",
        msg: "Log in successfully.",
      });
      
      /*
      if (判斷帳號密碼API){
        setAlert({
          open: true,
          severity: "success",
          msg: "Log in successfully.",
        });
        // 切換至main page，即從API取得該帳號資料

      }else{
        setAlert({
          open: true,
          severity: "warning",
          msg: "The entered account and password are invalid.",
        });
      }*/
    }
  }

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 15,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            border: '15px',
            border: 1
          }}
        >
          <Avatar sx={{ width: 100, height: 100, mt: -6.5, mb: 1 }} src={require('./img/SmileImg.png')} />
          
          <Typography component="h1" variant="h5">
            Welcome
          </Typography>

          <Box component="form" textAlign='center' onSubmit={handleLogin} noValidate sx={{ ml: 5, mr: 5, mb: 3, mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              autoFocus
              autoComplete="given-name"
              label="Username"
              id="username"
              value={username}
              onChange={handleUsername}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              autoComplete="current-password"
              label="Password"
              type="password"
              id="password"
              value = {password}
              onChange= {handlePassword}
            />
            <Button
              type="submit"
              size="large"
              variant="outlined"
              alignItems="center"
              justifyContent="center"
              sx={{ color: 'black', borderColor: 'black', mx: 5, mt: 4, mb: 2 }}

            >
              Login
             
            </Button>

            <Grid container >
              <Grid item sx={{ textAlign: 'center', ml: 11.5, fontSize: 12 }}>
                Don't have an account?
                <br />
                <Link href="register" variant="body2" sx={{ fontSize: 12 }}>
                  Register Now
                </Link>
              </Grid>
            </Grid>

          </Box>
        </Box>
        <Copyright sx={{ mt: 8, mb: 4 }} />
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