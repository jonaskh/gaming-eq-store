import React, { useState, useEffect } from 'react';
import Navbar from './Navbar';
import ProductCard from './ProductCard';
import Search from './Search';
import APIService from "./APIService";

function Shop() {
  const [search, setSearch] = useState('');
  const [products, setProducts] = useState([]);

  useEffect(() => {
      APIService.getProducts()
        .then(response => setProducts(response.data))
        .catch(error => console.log(error));
  });

  const filteredProducts = products.filter(product => product.productName.toLowerCase().includes(search.toLowerCase()));

  const handleSearch = event => {
    setSearch(event.target.value);
  };

  return (
      <div>
        <Navbar />
        <h2 className="section-title">Shop</h2>
        <Search value={search} onChange={handleSearch} />
        <section className="products">
          {filteredProducts.map((product) => (
              <ProductCard
                  key={product.id}
                  image={product.productImage}
                  title={product.productName}
                  price={product.price}
              />
          ))}
        </section>
      </div>
  );
}

export default Shop;
