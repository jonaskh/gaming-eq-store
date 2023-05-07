import React, { useState } from 'react';
import Navbar from '../Components/Navbar';
import '../css/LoginPage.css'; // import the CSS file
import { Link, useNavigate } from 'react-router-dom';

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleEmailChange = event => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = event => {
        setPassword(event.target.value);
    };

    const handleSubmit = async event => {
        event.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/authenticate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
            });

            if (!response.ok) {
                throw new Error('Authentication failed');
            }

            const data = await response.json();
            localStorage.setItem('jwt', data.jwt);
            navigate('/settings'); // Replace '/protected' with the path to your protected area
        } catch (error) {
            console.error('Error:', error);
            alert('Login failed. Please check your credentials and try again.');
        }
    };

    return (
        <div>
            <Navbar />
            <div className="center-container">
                <div className="login-form">
                    <h2>Login</h2>
                    <form onSubmit={handleSubmit}>
                        <div>
                            <label htmlFor="email">Email:</label>
                            <input
                                type="email"
                                id="email"
                                value={email}
                                onChange={handleEmailChange}
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
                    <p id={"registerText"}>
                        Don't have an account? <Link to="/register">Register here</Link>
                    </p>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;
