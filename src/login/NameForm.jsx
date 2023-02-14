import { useState } from "react";

import "./styling/nameForm.css";

const NameForm = ({ label, addUserData }) => {
  const [formName, setFormName] = useState("");

  const handleNameChange = (event) => {
    console.log(event.target.value);
    setFormName(event.target.value);
  };

  const onSubmit = (event) => {
    event.preventDefault();

    addUserData({
      name: formName,
    });
    console.log({ formName });

    setFormName("");
  };

  return (
    <form onSubmit={onSubmit}>
      <label className="name-form-label">{label}</label>
      <input className="name-form-text" type="text" onChange={handleNameChange}></input>
      <input type="submit" value="Submit" />
    </form>
  );
};
export default NameForm;
