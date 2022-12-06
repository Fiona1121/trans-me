import React, { useState } from "react";
import { useDispatch } from "react-redux";
import {
  deleteBlock,
  insertBlock,
  moveBlockDown,
  moveBlockUp,
  toggleBlockVisibility,
  updateBlockContent,
} from "../../../../slices/globalSlice";
import RichEditor from "./richEditor";
import {
  Box,
  ButtonGroup,
  CardContent,
  IconButton,
  Menu,
  MenuItem,
  Paper,
  Tooltip,
} from "@mui/material";

import NorthRoundedIcon from "@mui/icons-material/NorthRounded";
import SouthRoundedIcon from "@mui/icons-material/SouthRounded";
import AddRoundedIcon from "@mui/icons-material/AddRounded";
import DeleteRoundedIcon from "@mui/icons-material/DeleteRounded";
import ContentCopyRoundedIcon from "@mui/icons-material/ContentCopyRounded";
import VisibilityRoundedIcon from "@mui/icons-material/VisibilityRounded";
import VisibilityOffRoundedIcon from "@mui/icons-material/VisibilityOffRounded";
import MoreVertRoundedIcon from "@mui/icons-material/MoreVertRounded";
import { BlockAPI } from "../../../../api";
import RecordingOperator from "./recordingOperator";

export default function Block({ index, id, content, hidden }) {
  const dispatch = useDispatch();

  const [anchorEl, setAnchorEl] = React.useState(null);
  const [isOpened, setIsOpened] = useState(false);
  const [isRecordOpened, setIsRecordOpened] = useState(false);

  const BLOCK_TOOLBAR = [
    {
      title: "Move Up",
      icon: <NorthRoundedIcon />,
      onClick: () => {
        dispatch(moveBlockUp({ id }));
      },
    },
    {
      title: "Move Down",
      icon: <SouthRoundedIcon />,
      onClick: () => {
        dispatch(moveBlockDown({ id }));
      },
    },
    {
      title: "Add Block",
      icon: <AddRoundedIcon />,
      onClick: async () => {
        const response = await BlockAPI.postBlock({
          content: "",
          hidden: false,
        });
        dispatch(insertBlock({ index: index + 1, block: response.data.data }));
      },
    },
    {
      title: "Delete Block",
      icon: <DeleteRoundedIcon />,
      onClick: () => {
        // TODO: Call API to delete block
        dispatch(deleteBlock({ id }));
      },
    },
    {
      title: "Duplicate Block",
      icon: <ContentCopyRoundedIcon />,
      onClick: async () => {
        const response = await BlockAPI.postBlock({
          content: content,
          hidden: false,
        });
        dispatch(insertBlock({ index: index + 1, block: response.data.data }));
      },
    },
    {
      title: "Toggle Visibility",
      icon: hidden ? <VisibilityOffRoundedIcon /> : <VisibilityRoundedIcon />,
      onClick: () => {
        dispatch(toggleBlockVisibility({ id }));
      },
    },
  ];

  const MENU_OPTIONS = [
    {
      title: "Translate Block",
      onClick: () => {
        handleMenuClose();
      },
    },
    {
      title: "Generate Abstract",
      onClick: () => {
        handleMenuClose();
      },
    },
    {
      title: "Recording Conversion",
      onClick: () => {
        handleMenuClose();
        onOpenRecord();
      },
    },
  ];

  const handleMenuClick = (event) => {
    setAnchorEl(event.currentTarget);
    setIsOpened(!isOpened);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
    setIsOpened(false);
  };

  const handleBlockUpdate = (content) => {
    dispatch(updateBlockContent({ id, content }));
  };

  const onOpenRecord = () => {
    setIsRecordOpened(true);
  };

  return (
    <div>
      <Paper sx={{ width: "100%" }} variant="outlined">
        <CardContent>
          <RichEditor content={content} onUpdate={handleBlockUpdate} />
          <Box
            sx={{
              display: "flex",
              justifyContent: "flex-end",
            }}
          >
            <ButtonGroup variant="text">
              {BLOCK_TOOLBAR.map((button) => (
                <Tooltip key={button.title} title={button.title}>
                  <IconButton id={button.title} onClick={button.onClick}>
                    {button.icon}
                  </IconButton>
                </Tooltip>
              ))}
              <Tooltip title="More">
                <IconButton
                  id="menu-button"
                  onClick={handleMenuClick}
                  aria-controls={isOpened ? "menu" : undefined}
                  aria-haspopup="true"
                  aria-expanded={isOpened ? "true" : undefined}
                >
                  <MoreVertRoundedIcon />
                </IconButton>
              </Tooltip>
            </ButtonGroup>
          </Box>
        </CardContent>
        <Menu
          id="menu"
          anchorEl={anchorEl}
          open={isOpened}
          onClose={handleMenuClose}
          MenuListProps={{
            "aria-labelledby": "menu-button",
          }}
        >
          {MENU_OPTIONS.map((option) => (
            <MenuItem
              key={option.title}
              onClick={option.onClick}
              sx={{ backgroundColor: "rgba(255, 255, 255, 1)" }}
            >
              {option.title}
            </MenuItem>
          ))}
        </Menu>
      </Paper>

      <RecordingOperator
        index={index}
        open={isRecordOpened}
        setOpen={setIsRecordOpened}
      />
    </div>
  );
}
