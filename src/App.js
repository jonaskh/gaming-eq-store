import React from 'react';
import Navbar from './Navbar';
import ProductCard from './ProductCard';
import './App.css';
import Testimonial from './Testimonial';
import Hero from './Hero';


function App() {
  const products = [
    {
      image: 'Product 1.png',
      title: 'Headset for office and gaming',
      price: 850,
      onClick: () => alert('Clicked Product 1'),

    },
    {
      image: 'Product 2.png',
      title: 'Gaming mouse',
      price: 700,
      onClick: () => alert('Clicked Product 2'),

    },
    {
      image: 'Product 3.png',
      title: 'Hot gaming headset',
      price: 1200,
      onClick: () => alert('Clicked Product 3'),

    },
  ];

  const testimonials = [
    {
      name: 'Joe',
      age: 17,
      text: "I recently purchased a headset from Cyberpunk Gaming Gear, and it was an awesome experience. The headset is super comfortable and has amazing sound quality. It's really helped me up my game and I'm so glad I made this purchase!",
    },
    {
      name: 'Alex',
      age: 22,
      text: "As a serious e-sports player, I need gear that can keep up with me and my intense gaming sessions. Cyberpunk Gaming Gear has been a lifesaver! The products I've purchased are comfortable, durable, and perform amazingly well. I'm so impressed with the quality and value of the products from this company!",
    },
    {
      name: 'Sarah',
      age: 37,
      text: "As a mother of two young gamers, I'm always looking for the best gaming gear for my kids. Cyberpunk Gaming Gear has been a great resource for me as they offer high-quality products at great prices. I know that my kids will be safe and comfortable while gaming with their new gear, and I'm so grateful to have found such a dependable company!",
    },
  ];
  
  return (
    <div className="App">
      <Navbar />
      <section className="hero">
        <Hero
          title="Welcome to Cyberpunk Gaming Gear"
          subtitle="Get the best gaming gear for your e-sports needs."
          buttonText="Shop now"
        />
      </section>

      <section className="products">
        {products.map((product) => (
          <ProductCard
            key={product.title}
            image={product.image}
            title={product.title}
            price={product.price}
          />
        ))}
      </section>

      <section className="testimonials">
        {testimonials.map((testimonial, index) => (
          <Testimonial key={index} name={testimonial.name} age={testimonial.age} text={testimonial.text} />
        ))}
      </section>

      <footer>
        <p>© 2023 All Rights Reserved</p>
      </footer>
    </div>
  );
}

export default App;