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
        navigate(`/product`, {state:{id}})
    };

    return (
        <div className="product-card" onClick={handleClick}>
            <img src={`${process.env.PUBLIC_URL}/${image}`} alt={title} />
            <div className="productCard-details">
                <h3>{title}</h3>
                <p>{price} kr</p>
            </div>
        </div>
    );
}

export default ProductCard;
