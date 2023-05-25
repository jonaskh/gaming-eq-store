import React, { useEffect, useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import Navbar from '../Components/Navbar';
import jwt_decode from 'jwt-decode';
import '../css/SettingsPage.css';

const SettingsPage = () => {
    const navigate = useNavigate();
    const [userEmail, setUserEmail] = useState('');
    const [userRoles, setUserRoles] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('jwt');

        if (!token) {
            navigate('/login');
        } else {
            try {
                const decodedToken = jwt_decode(token);
                const email = decodedToken.sub;
                const roles = decodedToken.roles.split(',');
                setUserEmail(email);
                setUserRoles(roles);
            } catch (err) {
                setError('Invalid token');
                localStorage.removeItem('jwt');
                navigate('/login');
            }
        }
    }, [navigate]);

    const handleLogout = () => {
        localStorage.removeItem('jwt');
        navigate('/login');
    };

    if (error) {
        return <div className="error">{error}</div>;
    }

    return (
        <div className="settings-container">
            <Navbar />
            <div className="settings-content">
                <h2>Settings</h2>
                {userEmail && <p>Email: {userEmail}</p>}
                {userRoles.length > 0 && (
                    <div>
                        <p>Roles: {userRoles.join(', ')}</p>
                        {userRoles.includes('ADMIN') && (
                            <Link to="/admin-panel">Admin Panel</Link>
                        )}
                    </div>
                )}
                <button onClick={handleLogout} className="logout-button">
                    Logout
                </button>
            </div>
        </div>
    );
};

export default SettingsPage;
