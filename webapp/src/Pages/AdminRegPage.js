import React, { useState } from 'react';
import Navbar from '../Components/Navbar';
import '../css/RegisterPage.css';
import { useNavigate } from 'react-router-dom';

const AdminRegPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [repeatPassword, setRepeatPassword] = useState('');
  const [zipcode, setZipcode] = useState('');
  const [address, setAddress] = useState('');
  const [verificationCode, setVerificationCode] = useState('');
  const navigate = useNavigate();

  const handleEmailChange = event => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = event => {
    setPassword(event.target.value);
  };

  const handleRepeatPasswordChange = event => {
    setRepeatPassword(event.target.value);
  };

  const handleZipcodeChange = event => {
    setZipcode(event.target.value);
  };

  const handleAddressChange = event => {
    setAddress(event.target.value);
  };

  const handleVerificationCodeChange = event => {
    setVerificationCode(event.target.value);
  };

  const handleSubmit = async event => {
    event.preventDefault();
    if (verificationCode !== 'admin') {
      alert('Invalid verification code. Please try again.');
      return;
    }
    if (password !== repeatPassword) {
      alert('Passwords do not match. Please try again.');
      return;
    }
    try {
      const response = await fetch('http://localhost:8080/api/registerAdmin', {
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

  const handleCancel = () => {
    navigate('/login');
  };

  return (
    <div>
      <Navbar />
      <div className="center-container">
        <div className="register-form">
          <h2 class="registerTitle">Create an Admin Account</h2>
          <form onSubmit={handleSubmit}>
            <div className="input-container">
              <img src={process.env.PUBLIC_URL + '/email.png'} className="icon" alt="Email Icon" />
              <input
                type="email"
                id="email"
                value={email}
                onChange={handleEmailChange}
                className="form-input"
                placeholder="Your Email"
              />
            </div>
            <div className="input-container">
              <img src={process.env.PUBLIC_URL + '/password.png'} className="icon" alt="Password Icon" />
              <input
                type="password"
                id="password"
                value={password}
                onChange={handlePasswordChange}
                className="form-input"
                placeholder="Password"
              />
            </div>
            <div className="input-container">
              <img src={process.env.PUBLIC_URL + '/password.png'} className="icon" alt="Password Icon" />
              <input
                type="password"
                id="repeatPassword"
                value={repeatPassword}
                onChange={handleRepeatPasswordChange}
                className="form-input"
                placeholder="Repeat Password"
              />
            </div>
            <div className="input-container">
              <img src={process.env.PUBLIC_URL + '/Zip.png'} className="icon" alt="Zipcode Icon" />
              <input
                type="text"
                id="zipcode"
                value={zipcode}
                onChange={handleZipcodeChange}
                className="form-input"
                placeholder="Zip Code"
              />
            </div>
            <div className="input-container">
              <img src={process.env.PUBLIC_URL + '/location.png'} className="icon" alt="Address Icon" />
              <input
                type="text"
                id="address"
                value={address}
                onChange={handleAddressChange}
                className="form-input"
                placeholder="Address"
              />
            </div>
            <div className="input-container">
              <img src={process.env.PUBLIC_URL + '/verified.png'} className="icon" alt="Verification Icon" />
              <input
                type="password"
                id="verificationCode"
                value={verificationCode}
                onChange={handleVerificationCodeChange}
                className="form-input"
                placeholder="Verification Code"
              />
            </div>
            <div className="button-container">
              <button type="submit" className="register-button">
                Register
              </button>
              <button type="button" className="cancel-button" onClick={handleCancel}>
                Cancel
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AdminRegPage;
