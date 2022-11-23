import React, { useState } from "react";
import { useDispatch } from "react-redux";

import {
  Button,
  ButtonGroup,
  Card,
  Checkbox,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
} from "@mui/material";
import MergeRoundedIcon from "@mui/icons-material/MergeRounded";
import VisibilityOffRoundedIcon from "@mui/icons-material/VisibilityOffRounded";
import VisibilityRoundedIcon from "@mui/icons-material/VisibilityRounded";
import DeleteRoundedIcon from "@mui/icons-material/DeleteRounded";
import AddRoundedIcon from "@mui/icons-material/AddRounded";
import Block from "./block";
import {
  addEmptyBlock,
  deleteBlocks,
  hideBlocks,
  mergeBlocks,
  showBlocks,
} from "../../../slices/globalSlice";

export default function Board({ blocks }) {
  const dispatch = useDispatch();
  const [selected, setSelected] = useState([1]);

  const handleSelectAll = () => {
    setSelected((prevSelected) => {
      if (prevSelected.length === blocks.length) {
        return [];
      }
      return blocks.map((block) => block.id);
    });
  };

  const handleSelect = (id) => {
    if (selected.includes(id)) {
      setSelected(selected.filter((item) => item !== id));
    } else {
      setSelected([...selected, id]);
    }
  };

  const handleMergeSelected = () => {
    dispatch(mergeBlocks({ ids: selected }));
    setSelected([]);
  };

  const handleHideSelected = () => {
    dispatch(hideBlocks({ ids: selected }));
    setSelected([]);
  };

  const handleShowSelected = () => {
    dispatch(showBlocks({ ids: selected }));
    setSelected([]);
  };

  const handleDeleteSelected = () => {
    dispatch(deleteBlocks({ ids: selected }));
    setSelected([]);
  };

  const handleAddBlock = () => {
    dispatch(addEmptyBlock());
  };

  return (
    <Card>
      <List>
        <ListItem key="list-menu">
          <ListItemButton>
            <ListItemIcon>
              <Checkbox
                checked={selected.length === blocks.length}
                onChange={handleSelectAll}
              />
            </ListItemIcon>
            <ButtonGroup size="large">
              <Button
                startIcon={<MergeRoundedIcon />}
                onClick={handleMergeSelected}
              >
                Merge
              </Button>
              <Button
                startIcon={<VisibilityRoundedIcon />}
                onClick={handleShowSelected}
              >
                Show
              </Button>
              <Button
                startIcon={<VisibilityOffRoundedIcon />}
                onClick={handleHideSelected}
              >
                Hide
              </Button>

              <Button
                startIcon={<DeleteRoundedIcon />}
                onClick={handleDeleteSelected}
                color="secondary"
              >
                Delete
              </Button>
            </ButtonGroup>
          </ListItemButton>
        </ListItem>
        {blocks.map((block, index) => (
          <ListItem key={"list-item-" + block.id}>
            <ListItemButton>
              <ListItemIcon>
                <Checkbox
                  checked={selected.includes(block.id)}
                  onChange={() => handleSelect(block.id)}
                />
              </ListItemIcon>
              <Block
                key={block.id}
                index={index}
                id={block.id}
                content={block.content}
                isHidden={block.isHidden}
              />
            </ListItemButton>
          </ListItem>
        ))}
        <ListItem key="list-add" sx={{ justifyContent: "center" }}>
          <Button
            variant="contained"
            startIcon={<AddRoundedIcon />}
            onClick={handleAddBlock}
          >
            Add Block
          </Button>
        </ListItem>
      </List>
    </Card>
  );
}
