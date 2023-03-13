import React from 'react';
import './ProductCard.css';

function ProductCard(props) {
  const { image, title, price, onClick } = props;

  return (
    <div className="product-card" onClick={onClick}>
      <img src={`${process.env.PUBLIC_URL}/${image}`} alt={title} />
      <div className="product-details">
        <h3>{title}</h3>
        <p>NOK {price}</p>
      </div>
    </div>
  );
}

export default ProductCard;
