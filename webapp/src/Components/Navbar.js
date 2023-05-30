import React from 'react';
import { Link } from 'react-router-dom';
import '../css/Navbar.css';
import { useSelector } from 'react-redux';

function Navbar() {
  const shoppingCartCount = useSelector((state) => state.shoppingCartCount);
  const isLoggedIn = useSelector((state) => state.isLoggedIn);

  return (
      <nav className="navbar">
        <Link to="/" className="logo-link">
          <img className="logo" src={process.env.PUBLIC_URL + '/logo-no-background.png'} alt="Logo" style={{ width: '150px', height: 'auto' }} />
        </Link>
        <ul className="menu">
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/shop">Shop</Link>
          </li>
          <li>
            <a href="/cart">
              <img src={`${process.env.PUBLIC_URL}/icons8-shopping-cart-30.png`} alt="Cart" />
              <span>{shoppingCartCount}</span>
            </a>
          </li>
          <li>
            <Link to="/settings">
              {isLoggedIn ? (
                  <img src={`${process.env.PUBLIC_URL}/icons8-male-user-24.png`} alt="Profile" />
              ) : (
                  <span>Login</span>
              )}
            </Link>
          </li>
        </ul>
      </nav>
  );
}

export default Navbar;
