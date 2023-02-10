import React from 'react';
import { useState } from "react";

import '../styling/NameForm.css'

// TODO: Figure out what the type of event is

type Props = {
    label: string
}

const NameForm = ({label}: Props) => {
    const [formField, setFormField] = useState({
        name: "",
    })

    const handleNameChange = (event: any) => {
        setFormField({
            name: event.target.value
        });
    }

    const onSubmit = (event: any) => {
        event.preventDefault();
        setFormField({name: ""})
    };

    return (
        <form onSubmit={onSubmit}>
            <label className="name-form-label">{label}</label>
            <input 
            type="text"
            onChange={handleNameChange}
            ></input>
            <input type="submit" value="Submit"/>
        </form>
    );
};
export default NameForm;