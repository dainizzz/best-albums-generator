import axios from "axios";
import { useState, useEffect } from "react";

export const useAlbumData = () => {
  const [albumsList, setAlbumsList] = useState([]);
  const [currentMatches, setCurrentMatches] = useState([]);
  const [isLoading, setLoading] = useState(false);
  const [isError, setError] = useState(false);

  console.log({ isLoading });
  const postAlbums = async (userId) => {
    setLoading(true);
    await axios
      .post(`${process.env.REACT_APP_BACKEND_URL}/users/${userId}/albums/2022`)
      .then(({ data }) => {
        console.log({ data });
        setAlbumsList(data);
        postMatches(userId);
      })
      .catch((error) => {
        setError(true);
        setLoading(false);
        console.log(("Error: ", error));
      });
  };

  const postMatches = async (userId) => {
    await axios
      .post(
        `${process.env.REACT_APP_BACKEND_URL}/users/${userId}/games/round/1`
      )
      .then(({ data }) => {
        console.log({ data });
        setCurrentMatches(data);
        console.log("Hooray!");
        setLoading(false);
      })
      .catch((error) => {
        setError(true);
        setLoading(false);
        console.log(("Error: ", error));
      });
  };

  return {
    albumsList,
    setAlbumsList,
    currentMatches,
    setCurrentMatches,
    postAlbums,
    isLoading,
    isError,
  };
};
