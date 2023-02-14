import axios from "axios";
import { useState } from "react";

export const useAlbumData = () => {
  const [albumsList, setAlbumsList] = useState([]);
  const [currentMatches, setCurrentMatches] = useState([]);
  const [isLoading, setLoading] = useState(false);
  const [isError, setError] = useState(false);

  const postAlbums = async (userId) => {
    await axios
      .post(`${process.env.REACT_APP_BACKEND_URL}/users/${userId}/albums/2022`)
      .then(({ data }) => {
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
    if (userId) {
      await axios
        .post(
          `${process.env.REACT_APP_BACKEND_URL}/users/${userId}/games/round/1`
        )
        .then(({ data }) => {
          setCurrentMatches(data);
          setLoading(false);
        })
        .catch((error) => {
          setError(true);
          setLoading(false);
          console.log(("Error: ", error));
        });
    }
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
