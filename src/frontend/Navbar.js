import React, { useState } from 'react';
import DropdownMenu from './DropdownMenu';
import './Navbar.css';
import Logo from './Logo';
import { Link } from 'react-router-dom';

function Navbar() {
  const [showShopDropdown, setShowDropdown] = useState(false);
  const [selectedShopOption, setSelectedOption] = useState(null);

  const [showAboutDropdown, setShowAboutDropdown] = useState(false);
  const [selectedAboutOption, setSelectedAboutOption] = useState(null);

  const [openDropdown, setOpenDropdown] = useState(null);



  const handleShopDropdownClick = () => {
    setOpenDropdown(openDropdown === 'shop' ? null : 'shop');
    setShowDropdown(false);
  };

  const handleAboutDropdownClick = () => {
    setOpenDropdown(openDropdown === 'about' ? null : 'about');
    setShowAboutDropdown(false);
  };
  

  const handleShopOptionSelect = (option) => {
    setSelectedOption(option);
    setShowDropdown(false);
  };

  const handleAboutOptionSelect = (option) => {
    setSelectedAboutOption(option);
    setShowAboutDropdown(false);
  };
  

  const shopCategories = ['Gaming', 'Office', 'Headset', 'Mouse', 'Keyboard', 'Console', 'Controllers'];

  const aboutOptions = ['Go to cart'];


  return (
    <nav className="navbar">
      <div className="logo-container">
          <Logo color="#D61C1C" />
      </div>
      <ul className="menu">
        <li><Link to="/">Home</Link></li>
        <li className="dropdown" onClick={handleShopDropdownClick}>
          <Link to="/shop">Shop</Link>
          {openDropdown === 'shop' && <DropdownMenu options={shopCategories} onSelect={handleShopOptionSelect} />}
        </li>
        <li className="dropdown" onClick={handleAboutDropdownClick}>
            <a href="#">Cart</a>
            {openDropdown === 'about' && <DropdownMenu options={aboutOptions} onSelect={handleAboutOptionSelect} />}
        </li>
        <li><a href="#">Sign in</a></li>
      </ul>
    </nav>
  );
}

export default Navbar;
