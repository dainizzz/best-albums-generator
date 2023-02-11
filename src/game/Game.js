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
  setCurrentMatches,
}) => {
  const [currentMatchNum, setCurrentMatchNum] = useState(0);
  const [currentMatch, setCurrentMatch] = useState({});

  useEffect(() => {
    console.log("Time for the next match!");
    if (currentMatchNum < currentMatches.length) {
      setCurrentMatch(currentMatches[currentMatchNum]);
    } else {
      console.log("Time for the next round!");
      const nextRound = currentRound + 1;
      setCurrentRound(nextRound);
    }

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentMatchNum]);

  // Make a new match request when currentround changes
  useEffect(() => {
    axios
      .post(
        `${process.env.REACT_APP_BACKEND_URL}/users/${currentUser.id}/games/round/${currentRound}`
      )
      .then((response) => {
        console.log({ response });
        setCurrentMatches(response.data);
        console.log("We have new matches!");
        setCurrentMatchNum(0);
      })
      .catch((error) => {
        console.log(("Error: ", error));
      });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentRound]);

  // Chain w/ request to update match data
  const selectAlbum = async (albumId) => {
    await axios
      .patch(
        `${process.env.REACT_APP_BACKEND_URL}/users/${currentUser.id}/albums/${albumId}`
      )
      .then((response) => {
        const newAlbumsData = albumsList.map((currentAlbum) => {
          return currentAlbum.id !== response.data.id
            ? currentAlbum
            : { ...currentAlbum, userRank: response.data.userRank };
        });
        setAlbumsList(newAlbumsData);
        setWinner(albumId);
      })
      .catch((error) => {
        console.log(("Error:", error));
      });
  };

  const setWinner = (albumId) => {
    axios
      .patch(
        `${process.env.REACT_APP_BACKEND_URL}/users/${currentUser.id}/games/${currentRound}/${albumId}`
      )
      .then((response) => {
        const nextMatchNum = currentMatchNum + 1;
        setCurrentMatchNum(nextMatchNum);
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
          selectAlbum={selectAlbum}
          currentMatch={currentMatch}
        />
        <AlbumContainer
          albumId={currentMatch.album2}
          userId={currentUser.id}
          selectAlbum={selectAlbum}
          currentMatch={currentMatch}
        />
      </div>
    </>
  );
};

export default Game;
