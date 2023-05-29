import React from 'react';
import '../css/Hero.css';

function Hero(props) {
  const { title, subtitle, buttonText } = props;

  return (
    <div className="hero">
      <div className="hero-text">
        <h1>{title}</h1>
        <p>{subtitle}</p>
          <a href="/shop">
              <button>Shop now</button>
          </a>
      </div>
    </div>
  );
}

export default Hero;
