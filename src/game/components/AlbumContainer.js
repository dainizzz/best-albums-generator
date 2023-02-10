import Album from "./Album";
import axios from "axios";
import { useState, useEffect } from "react";
import "../styling/AlbumContainer.css";

const AlbumContainer = ({ albumId, userId, currentRound, selectAlbum }) => {
  return (
    <div className="album-container">
      <Album
        albumId={albumId}
        userId={userId}
        currentRound={currentRound}
        selectAlbum={selectAlbum}
      />
    </div>
  );
};
export default AlbumContainer;
