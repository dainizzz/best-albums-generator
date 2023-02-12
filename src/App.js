import Lottie from "react-lottie";
import axios from "axios";
import { useState, useEffect } from "react";
import Login from "./login/Login";
import Game from "./game/Game";
import Results from "./results/Results";
import animationData from "./lotties/circle-shape-morphing-animation.json";
import { useUserData } from "./hooks/useUserData";
import { useAlbumData } from "./hooks/useAlbumData";

const App = () => {
  const [currentRound, setCurrentRound] = useState(1);
  const { currentUser, addUserData } = useUserData();
  const {
    albumsList,
    setAlbumsList,
    currentMatches,
    setCurrentMatches,
    postAlbums,
  } = useAlbumData();

  useEffect(() => {
    if (currentUser.id) {
      postAlbums(currentUser.id);
      console.log("The user has been updated:", currentUser);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentUser]);

  // Animation Config
  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: animationData,
    rendererSettings: {
      preserveAspectRatio: "xMidYMid slice",
    },
  };

  return (
    <>
      {Object.keys(currentUser).length === 0 ? (
        <Login addUserData={addUserData} />
      ) : null}
      {/* Loading screen logic will go here, using the ternary statement currently above */}
      {albumsList.length === 0 && Object.keys(currentUser).length !== 0 ? (
        <Lottie options={defaultOptions} height={400} width={400} />
      ) : null}
      {currentMatches.length > 0 && currentRound !== 5 ? (
        <Game
          albumsList={albumsList}
          setAlbumsList={setAlbumsList}
          currentUser={currentUser}
          currentRound={currentRound}
          currentMatches={currentMatches}
          setCurrentRound={setCurrentRound}
          setCurrentMatches={setCurrentMatches}
        />
      ) : null}
      {currentRound === 5 ? (
        <Results userId={currentUser.id} username={currentUser.name} />
      ) : null}
    </>
  );
};

export default App;
