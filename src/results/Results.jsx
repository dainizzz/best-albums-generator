import CustomNameForm from "./components/CustomNameForm";
import Graphic from "./components/Graphic";
import GraphicStyleDropdown from "./components/GraphicStyleDropdown";
import TextBox from "./components/TextBox";
import DownloadButton from './components/DownloadButton';
import "./styling/results.css";
import axios from "axios";
import { useState, useEffect } from "react";

const Results = ({ userId, username }) => {
  const [finalAlbums, setFinalAlbums] = useState([]);
  const [displayName, setDisplayName] = useState(username);
  const [showName, setShowName] = useState(true);

  // Move to custom hook and pass in a parameter isTriggered
  const effectHelper = () => {
    // nest inside if is triggered
    axios
      .get(`${process.env.REACT_APP_BACKEND_URL}/users/${userId}/albums/ranked`)
      .then((response) => {
        console.log("I am in the final album data request!");
        setFinalAlbums(response.data);
      })
      .catch((error) => {
        console.log("Error:", error);
      });
  };

  useEffect(() => {
    effectHelper();
    // Add isTriggered to this
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
  // Map through data in the hook to get only artist & title

  const finalAlbumsCleaned = finalAlbums.map(
    (album) => `${album.title} - ${album.artist}`
  );

  return (
    <div className="results-container">
      <Graphic displayName={displayName} finalAlbums={finalAlbumsCleaned} />
      <TextBox finalAlbums={finalAlbumsCleaned} />
      <DownloadButton/>
      <div className="image-modifiers">
        <GraphicStyleDropdown />
        <CustomNameForm setDisplayName={setDisplayName} />
      </div>
    </div>
  );
};

export default Results;
