import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import LogoutButton from './LogoutButton';

const SettingsPage = () => {
    const navigate = useNavigate();
    const [userEmail, setUserEmail] = useState('');

    useEffect(() => {
        // Check if the user is authenticated (e.g., by checking the presence of a JWT token)
        const isAuthenticated = !!localStorage.getItem('jwt');

        if (!isAuthenticated) {
            // User is not authenticated, redirect to login page
            navigate.push('/login');
        } else {
            // User is authenticated, fetch the user's email from the server or JWT token
            const email = 'user@example.com'; // Replace with actual logic to fetch user email
            setUserEmail(email);
        }
    }, [navigate]);

    const handleLogout = () => {
        // Clear the JWT token from local storage
        localStorage.removeItem('jwt');
        // Redirect the user to the login page or any other desired page
        navigate.push('/login');
    };

    return (
        <div>
            <h2>Settings</h2>
            {userEmail && <p>Email: {userEmail}</p>}
            <LogoutButton onLogout={handleLogout} />
        </div>
    );
};

export default SettingsPage;
