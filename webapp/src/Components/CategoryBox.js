import React from 'react';
import '../css/CategoryBox.css';

function CategoryBox({ category, isSelected, onClick }) {
    return (
        <div
            className={`category-box ${isSelected ? 'selected' : ''}`}
            onClick={() => onClick(category)}
        >
            <div className="category-text">
                {category}
            </div>
            <img
                src={process.env.PUBLIC_URL + '/' + category + '_category_image.png'}
                alt="category"
                className="category-background-image"
            />
        </div>
    );
}

export default CategoryBox;
