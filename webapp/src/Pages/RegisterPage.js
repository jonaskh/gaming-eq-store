import React, { useState } from 'react';
import Navbar from '../Components/Navbar';
import '../css/RegisterPage.css';
import { useNavigate } from 'react-router-dom';

const RegisterPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [zipcode, setZipcode] = useState('');
    const [address, setAddress] = useState('');
    const navigate = useNavigate();

    const handleEmailChange = event => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = event => {
        setPassword(event.target.value);
    };

    const handleZipcodeChange = event => {
        setZipcode(event.target.value);
    };

    const handleAddressChange = event => {
        setAddress(event.target.value);
    };

    const handleSubmit = async event => {
        event.preventDefault();
        try {
            const response = await fetch('http://backend:8080/api/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password, zipcode, address }),
            });

            if (!response.ok) {
                throw new Error('Registration failed');
            }

            alert('Registration successful! You can now log in.');
            navigate('/login');
        } catch (error) {
            console.error('Error:', error);
            alert('Registration failed. Please check your input and try again.');
        }
    };

    return (
        <div>
            <Navbar />
            <div className="center-container">
                <div className="register-form">
                    <h2>Register</h2>
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
                        <div>
                            <label htmlFor="zipcode">Zip Code:</label>
                            <input
                                type="text"
                                id="zipcode"
                                value={zipcode}
                                onChange={handleZipcodeChange}
                                className="form-input"
                            />
                        </div>
                        <div>
                            <label htmlFor="address">Address:</label>
                            <input
                                type="text"
                                id="address"
                                value={address}
                                onChange={handleAddressChange}
                                className="form-input"
                            />
                        </div>
                        <button type="submit">Register</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default RegisterPage;
