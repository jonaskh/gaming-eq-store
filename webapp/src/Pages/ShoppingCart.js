import React, {useEffect, useState} from 'react';
import Navbar from '../Components/Navbar';
import Footer from "../Components/Footer";
import APIService from "../Services/APIService";
import CartItem from "../Components/CartItem";
import "../css/Cart.css"


function ShoppingCart() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        APIService.getProducts()
            .then(response => {
                setProducts(response.data);
                console.log(response.data);
            })
            .catch(error => console.log(error));

    }, []);

    return (
        <div>
            <Navbar/>
            <div className="cart-section">
                <div className="cart-container">
                    <h2 className="section-title">Your Cart</h2>
                    <section className="items">
                        {products.map((product) => (
                            <CartItem
                                key={product.product_id}
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
