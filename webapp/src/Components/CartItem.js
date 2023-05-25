// Importing necessary modules
import React, { useState } from 'react';
import '../css/CartItem.css';

const CartItem = (props) => {
    const { image, title, price, itemQuantity } = props;
    const [quantity, setQuantity] = useState(itemQuantity);

    const updateQuantity = (event) => {
        setQuantity(event.target.value);
    };

    return (
        <div className='cart-item'>
            <img src={image} alt={title} className='item-image' />
            <div className='item-details'>
                <h4 className='item-title'>{title}</h4>
                <p className='item-price'>{price.toFixed(2)} kr</p>
                <input
                    type='number'
                    min='0'
                    value={quantity}
                    onChange={updateQuantity}
                    className='item-quantity'
                />
                <p className='item-total'>{(price * quantity).toFixed(2)} kr</p>
            </div>
        </div>
    );
};

export default CartItem;
