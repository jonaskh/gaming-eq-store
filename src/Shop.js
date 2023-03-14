import React, { useState } from 'react';
import Navbar from './Navbar';
import ProductCard from './ProductCard';
import Search from './Search';

function Shop() {
  const [search, setSearch] = useState('');
  const products = [
    {
      image: 'Product 1.png',
      title: 'Headset for office and gaming',
      price: '850',
      onClick: () => console.log('Product 1 clicked'),
    },
    {
      image: 'Product 2.png',
      title: 'Gaming mouse',
      price: '700',
      onClick: () => console.log('Product 2 clicked'),
    },
    {
      image: 'Product 3.png',
      title: 'Hot gaming headset',
      price: '$1200',
      onClick: () => console.log('Product 3 clicked'),
    },
  ];

  const filteredProducts = products.filter(product => product.title.toLowerCase().includes(search.toLowerCase()));

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
            key={product.title}
            image={product.image}
            title={product.title}
            price={product.price}
          />
        ))}
      </section>
    </div>
  );
}

export default Shop;
