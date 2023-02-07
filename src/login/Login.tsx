import React from 'react';
import NameForm from '../ui/components/NameForm'
import './styling/login.css'

const displayContent = {
    title: '2022 Best Albums Generator',
    label: 'Enter your Last.fm username to continue',
}


const Login = () => {
    return (
        <div className="container">
            <h1>{displayContent.title}</h1>
            <NameForm label={displayContent.label}/>
        </div>
    )
}
export default Login;