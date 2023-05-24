import React, {useEffect, useState} from 'react';
import '../css/ProductPage.css';
import APIService from "../Services/APIService";
import {useLocation} from "react-router-dom";
import Navbar from "../Components/Navbar";
import ProductCard from "../Components/ProductCard"; // import the CSS file

const ProductPage = () => {

    const [product, setProduct] = useState([]);
    const location = useLocation();

    useEffect(() => {
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
                            <span className="price-amount">{product.price}</span>
                            <span className="price-currency">NOK</span>
                        </div>
                    </div>
                    <button className="product-button">Add to Cart</button>
                </div>
            </div>
            <div className="more-products">
                Other products
            </div>

            <section className="products" onClick={handleProductsClick}>

                {randomProducts.map((product) => (
                    <ProductCard
                        key={product.id}
                        id={product.id}
                        image={product.productImage}
                        title={product.productName}
                        price={product.price}
                    />
                ))}
            </section>
        </>
    );
};

export default ProductPage;
