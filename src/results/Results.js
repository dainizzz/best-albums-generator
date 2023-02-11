import NameForm from "../ui/components/NameForm";
import Graphic from "./components/Graphic";
import GraphicStyleDropdown from "./components/GraphicStyleDropdown";
import TextBox from "./components/TextBox";
import "./styling/results.css";
import axios from "axios";
import { useState, useEffect } from "react";

const displayContent = {
  label: "Use custom name",
};

const Results = ({ userId, username }) => {
  const [finalAlbums, setFinalAlbums] = useState([]);
  const [displayName, setDisplayName] = useState(username);
  const [showName, setShowName] = useState(true);

  const effectHelper = () => {
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
  }, []);

  return (
    <div className="results-container">
      <Graphic finalAlbums={finalAlbums} />
      <TextBox finalAlbums={finalAlbums} />
      <div className="image-modifiers">
        <GraphicStyleDropdown />
        <NameForm label={displayContent.label} />
      </div>
    </div>
  );
};

export default Results;
