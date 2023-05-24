import React from 'react';
import '../css/Search.css'; // New line to import Search CSS

function Search({ value, onChange, placeholder }) { // Add placeholder prop
  return (
      <input
          className="search-input"
          type="text"
          placeholder={placeholder}
          value={value}
          onChange={onChange}
      />
  );
}

export default Search;
