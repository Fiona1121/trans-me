import React from "react";
import { useSelector } from "react-redux";
import { selectGlobal } from "../../../slices/globalSlice";

import {
  Accordion,
  AccordionDetails,
  AccordionSummary,
  Card,
  CardContent,
  CircularProgress,
  Grid,
  IconButton,
  Toolbar,
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
  ]);

  const fetchTerms = () => {
    const stringContent = blocks.map((block) => block.content).join("");
    const text = stringContent.replace(/<[^>]+>/g, "");
    // TODO: fetch terms from server
  };

  const handleRefresh = () => {
    setIsLoading(true);
    fetchTerms();
    setIsLoading(false);
  };

  // Initialize terms
  React.useEffect(() => {
    fetchTerms();
  }, []);

  return (
    <Card sx={{ maxWidth: 300, height: "90vh" }}>
      <Toolbar sx={{ paddingLeft: 0, paddingRight: 0 }}>
        <Grid
          container
          direction="row"
          justifyContent="space-between"
          alignItems="stretch"
        >
          <Grid item>
            <Typography gutterBottom variant="h6">
              Technical Term
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
      </Toolbar>
      <CardContent>
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
              <Accordion key={"term-" + index}>
                <AccordionSummary expandIcon={<ExpandMoreRoundedIcon />}>
                  <Typography variant="body1">{term.name}</Typography>
                </AccordionSummary>
                <AccordionDetails>
                  <Typography variant="body2">{term.details}</Typography>
                </AccordionDetails>
              </Accordion>
            ))
          )}
        </div>
      </CardContent>
    </Card>
  );
}
