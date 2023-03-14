import React from 'react';
import './Hero.css';

function Hero(props) {
  const { title, subtitle, buttonText } = props;

  return (
    <div className="hero">
      <div className="hero-text">
        <h1>{title}</h1>
        <p>{subtitle}</p>
        <button>{buttonText}</button>
      </div>
    </div>
  );
}

export default Hero;
