import AlbumContainer from "./components/AlbumContainer";
import axios from "axios";
import { useState, useEffect } from "react";

import "./styling/game.css";

const Game = ({
  albumsList,
  currentUser,
  currentRound,
  currentMatches,
  setAlbumsList,
  setCurrentRound,
}) => {
  const [currentMatchNum, setCurrentMatchNum] = useState(0);
  const [currentMatch, setCurrentMatch] = useState({});

  // Chain w/ request to update match data
  const selectAlbum = (albumId) => {
    axios
      .patch(
        `${process.env.REACT_APP_BACKEND_URL}/boards/users/${currentUser.id}/albums/${albumId}`,
        { albumId }
      )
      .then((response) => {
        const newAlbumsData = albumsList.map((currentAlbum) => {
          return currentAlbum.id !== response.data.id
            ? currentAlbum
            : { ...currentAlbum, userRank: response.data.userRank };
        });
        setAlbumsList(newAlbumsData);
      })
      .catch((error) => {
        console.log(("Error:", error));
      });
  };

  return (
    <>
      {/* Instructions will be a hide/show element that pop up after you click an icon */}
      <h3 className="instructions">
        Click on the button below the album you prefer.
      </h3>
      <div className="game-container">
        <AlbumContainer
          albumId={currentMatch.album1}
          userId={currentUser.id}
          currentRound={currentRound}
          selectAlbum={selectAlbum}
        />
        <AlbumContainer
          albumId={currentMatch.album2}
          userId={currentUser.id}
          currentRound={currentRound}
          selectAlbum={selectAlbum}
        />
      </div>
    </>
  );
};

export default Game;
