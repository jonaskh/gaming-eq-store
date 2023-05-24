import React, { useState } from 'react';
import DropdownMenu from './DropdownMenu';
import '../css/Navbar.css';
import Logo from './Logo';
import { Link } from 'react-router-dom';

function Navbar() {
  const [showAboutDropdown, setShowAboutDropdown] = useState(false);
  const [selectedAboutOption, setSelectedAboutOption] = useState(null);

  const [openDropdown, setOpenDropdown] = useState(null);

  const handleAboutDropdownClick = () => {
    setOpenDropdown(openDropdown === 'about' ? null : 'about');
    setShowAboutDropdown(false);
  };


  const handleAboutOptionSelect = (option) => {
    setSelectedAboutOption(option);
    setShowAboutDropdown(false);
  };
  

  const aboutOptions = ['Go to cart'];


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
        <li className="dropdown" onClick={handleAboutDropdownClick}>
            <a href="/cart">Cart</a>
            {openDropdown === 'about' && <DropdownMenu options={aboutOptions} onSelect={handleAboutOptionSelect} />}
        </li>
        <li><a href="/login">Sign in</a></li>
        <li><a href="/settings">Profile</a></li>
      </ul>
    </nav>
  );
}

export default Navbar;
