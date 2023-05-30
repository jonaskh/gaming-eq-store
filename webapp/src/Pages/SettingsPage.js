import React, { useEffect, useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import Navbar from '../Components/Navbar';
import jwt_decode from 'jwt-decode';
import '../css/SettingsPage.css';
import Footer from "../Components/Footer";
import Order from '../Components/Order';
import APIService from "../Services/APIService";
import { useDispatch } from 'react-redux';
import { setCartCount, setLoggedIn} from "../Services/Store";


const SettingsPage = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [userEmail, setUserEmail] = useState('');
    const [userRoles, setUserRoles] = useState([]);
    const [orderList, setOrderList] = useState([]); // New state variable for orders
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
                dispatch(setLoggedIn(true));
                APIService.getCartItemsByUserEmail(email)
                    .then(response => {
                        dispatch(setCartCount(response.data.length));
                        console.log(response.data);
                    })
                    .catch(error => console.log(error));
            } catch (err) {
                setError('Invalid token');
                localStorage.removeItem('jwt');
                navigate('/login');
            }
        }
    }, [navigate]);

    useEffect(() => {
        const fetchOrders = async () => {
            try {
                const response = await APIService.getAllOrdersByEmail(userEmail);
                setOrderList(response.data);
            } catch (err) {
                setError(err.message);
            }
        };

        if (userEmail) {
            fetchOrders();
        }
    }, [userEmail]);

    const handleLogout = () => {
        dispatch(setLoggedIn(false));
        dispatch(setCartCount(0));
        localStorage.removeItem('jwt');
        navigate('/login');
    };

    if (error) {
        return <div className="error">{error}</div>;
    }

    return (
        <div className="settings-container">
            <div className="settings-content">
                <h2>Profile</h2>
                {userEmail && <p>Email: {userEmail}</p>}
                {userRoles.length > 0 && (
                    <div>
                        <p>Roles: {userRoles.join(', ')}</p>
                        {userRoles.includes('ADMIN') && (
                            <Link to="/admin-panel">Admin Panel</Link>
                        )}
                    </div>
                )}
                <h2>Order history</h2>
                <div className="order-section">
                    <div className="order-header">
                        <h3>ID</h3>
                        <h3>Date</h3>
                        <h3>Total Cost</h3>
                    </div>
                    {orderList.map(order =>
                        <Order
                            key={order.orderId}
                            id={order.orderId}
                            date={order.orderDate}
                            total={order.totalOrderPrice}
                        />
                    )}
                </div>
                <button onClick={handleLogout} className="logout-button">
                    Logout
                </button>
            </div>
        </div>
    );
};

export default SettingsPage;
