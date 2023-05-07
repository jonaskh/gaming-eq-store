import React, {useEffect, useState} from 'react';
import '../css/ProductPage.css';
import APIService from "../Services/APIService";
import {useLocation} from "react-router-dom"; // import the CSS file

const ProductPage = () => {

    const [product, setProduct] = useState([]);
    const location = useLocation();

    useEffect(() => {
        APIService.getSelectedProduct(location.state.id)
            .then(response => {
                //console.log(response.data) for testing purposes
                setProduct(response.data)})
            .catch(error => console.log(error));
    })

    return (
        <div className="product-container">
            <div className="product-image">
                <img src={`${process.env.PUBLIC_URL}/${product.productImage}`} alt={product.productName} />
            </div>
            <div className="product-details">
                <h1>{product.productName}</h1>
                <p className="product-description">
                    {product.productDesc}
                </p>
                <div className="product-info">
                    <div className="product-price">
                        <span className="price-currency">NOK</span>
                        <span className="price-amount">{product.price}</span>
                    </div>
                    <div className="product-rating">
                        <span className="rating-star">&#9733;</span>
                        <span className="rating-count">(10)</span>
                    </div>
                </div>
                <button className="product-button">Add to Cart</button>
            </div>
        </div>
    );
};

export default ProductPage;
