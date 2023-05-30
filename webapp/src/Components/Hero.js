import React from 'react';
import '../css/Hero.css';
import {Link} from "react-router-dom";

function Hero(props) {
  const { title, subtitle, buttonText } = props;

  return (
    <div className="hero">
      <div className="hero-text">
        <h1>{title}</h1>
        <p>{subtitle}</p>
          <Link to="/shop">
              <button>Shop now</button>
          </Link>
      </div>
    </div>
  );
}

export default Hero;
