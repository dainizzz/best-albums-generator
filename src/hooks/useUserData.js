import axios from "axios";
import { useState } from "react";

export const useUserData = () => {
  const [usersList, setUsersList] = useState([]);
  const [currentUser, setCurrentUser] = useState({});

  const addUserData = (newUser) => {
    axios
      .post(`${process.env.REACT_APP_BACKEND_URL}/users`, newUser)
      .then(({ data }) => {
        setUsersList((prev) => [...prev, data]);
        setCurrentUser(data);
        console.log("This is the current user:", { currentUser });
      })
      .catch((error) => {
        console.log(("Error: ", error));
      });
  };

  return {
    usersList,
    setUsersList,
    currentUser,
    setCurrentUser,
    addUserData,
  };
};
