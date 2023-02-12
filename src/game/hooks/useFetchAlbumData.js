import axios from "axios";
import { useMemo } from "react";

const createAlbumUrl = (userId, albumId) =>
  `${process.env.REACT_APP_BACKEND_URL}/users/${userId}/albums/${albumId}`;

export const useFetchAlbumData = (currentMatch, userId, albumId) => {
  const albumData = useMemo(() => {
    axios
      .get(createAlbumUrl(userId, albumId))
      .then(({ data }) => {
        console.log("I am inside get album data request ;o");
        return data;
      })
      .catch((error) => {
        console.log("Error:", error);
      });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentMatch]);
  return { albumData };
};
