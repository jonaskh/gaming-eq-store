import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from '../Components/Navbar';


const SettingsPage = () => {
    const navigate = useNavigate();
    const [userEmail, setUserEmail] = useState('');

    useEffect(() => {
        const isAuthenticated = !!localStorage.getItem('jwt');

        if (!isAuthenticated) {
            navigate('/login');
        } else {
            const email = 'user@example.com';
            setUserEmail(email);
        }
    }, [navigate]);

    const handleLogout = () => {
        localStorage.removeItem('jwt');
        navigate('/login');
    };

    return (
        <div>
            <Navbar />
            <div>
            <h2>Settings</h2>
            {userEmail && <p>Email: {userEmail}</p>}
            <button onClick={handleLogout}>Logout</button>
        </div>
    </div>
    );
};

export default SettingsPage;
