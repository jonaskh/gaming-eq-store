import React, {useState, useEffect} from 'react';
import Navbar from '../Components/Navbar';
import ProductCard from '../Components/ProductCard';
import Search from '../Components/Search';
import APIService from "../Services/APIService";
import '../css/Shop.css'; // New line to import Shop CSS
import CategoryBox from "../Components/CategoryBox";


function Shop() {
    const [search, setSearch] = useState('');
    const [products, setProducts] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('All');

    const shopCategories = ['All', 'Gaming', 'Office', 'Headset', 'Mouse', 'Keyboard', 'Console', 'Controller'];

    const placeholderText = selectedCategory === 'All' ? 'Search products' : `Search ${selectedCategory.toLowerCase()}`;


    useEffect(() => {
        APIService.getProducts()
            .then(response => {
                setProducts(response.data);
                console.log(response.data);
            })
            .catch(error => console.log(error));
    }, []);

    const filteredProducts = products.filter(product =>
        product.productName.toLowerCase().includes(search.toLowerCase()) &&
        (selectedCategory === 'All' || product.productCategory.toLowerCase() === selectedCategory.toLowerCase())
    );

    const handleSearch = event => {
        setSearch(event.target.value);
    };

    const handleCategoryClick = category => {
        setSelectedCategory(category);
    };

    return (
        <div>
            <Navbar/>
            <div className="shop-container">
                <Search value={search} onChange={handleSearch} placeholder={placeholderText}/>
                <div className="categories-container">
                    {shopCategories.map(category => (
                        <CategoryBox
                            key={category}
                            category={category}
                            isSelected={selectedCategory === category}
                            onClick={handleCategoryClick}
                        />
                    ))}
                </div>
                <section className="products">
                    {filteredProducts.map((product) => (
                        <ProductCard
                            key={product.id}
                            id={product.id}
                            image={product.productImage}
                            title={product.productName}
                            price={product.price}
                        />
                    ))}
                </section>
            </div>
        </div>
    );
}

export default Shop;