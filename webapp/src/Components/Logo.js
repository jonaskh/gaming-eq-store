import React from 'react';
import '../css/Logo.css';

function Logo(props) {
  const { color } = props;

  return (
    <a href="/Users/Public" className="logo-link">
      <div className="logo" style={{ backgroundColor: color }}>
        <span>CGG</span>
      </div>
    </a>
  );
}

export default Logo;
