import { createTheme } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    primary: {
      main: "rgba(34, 34, 34, 1)",
      contrastText: "rgba(255, 255, 255, 1)",
    },
    secondary: {
      main: "rgba(234, 85, 80, 1)",
    },
    neutral: {
      main: "rgba(255, 255, 255, 1)",
    },
    background: {
      paper: "rgba(255, 255, 255, 0.5)",
    },
  },
  components: {
    MuiCssBaseline: {
      styleOverrides: {
        body: {
          height: "100vh",
        },
      },
    },
  },
  typography: {
    fontFamily: "Raleway",
  },
});

export default theme;
