import React, {useEffect, useState} from 'react';
import Navbar from '../Components/Navbar';
import ProductCard from '../Components/ProductCard';
import '../css/Home.css';
import Testimonial from '../Components/Testimonial';
import Hero from '../Components/Hero';
import APIService from "../Services/APIService";
import Footer from "../Components/Footer";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";


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
      name: 'Freddy',
        age: 26,
        text: "As a serious e-sports player, I need gear that can keep up with me and my intense gaming sessions. Cyberpunk Gaming Gear has been a lifesaver! The products I've purchased are comfortable, durable, and perform amazingly well. I'm so impressed with the quality and value of the products from this company!",
},
    {
      name: 'Sarah',
      age: 37,
      text: "As a mother of two young gamers, I'm always looking for the best gaming gear for my kids. Cyberpunk Gaming Gear has been a great resource for me as they offer high-quality products at great prices. I know that my kids will be safe and comfortable while gaming with their new gear, and I'm so grateful to have found such a dependable company!",
    },
  ];

    const discounts = [
        {text: '30% off on all controllers', buttonText: 'Shop Now'},
        {text: 'The finest products for your gaming setup', buttonText: 'Shop Now'},
        //{image: '/discounts/img3.jpg', text: 'Exclusive deals for new customers', buttonText: 'Join Us'},
    ];

    const sliderSettings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
    };

    return (
        <div className="Home">
            <section className="hero-section">
                <Hero
                    title="Welcome to Cyberpunk Gaming Gear"
                    subtitle="Upgrade your game with cutting-edge gear from Cyberpunk Gaming Gear!"
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

            <section className="discounts">
                <Slider {...sliderSettings}>
                    {discounts.map((discount, index) => (
                        <div key={index} className={`discount-slide discount-slide-${index}`}>
                            <div className="slide-section">
                                <h1 className="slider-text">{discount.text}</h1>
                                <a href="/shop">
                                    <button>{discount.buttonText}</button>
                                </a>
                            </div>
                        </div>
                    ))}
                </Slider>
            </section>

            <h2 className="section-title">Testimonials</h2>

            <section className="testimonials">
                {testimonials.map((testimonial, index) => (
                    <Testimonial key={index} name={testimonial.name} age={testimonial.age} text={testimonial.text}/>
                ))}
            </section>
        </div>
    );
}

export default Home;