import React, {useEffect, useState} from 'react';
import '../css/ProductPage.css';
import APIService from "../Services/APIService";
import {useLocation} from "react-router-dom";
import Navbar from "../Components/Navbar";
import ProductCard from "../Components/ProductCard";
import Footer from "../Components/Footer";
import jwt_decode from "jwt-decode";
import Popup from "../Components/Popup"; // import the CSS file
import { useDispatch } from 'react-redux';
import {setCartCount, setLoggedIn} from "../Services/Store";
import { useSelector } from 'react-redux';

const ProductPage = () => {
    const dispatch = useDispatch();

    const [product, setProduct] = useState([]);

    const [showPopup, setShowPopup] = useState(false);
    const [popupMessage, setPopupMessage] = useState('');

    const location = useLocation();
    const [email, setEmail] = useState("");

    useEffect(() => {
        const token = localStorage.getItem('jwt');
        try {
            const decodedToken = jwt_decode(token);
            setEmail(decodedToken.sub);

        } catch(err) {
            console.log("ERROR! Very useful ERROR message!!!");
        }
        APIService.getSelectedProduct(location.state.id)
            .then(response => {
                //console.log(response.data) //for testing purposes
                setProduct(response.data)})
            .catch(error => console.log(error));

    }, [location.state.id])

    const [randomProducts, setRandomProducts] = useState([]);

    useEffect(() => {
        APIService.getMoreRandomProducts(location.state.id).then((response) => {
            setRandomProducts(response.data);
        });
    }, [location.state.id]);

    /**
     * Relaods the page when a product is clicked.
     */
    const handleProductsClick = () => {
        window.location.reload();
    };

    const handleAddProductToCard = () => {
        if (email) {
            APIService.addProductToCart(email, product.product_id).then(() => {
                setPopupMessage("Added to cart!");
                setShowPopup(true);
                APIService.getCartItemsByUserEmail(email)
                    .then(response => {
                        dispatch(setCartCount(response.data.length));
                    })
                    .catch(error => console.log(error));
                setTimeout(() => {
                    setShowPopup(false);
                }, 5000);
            })
        } else {
            setPopupMessage("You need to be logged in to add to cart");
            setShowPopup(true);
            setTimeout(() => {
                setShowPopup(false);
            }, 5000);
        }
    }

    return (
        <>
            <div className="product-page">
                <h1 className="product-title">{product.productName}</h1>
                <div className="product-container">
                    <div className="product-image">
                        <img src={`${process.env.PUBLIC_URL}/${product.productImage}`} alt={product.productName} />
                    </div>
                    <div className="product-details">
                        <h2>Product info</h2>
                        <p className="product-description">
                            {product.productDesc}
                        </p>
                        <div className="product-info">
                            <div className="product-price">
                                <span className="price-amount">{product.price}</span>
                                <span className="price-currency">kr</span>
                            </div>
                        </div>
                        <button className="product-button" onClick={handleAddProductToCard}>Add to Cart</button>
                    </div>
                </div>

                <h1 className="more-products">
                    Other products
                </h1>
                <section className="products">

                    {randomProducts.map((product) => (
                        <ProductCard
                            key={product.product_id}
                            id={product.product_id}
                            image={product.productImage}
                            title={product.productName}
                            price={product.price}
                            onClick={handleProductsClick}
                        />
                    ))}
                </section>

                {showPopup && <Popup message={popupMessage} displayTime={5000} />}
            </div>
        </>
    );
};

export default ProductPage;
