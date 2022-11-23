import { Card, CardContent } from "@mui/material";
import React from "react";
import Block from "./block";

export default function Board({ blocks }) {
  return (
    <Card>
      <CardContent sx={{ display: "flex", flexDirection: "column", gap: 3 }}>
        {blocks.map((block, index) => (
          <Block
            key={block.id}
            index={index}
            id={block.id}
            content={block.content}
            isHidden={block.isHidden}
          />
        ))}
      </CardContent>
    </Card>
  );
}
