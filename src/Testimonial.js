import React from 'react';
import './Testimonial.css';

function Testimonial(props) {
  const { name, age, text } = props;

  return (
    <div className="testimonial">
      <p className="testimonial-text">{text}</p>
      <p className="testimonial-info">{name}, {age}</p>
    </div>
  );
}

export default Testimonial;
