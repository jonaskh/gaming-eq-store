import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from '../Components/Navbar';
import jwt_decode from 'jwt-decode';




const SettingsPage = () => {
    const navigate = useNavigate();
    const [userEmail, setUserEmail] = useState('');

    useEffect(() => {
        const isAuthenticated = !!localStorage.getItem('jwt');

        if (!isAuthenticated) {
            navigate('/login');
        } else {
            const token = localStorage.getItem('jwt');
            const decodedToken = jwt_decode(token);
            const email = decodedToken.sub;
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