import React, { useEffect, useState } from 'react';
import Navbar from '../Components/Navbar';
import Footer from "../Components/Footer";
import APIService from "../Services/APIService";
import CartItem from "../Components/CartItem";
import jwt_decode from "jwt-decode";
import { useNavigate } from "react-router-dom";
import "../css/ShoppingPage.css";
import Popup from "../Components/Popup";

function ShoppingCart() {
    const navigate = useNavigate();
    const [products, setProducts] = useState([]);
    const [error, setError] = useState(null);
    const [totalCost, setTotalCost] = useState(0);
    const [showPopup, setShowPopup] = useState(false);
    const [popupMessage, setPopupMessage] = useState('');
    const token = localStorage.getItem('jwt');
    let checkoutButtonTempDisabled = false;
    const isButtonDisabled = products.length === 0 || checkoutButtonTempDisabled;

    useEffect(() => {
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
                APIService.getTotalCost(email)
                    .then(response => {
                        setTotalCost(response.data);
                    })
                    .catch(error => console.log(error));
            } catch (err) {
                setError('Invalid token');
                localStorage.removeItem('jwt');
                navigate('/login');
            }
        }
    }, [navigate]);

    if (error) {
        return <div className="error">{error}</div>;
    }

    const handleQuantityChange = (id, newQuantity) => {
        const updatedProducts = products.map(product =>
            product.id === id ? { ...product, productAmount: newQuantity } : product
        );
        setProducts(updatedProducts);

        const updatedTotalCost = updatedProducts.reduce((acc, curr) =>
            acc + curr.price * curr.productAmount, 0
        );
        setTotalCost(updatedTotalCost);
    };

    const handleCheckout = () => {
        checkoutButtonTempDisabled = true;
        try {
            const decodedToken = jwt_decode(token);
            const email = decodedToken.sub;
            APIService.addOrderFromCartItems(email)
                .then(response => {
                    APIService.deleteAllItemsInCart(email)
                        .then(() => {
                            setPopupMessage("Thank you for your purchase!");
                            setShowPopup(true);
                            setTimeout(() => {
                                setShowPopup(false);
                                window.location.reload();
                            }, 5000);
                        })
                        .catch(error => console.log(error));
                })
                .catch(error => console.log(error));
        } catch (err) {
            setError('Invalid token');
            localStorage.removeItem('jwt');
        }
        checkoutButtonTempDisabled = false;
    }

    return (
        <div>
            <Navbar />
            <div className="cart-section">
                <div className="cart-container">
                    <h2 className="section-title">Your Cart</h2>
                    <section className="items">
                        {products.length === 0 ? (
                            <p className="empty-cart">Your cart is empty</p>
                        ) : (
                            products.map(product => (
                                <CartItem
                                    key={product.id}
                                    id={product.id}
                                    image={product.productImage}
                                    title={product.productName}
                                    price={product.price}
                                    itemQuantity={product.productAmount}
                                    onQuantityChange={handleQuantityChange}
                                />
                            ))
                        )}
                    </section>
                </div>
                <div className="cart-info-section">
                    <h2 className="section-title">Cart Summary</h2>
                    <div className="cart-info">
                        <p>Grand Total: {totalCost}</p>
                    </div>
                    <button className={`checkout-btn ${isButtonDisabled ? 'disabled' : ''}`} onClick={handleCheckout} disabled={isButtonDisabled}>
                        Checkout
                    </button>
                </div>
            </div>

            <Footer />
            {showPopup && <Popup message={popupMessage} displayTime={5000} />}
        </div>
    );
}

export default ShoppingCart;
