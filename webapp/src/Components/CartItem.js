// Importing necessary modules
import React, { useState } from 'react';
import '../css/CartItem.css';
import APIService from "../Services/APIService";

const CartItem = (props) => {
    const { id, image, title, price, itemQuantity, onQuantityChange } = props;
    const [quantity, setQuantity] = useState(itemQuantity);

    const updateQuantity = (event) => {
        setQuantity(event.target.value);
        APIService.setProductAmount(id, event.target.value-1).then();
        onQuantityChange(id, event.target.value); // Pass the changes to parent component
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
