// Importing necessary modules
import React, { useState } from 'react';
import '../css/CartItem.css';
import APIService from "../Services/APIService";

const CartItem = (props) => {
    const { id, image, title, price, itemQuantity, onQuantityChange } = props;
    const [quantity, setQuantity] = useState(itemQuantity);

    const updateQuantity = (event) => {
        setQuantity(event.target.value);
        if (quantity) {
            APIService.setProductAmount(id, quantity).then();
            onQuantityChange(id, event.target.value);
        }
    };

    const deleteItem = () => {
        APIService.deleteCartItem(id).then(
            () => {handleProductsClick()}
        );
    }

    /**
     * Relaods the page when a product is clicked.
     */
    const handleProductsClick = () => {
        window.location.reload();
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
                <button className="delete-button" onClick={deleteItem}>Delete</button>

            </div>
        </div>
    );
};

export default CartItem;
