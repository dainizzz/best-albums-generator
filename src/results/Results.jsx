import CustomNameForm from "./components/CustomNameForm";
import Graphic from "./components/Graphic";
import GraphicStyleDropdown from "./components/GraphicStyleDropdown";
import TextBox from "./components/TextBox";
import DownloadButton from './components/DownloadButton';
import "./styling/results.css";
import axios from "axios";
import { useState, useEffect } from "react";
import data from "../sampleAlbums.json";

// ------------------------------------------------------------------
const finalAlbumsCleaned = data.map(
  (album, index) => `${index + 1}. ${album.title} - ${album.artist}`
);
console.log(finalAlbumsCleaned);

// TODO: set back to this (original) after testing:
// const Results = ({ userId, username }) => {
const Results = () => {
  // TODO: Set state back to empty array
  // const [finalAlbums, setFinalAlbums] = useState([]);
  const [testingAlbums, setTestingAlbums] = useState(finalAlbumsCleaned);
  // TODO: take out username here
  const username = "inolvidable-"
  const [displayName, setDisplayName] = useState(username);
  const [graphicStyle, setGraphicStyle] = useState("cyber");
  const [hideName, setHideName] = useState(false);

  return (
    <div className="results-container">
      <Graphic displayName={displayName} finalAlbumsCleaned={finalAlbumsCleaned} graphicStyle={graphicStyle}/>
      <TextBox finalAlbumsCleaned={testingAlbums} />
      <DownloadButton/>
      <div className="image-modifiers">
        <GraphicStyleDropdown setGraphicStyle={setGraphicStyle} />
        <CustomNameForm setDisplayName={setDisplayName} username={username} setHideName={setHideName} hideName={hideName} />
      </div>
    </div>
  );
};

export default Results;
