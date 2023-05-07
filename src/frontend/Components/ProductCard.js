import React from 'react';
import {useNavigate} from 'react-router-dom';
import '../css/ProductCard.css';

function ProductCard(props) {
    const { image, title, price, id } = props;
    const navigate = useNavigate();

    const handleClick = () => {
        console.log("title:" + title)
        console.log("price:" + price)
        console.log("id:" + id)
        navigate(`/products/${id}`);
    };

    return (
        <div className="product-card" onClick={handleClick}>
            <img src={`${process.env.PUBLIC_URL}/${image}`} alt={title} />
            <div className="product-details">
                <h3>{title}</h3>
                <p>NOK {price}</p>
            </div>
        </div>
    );
}

export default ProductCard;
