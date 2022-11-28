import React from "react";
import { useSelector } from "react-redux";
import { selectGlobal } from "../../../slices/globalSlice";

import {
  Accordion,
  AccordionDetails,
  AccordionSummary,
  CardContent,
  CircularProgress,
  Grid,
  IconButton,
  ListSubheader,
  Paper,
  Tooltip,
  Typography,
} from "@mui/material";
import ExpandMoreRoundedIcon from "@mui/icons-material/ExpandMoreRounded";
import RefreshRoundedIcon from "@mui/icons-material/RefreshRounded";

export default function Term() {
  const { blocks } = useSelector(selectGlobal);
  const [isLoading, setIsLoading] = React.useState(false);
  const [terms, setTerms] = React.useState([
    { name: "Test", details: "This is the detail of test." },
    { name: "Test2", details: "This is the detail of test2." },
    { name: "Test3", details: "This is the detail of test3." },
  ]);

  const fetchTerms = () => {
    setIsLoading(true);
    // const stringContent = blocks.map((block) => block.content).join("");
    // const text = stringContent.replace(/<[^>]+>/g, "");
    // TODO: fetch terms from server
    setIsLoading(false);
  };

  const handleRefresh = () => {
    fetchTerms();
  };

  // Initialize terms
  React.useEffect(() => {
    fetchTerms();
  }, []);

  return (
    <Paper
      variant="outlined"
      sx={{ maxWidth: 300, height: "85vh", overflow: "scroll" }}
    >
      <ListSubheader
        sx={{
          pt: 2,
          pb: 1,
          mb: 1,
          zIndex: 950,
          backgroundColor: "rgba(255, 255, 255, 1)",
          borderBottom: "1px solid rgba(0, 0, 0, 0.12)",
        }}
      >
        <Grid
          container
          direction="row"
          justifyContent="space-between"
          alignItems="stretch"
        >
          <Grid item>
            <Typography
              gutterBottom
              variant="h5"
              sx={{
                fontFamily: "Playfair Display",
                color: "rgba(34, 34, 34, 1)",
                fontWeight: 700,
                mt: 1,
              }}
            >
              Terms
            </Typography>
          </Grid>
          <Grid item>
            <Tooltip title="Refresh">
              <IconButton
                size="small"
                onClick={handleRefresh}
                disabled={isLoading}
              >
                <RefreshRoundedIcon />
              </IconButton>
            </Tooltip>
          </Grid>
        </Grid>
      </ListSubheader>
      <CardContent sx={{ pt: 1 }}>
        <div>
          {isLoading ? (
            <Grid
              container
              direction="column"
              justifyContent="center"
              alignItems="center"
              spacing={1}
            >
              <Grid item>
                <CircularProgress />
              </Grid>
              <Grid item>
                <Typography variant="body2">Loading...</Typography>
              </Grid>
            </Grid>
          ) : (
            terms.map((term, index) => (
              <Accordion
                key={"term-" + index}
                elevation={0}
                sx={{ border: "1px solid rgba(0, 0, 0, 0.12)" }}
              >
                <AccordionSummary
                  expandIcon={<ExpandMoreRoundedIcon />}
                  sx={{ margin: 0 }}
                >
                  <Typography variant="body1" sx={{ fontWeight: 500 }}>
                    {term.name}
                  </Typography>
                </AccordionSummary>
                <AccordionDetails>
                  <Typography variant="body2">{term.details}</Typography>
                </AccordionDetails>
              </Accordion>
            ))
          )}
        </div>
      </CardContent>
    </Paper>
  );
}
