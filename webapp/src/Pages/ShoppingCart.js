import React, {useEffect, useState} from 'react';
import Navbar from '../Components/Navbar';
import Footer from "../Components/Footer";
import APIService from "../Services/APIService";
import CartItem from "../Components/CartItem";
import "../css/Cart.css"
import jwt_decode from "jwt-decode";
import {useNavigate} from "react-router-dom";


function ShoppingCart() {
    const navigate = useNavigate();
    const [products, setProducts] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('jwt');

        if (!token) {
            navigate('/login');
        } else {
            try {
                const decodedToken = jwt_decode(token);
                const email = decodedToken.sub;
                APIService.getCartItemsByUserEmail(email)
                    .then(response => {
                        setProducts(response.data);
                        console.log(response.data);
                    })
                    .catch(error => console.log(error));
            } catch(err) {
                setError('Invalid token');
                localStorage.removeItem('jwt');
                navigate('/login');
            }
        }
    }, [navigate]);

    if (error) {
        return <div className="error">{error}</div>
    }


    return (
        <div>
            <Navbar/>
            <div className="cart-section">
                <div className="cart-container">
                    <h2 className="section-title">Your Cart</h2>
                    <section className="items">
                        {products.map((product) => (
                            <CartItem
                                key={product.id}
                                id={product.id}
                                image={product.productImage}
                                title={product.productName}
                                price={product.price}
                                itemQuantity={product.productAmount}
                            />
                        ))}
                    </section>
                </div>

                <h2>You fucker</h2>

            </div>

            <Footer/>
        </div>
    );
}

export default ShoppingCart;
