import axios from "axios";
import { useState, useEffect } from "react";

export const useCurrentMatchQuery = (
  currentUserId,
  currentRound,
  currentMatches,
  setCurrentRound,
  setCurrentMatches,
  albumsList,
  setAlbumsList
) => {
  const [currentMatchNum, setCurrentMatchNum] = useState(0);
  const [currentMatch, setCurrentMatch] = useState({});

  // Sets up next match and next round
  useEffect(() => {
    if (currentMatchNum < currentMatches.length) {
      setCurrentMatch(currentMatches[currentMatchNum]);
    } else {
      const nextRound = currentRound + 1;
      setCurrentRound(nextRound);
    }

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentMatchNum]);

  // Make a new match request when current round changes
  useEffect(() => {
    axios
      .post(
        `${process.env.REACT_APP_BACKEND_URL}/users/${currentUserId}/games/round/${currentRound}`
      )
      .then(({ data }) => {
        console.log({ data });
        setCurrentMatches(data);
        setCurrentMatchNum(0);
      })
      .catch((error) => {
        console.log(("Error: ", error));
      });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentRound]);

  const setWinner = (albumId) => {
    axios
      .patch(
        `${process.env.REACT_APP_BACKEND_URL}/users/${currentUserId}/games/${currentRound}/${albumId}`
      )
      .then((response) => {
        const nextMatchNum = currentMatchNum + 1;
        setCurrentMatchNum(nextMatchNum);
      });
  };

  // Chain w/ request to update match data
  const selectAlbum = async (albumId) => {
    await axios
      .patch(
        `${process.env.REACT_APP_BACKEND_URL}/users/${currentUserId}/albums/${albumId}`
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

  return {
    currentMatchNum,
    setCurrentMatchNum,
    currentMatch,
    setCurrentMatch,
    selectAlbum,
  };
};