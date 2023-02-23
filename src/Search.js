import React from 'react';

function Search({ value, onChange }) {
  return (
    <input type="text" placeholder="Search products" value={value} onChange={onChange} />
  );
}

export default Search;
