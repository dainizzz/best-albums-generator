import CustomNameForm from "./components/CustomNameForm";
import Graphic from "./components/Graphic";
import GraphicStyleDropdown from "./components/GraphicStyleDropdown";
import TextBox from "./components/TextBox";
import "./styling/results.css";
import axios from "axios";
import { useState, useEffect } from "react";

const Results = ({ userId, username }) => {
  const [finalAlbums, setFinalAlbums] = useState([]);
  const [displayName, setDisplayName] = useState(username);
  const [graphicStyle, setGraphicStyle] = useState("cyber");
  const [hideName, setHideName] = useState(false);

  const effectHelper = () => {
  axios
    .get(`${process.env.REACT_APP_BACKEND_URL}/users/${userId}/albums/ranked`)
    .then(({data}) => {
      console.log("I am in the final album data request!");
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
      <Graphic displayName={displayName} finalAlbums={finalAlbums} graphicStyle={graphicStyle}/>
      <TextBox finalAlbums={finalAlbums} />
      <div className="image-modifiers">
        <GraphicStyleDropdown setGraphicStyle={setGraphicStyle} />
        <CustomNameForm setDisplayName={setDisplayName} username={username} setHideName={setHideName} hideName={hideName} />
      </div>
    </div>
  );
};

export default Results;
