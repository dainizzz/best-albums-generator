import React from "react";
import { useState } from "react";

import "../styling/NameForm.css";

// type Props = {
//     label: string
// }

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
      <input type="text" onChange={handleNameChange}></input>
      <input type="submit" value="Submit" />
    </form>
  );
};
export default NameForm;
