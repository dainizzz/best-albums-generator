import axios from "axios";
import React from "react";
import { useState, useEffect } from "react";
import Login from "./login/Login";
import Game from "./game/Game";

const App = () => {
  const [usersList, setUsersList] = useState([]);
  const [currentUser, setCurrentUser] = useState({});
  const [albumsList, setAlbumsList] = useState([]);
  const [currentRound, setCurrentRound] = useState(0);
  const [currentMatches, setCurrentMatches] = useState([]);

  //   const addUserData = (newUser) => {
  //     axios
  //       .post(`${process.env.REACT_APP_BACKEND_URL}/users`, newUser)
  //       .then((response) => {
  //         console.log(response.data);
  //         setCurrentUser(response.data);
  //       })
  //       .catch((error) => {
  //         console.log(("Error: ", error));
  //       });
  //   };

  // V3: async
  const addUserData = async (newUser) => {
    await axios
      .post(`${process.env.REACT_APP_BACKEND_URL}/users`, newUser)
      .then((response) => {
        console.log({ response });
        const newUsersList = [...usersList];
        newUsersList.push(response.data);
        setUsersList(newUsersList);
        setCurrentUser(response.data);
        const userId = response.data.id;
        postAlbums(userId);
      })
      .catch((error) => {
        console.log(("Error: ", error));
      });
  };

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

  return (
    <>
      {albumsList.length > 0 ? null : <Login addUserData={addUserData} />}
      {albumsList.length > 0 ? (
        <Game
          albumsList={albumsList}
          setAlbumsList={setAlbumsList}
          currentUser={currentUser}
          currentRound={currentRound}
          currentMatches={currentMatches}
          setCurrentRound={setCurrentRound}
        />
      ) : null}
    </>
  );
};

export default App;
