import React from 'react';
import '../css/DropdownMenu.css';

function DropdownMenu(props) {
  const { options, onSelect } = props;

  return (
    <div className="dropdown-menu">
      {options.map((option) => (
        <div key={option} className="dropdown-menu-item" onClick={() => onSelect(option)}>
          {option}
        </div>
      ))}
    </div>
  );
}

export default DropdownMenu;
