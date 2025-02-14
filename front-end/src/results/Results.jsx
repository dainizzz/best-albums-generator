import CustomNameForm from "./components/CustomNameForm";
import Graphic from "./components/Graphic";
import GraphicStyleDropdown from "./components/GraphicStyleDropdown";
import TextBox from "./components/TextBox";
import "./styling/results.css";
import axios from "axios";
import { useState, useEffect } from "react";
import DownloadButton from "./components/DownloadButton";

const Results = ({ userId, username }) => {
  const [finalAlbums, setFinalAlbums] = useState([]);
  const [displayName, setDisplayName] = useState(username);
  const [graphicStyle, setGraphicStyle] = useState("blobs");
  const [hideName, setHideName] = useState(false);

  const effectHelper = () => {
  axios
    .get(`${process.env.REACT_APP_BACKEND_URL}/users/${userId}/albums/ranked`)
    .then(({data}) => {
      const finalAlbumsCleaned = data.map(
        (album, index) => `${index + 1}. ${album.title} - ${album.artist}`
      );
      console.log(finalAlbumsCleaned);
      setFinalAlbums(finalAlbumsCleaned);
    })
    .catch((error) => {
      console.log("Error:", error);
    });
};

useEffect(() => {
  effectHelper();
  // eslint-disable-next-line react-hooks/exhaustive-deps
}, []);


  return (
    <div className="results-container">
      <Graphic displayName={displayName} finalAlbums={finalAlbums} graphicStyle={graphicStyle} hideName={hideName}/>
      <TextBox finalAlbums={finalAlbums} />
      <div className="image-modifiers">
        <GraphicStyleDropdown setGraphicStyle={setGraphicStyle} />
        <CustomNameForm setDisplayName={setDisplayName} username={username} setHideName={setHideName} hideName={hideName} />
        <DownloadButton/>
      </div>
    </div>
  );
};

export default Results;
