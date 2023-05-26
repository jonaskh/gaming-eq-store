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
    const [totalCost, setTotalCost] = useState(0);

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
        return <div className="error">{error}</div>
    }
    const handleQuantityChange = (id, newQuantity) => {
        const updatedProducts = products.map((product) =>
            product.id === id ? {...product, productAmount: newQuantity} : product
        );
        setProducts(updatedProducts);

        const updatedTotalCost = updatedProducts.reduce((acc, curr) =>
            acc + curr.price * curr.productAmount, 0
        );
        setTotalCost(updatedTotalCost);
    };


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
                                onQuantityChange={handleQuantityChange}

                            />
                        ))}
                    </section>
                </div>

                <h2>You fucker</h2>
                <div className="cart-info">
                    Grand total: {totalCost}
                </div>

            </div>

            <Footer/>
        </div>
    );
}

export default ShoppingCart;
