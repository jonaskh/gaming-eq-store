import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from '../Components/Navbar';
import jwt_decode from 'jwt-decode';
import '../css/AdminPanel.css';

const AdminPanelPage = () => {
    const navigate = useNavigate();
    const [userRoles, setUserRoles] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('jwt');

        if (!token) {
            navigate('/login');
        } else {
            try {
                const decodedToken = jwt_decode(token);
                const roles = decodedToken.roles.split(',');
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
    const isAdmin = userRoles.includes('ADMIN');
    if (!isAdmin) {
        navigate('/login');
        return null; 
    }

    return (
        <div className="admin-panel-container">
            <Navbar />
            <div className="admin-panel-content">
                <h2>Admin Panel</h2>
                <p>Legg til noe kult her</p>
                <button onClick={handleLogout} className="logout-button">
                    Logout
                </button>
            </div>
        </div>
    );
};

export default AdminPanelPage;
