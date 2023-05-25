import React, {useEffect, useState} from 'react';
import Navbar from '../Components/Navbar';
import ProductCard from '../Components/ProductCard';
import '../css/Home.css';
import Testimonial from '../Components/Testimonial';
import Hero from '../Components/Hero';
import APIService from "../Services/APIService";
import Footer from "../Components/Footer";


function Home() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        APIService.getRandomProducts().then((response) => {
            setProducts(response.data);
        });
    }, []);

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
    <div className="Home">
      <Navbar />
      <section className="hero-section">
        <Hero
          title="Welcome to Cyberpunk Gaming Gear"
          subtitle="Get the best gaming gear for your e-sports needs."
          buttonText="Shop now"
        />
      </section>

      <h2 className="section-title">Featured Products</h2>

      <section className="products">

        {products.map((product) => (
          <ProductCard
            key={product.product_id}
            id={product.product_id}
            image={product.productImage}
            title={product.productName}
            price={product.price}
          />
        ))}
      </section>

      <h2 className="section-title">Testimonials</h2>

      <section className="testimonials">
        {testimonials.map((testimonial, index) => (
          <Testimonial key={index} name={testimonial.name} age={testimonial.age} text={testimonial.text} />
        ))}
      </section>
        <Footer/>
    </div>
  );
}

export default Home;