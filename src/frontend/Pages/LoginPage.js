import React, { useState } from 'react';
import Navbar from '../Components/Navbar';
import '../css/LoginPage.css'; // import the CSS file

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleUsernameChange = event => {
        setUsername(event.target.value);
    };

    const handlePasswordChange = event => {
        setPassword(event.target.value);
    };

    const handleSubmit = event => {
        event.preventDefault();
        // Perform login logic here
    };

    return (
        <div>
            <Navbar />
            <div className="center-container">
                <div className="login-form">
                    <h2>Login</h2>
                    <form onSubmit={handleSubmit}>
                        <div>
                            <label htmlFor="username">Username:</label>
                            <input
                                type="text"
                                id="username"
                                value={username}
                                onChange={handleUsernameChange}
                                className="form-input"
                            />
                        </div>
                        <div>
                            <label htmlFor="password">Password:</label>
                            <input
                                type="password"
                                id="password"
                                value={password}
                                onChange={handlePasswordChange}
                                className="form-input"
                            />
                        </div>
                        <button type="submit">Log in</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;