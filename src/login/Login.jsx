import React from "react";
import NameForm from "./NameForm";
import "./styling/login.css";

export const displayContent = {
  title: "2022 Best Albums Generator",
  label: "Enter your Last.fm username to continue",
};

export const Login = ({ addUserData }) => {
  return (
    <div className="container">
      <h1 className="homepage-title">{displayContent.title}</h1>
      <NameForm label={displayContent.label} addUserData={addUserData} />
    </div>
  );
};
export default Login;
