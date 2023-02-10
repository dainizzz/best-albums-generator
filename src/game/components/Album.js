import { useState, useEffect } from "react";
import axios from "axios";

const Album = ({ albumId, userId, currentRound, selectAlbum }) => {
  // get request using album id to get album data

  return (
    <>
      <img
        src="https://lastfm.freetls.fastly.net/i/u/300x300/022d1a3924a84c731520bda4e2f6e3d8.png"
        alt="placeholder for album"
      />
      <h2>Album Title Prop</h2>
      <h3>Artist Name Prop</h3>
      <button onClick={() => selectAlbum(albumId)}>Select</button>
    </>
  );
};

export default Album;
