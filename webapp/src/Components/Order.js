import React from 'react';
import '../css/Order.css'; // Import the CSS we will write later

const Order = (props) => {
    const {id, date, total} = props;
    return (
        <div className="order">
            <p>{id}</p>
            <p>{date}</p>
            <p>{total}</p>
        </div>
    )
}

export default Order;
