import React, {useEffect, useState} from 'react';
import '../css/ProductPage.css';
import APIService from "../Services/APIService";
import {useLocation} from "react-router-dom";
import Navbar from "../Components/Navbar";
import ProductCard from "../Components/ProductCard";
import Footer from "../Components/Footer";
import jwt_decode from "jwt-decode"; // import the CSS file

const ProductPage = () => {

    const [product, setProduct] = useState([]);
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

    return (
        <>
            <Navbar />
            <div className="product-page">
                <div className="product-container">
                    <div className="product-image">
                        <h1>{product.productName}</h1>
                        <img src={`${process.env.PUBLIC_URL}/${product.productImage}`} alt={product.productName} />
                    </div>
                    <div className="product-details">
                        <div className="product-info">
                            <div className="product-price">
                                <span className="price-amount">{product.price}</span>
                                <span className="price-currency">kr</span>
                            </div>
                        </div>
                        <button className="product-button" onClick={() => APIService.addProductToCart(email, product.product_id)}>Add to Cart</button>
                    </div>
                    <div>
                        <h2>Product info</h2>
                        <p className="product-description">
                            {product.productDesc}
                        </p>
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
                <Footer/>
            </div>
        </>
    );
};

export default ProductPage;
