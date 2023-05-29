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
        </div>
    );
}

export default CategoryBox;
