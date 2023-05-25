import React, {useState} from 'react';
import DropdownMenu from './DropdownMenu';
import '../css/Navbar.css';
import Logo from './Logo';
import {Link} from 'react-router-dom';

function Navbar() {


    return (
        <nav className="navbar">
            <div className="navBarTitle">Cyberpunk Gaming Gear</div>
            <ul className="menu">
                <li><Link to="/">Home</Link></li>
                <li>
                    <Link to="/shop">Shop</Link>
                </li>
                <li>
                    <a href="/cart">
                        <img src={`${process.env.PUBLIC_URL}/icons8-shopping-cart-30.png`} alt="Cart"/>
                    </a>
                </li>
                <li>
                    <a href="/settings">
                        <img src={`${process.env.PUBLIC_URL}/icons8-male-user-24.png`} alt="Profile"/>

                    </a>
                </li>
            </ul>
        </nav>
    );
}

export default Navbar;
