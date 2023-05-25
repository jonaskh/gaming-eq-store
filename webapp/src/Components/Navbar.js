import React, { useState } from 'react';
import DropdownMenu from './DropdownMenu';
import '../css/Navbar.css';
import Logo from './Logo';
import { Link } from 'react-router-dom';

function Navbar() {
  



  return (
    <nav className="navbar">
      <div className="logo-container">
          <Logo color="#ffbd03" />
      </div>
      <ul className="menu">
        <li><Link to="/">Home</Link></li>
        <li>
          <Link to="/shop">Shop</Link>
        </li>
        <li>
            <a href="/cart">Cart</a>
        </li>
        <li><a href="/login">Sign in</a></li>
        <li><a href="/settings">Profile</a></li>
      </ul>
    </nav>
  );
}

export default Navbar;
