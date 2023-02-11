import Lottie from "react-lottie";
import axios from "axios";
import { useState, useEffect } from "react";
import Login from "./login/Login";
import Game from "./game/Game";
import Results from "./results/Results";
import animationData from "./lotties/circle-shape-morphing-animation.json";

const App = () => {
  const [usersList, setUsersList] = useState([]);
  const [currentUser, setCurrentUser] = useState({});
  const [albumsList, setAlbumsList] = useState([]);
  const [currentRound, setCurrentRound] = useState(1);
  const [currentMatches, setCurrentMatches] = useState([]);

  useEffect(() => {
    console.log("The user has been updated:");
    console.log({ currentUser });
    postAlbums(currentUser.id);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentUser]);

  const addUserData = (newUser) => {
    axios
      .post(`${process.env.REACT_APP_BACKEND_URL}/users`, newUser)
      .then((response) => {
        console.log(response.data);
        const newUsersList = [...usersList];
        newUsersList.push(response.data);
        setUsersList(newUsersList);
        setCurrentUser(response.data);
        console.log({ currentUser });
        console.log({ usersList });
      })
      .catch((error) => {
        console.log(("Error: ", error));
      });
  };

  // V3: async; now utilizing in tandem w/ useEffect instead lol
  // const addUserData = async (newUser) => {
  //   await axios
  //     .post(`${process.env.REACT_APP_BACKEND_URL}/users`, newUser)
  //     .then((response) => {
  //       console.log({ response });
  //       const newUsersList = [...usersList];
  //       newUsersList.push(response.data);
  //       setUsersList(newUsersList);
  //       setCurrentUser(response.data);
  //       const userId = response.data.id;
  //       postAlbums(userId);
  //     })
  //     .catch((error) => {
  //       console.log(("Error: ", error));
  //     });
  // };

  const postAlbums = async (userId) => {
    await axios
      .post(`${process.env.REACT_APP_BACKEND_URL}/users/${userId}/albums/2022`)
      .then((response) => {
        console.log({ response });
        setAlbumsList(response.data);
        const currentUserId = userId;
        postMatches(currentUserId);
      })
      .catch((error) => {
        console.log(("Error: ", error));
      });
  };

  const postMatches = async (userId) => {
    await axios
      .post(
        `${process.env.REACT_APP_BACKEND_URL}/users/${userId}/games/round/1`
      )
      .then((response) => {
        console.log({ response });
        setCurrentMatches(response.data);
        console.log("Hooray!");
      })
      .catch((error) => {
        console.log(("Error: ", error));
      });
  };

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
