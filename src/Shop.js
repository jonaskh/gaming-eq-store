import React from 'react';
import Navbar from './Navbar';
import ProductCard from './ProductCard';

function Shop() {
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

  return (
    <div>
      <Navbar />
      <section className="products">
        <h2 className="section-title">Shop</h2>
        <div className="product-container">
          {products.map((product, index) => (
            <ProductCard 
            key={index} 
            image={product.image}
            title={product.title} 
            price={product.price}  />
          ))}
        </div>
      </section>
    </div>
  );
}

export default Shop;
