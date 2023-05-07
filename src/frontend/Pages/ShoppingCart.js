import React, { useState } from 'react';
import Navbar from '../Components/Navbar';


const ShoppingItem = ({ name, price, handleAddToCart }) => {
    return (
        <div>
            <h2>{name}</h2>
            <p>{price} USD</p>
            <button onClick={handleAddToCart}>Add to Cart</button>
        </div>
    );
};

const ShoppingCart = ({ cartItems, handleRemoveFromCart }) => {
    return (
        <div>
            <h2>Shopping Cart</h2>
            {cartItems.length === 0 && <p>Your cart is empty.</p>}
            {cartItems.map(item => (
                <div key={item.id}>
                    <h3>{item.name}</h3>
                    <p>{item.price} USD</p>
                    <button onClick={() => handleRemoveFromCart(item)}>Remove</button>
                </div>
            ))}
            <p>Total: {cartItems.reduce((acc, item) => acc + item.price, 0)} USD</p>
        </div>
    );
};

const Shop = () => {
    const [cartItems, setCartItems] = useState([]);

    const handleAddToCart = item => {
        setCartItems([...cartItems, item]);
    };

    const handleRemoveFromCart = item => {
        setCartItems(cartItems.filter(i => i.id !== item.id));
    };

    const items = [
        { id: 1, name: 'Product A', price: 10 },
        { id: 2, name: 'Product B', price: 20 },
        { id: 3, name: 'Product C', price: 30 },
    ];

    return (
        <div>
            <Navbar />
            <h1>Shop</h1>
            <div>
                {items.map(item => (
                    <ShoppingItem
                        key={item.id}
                        name={item.name}
                        price={item.price}
                        handleAddToCart={() => handleAddToCart(item)}
                    />
                ))}
            </div>
            <ShoppingCart cartItems={cartItems} handleRemoveFromCart={handleRemoveFromCart} />
        </div>
    );
};

export default Shop;
