import { useState, useEffect } from "react";
import axios from "axios";

const Album = ({ albumId, userId, selectAlbum, currentMatch }) => {
  const [albumData, setAlbumData] = useState({});

  // useEffect triggers get request using album id to get album data; this is happening too soon :(
  useEffect(() => {
    axios
      .get(
        `${process.env.REACT_APP_BACKEND_URL}/users/${userId}/albums/${albumId}`
      )
      .then((response) => {
        console.log("I am inside get album data request ;o");
        setAlbumData(response.data);
      })
      .catch((error) => {
        console.log("Error:", error);
      });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentMatch]);

  return (
    <>
      <img src={albumData.imgLink} alt="album cover" />
      <h2>{albumData.title}</h2>
      <h3>{albumData.artist}</h3>
      <button onClick={() => selectAlbum(albumId)}>Select</button>
    </>
  );
};

export default Album;
