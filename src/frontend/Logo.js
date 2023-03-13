import React from 'react';
import './Logo.css';

function Logo(props) {
  const { color } = props;

  return (
    <a href="/" className="logo-link">
      <div className="logo" style={{ backgroundColor: color }}>
        <span>CGG</span>
      </div>
    </a>
  );
}

export default Logo;
