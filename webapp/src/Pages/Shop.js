import React, {useState, useEffect} from 'react';
import Navbar from '../Components/Navbar';
import ProductCard from '../Components/ProductCard';
import Search from '../Components/Search';
import APIService from "../Services/APIService";
import '../css/Shop.css'; 
import CategoryBox from "../Components/CategoryBox";
import Footer from "../Components/Footer";


function Shop() {
    const [search, setSearch] = useState('');
    const [products, setProducts] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('All');

    const shopCategories = ['All', 'Gaming', 'Office', 'Headsets', 'Mouse', 'Keyboards', 'Consoles', 'Controllers', 'Monitors'];

    const placeholderText = selectedCategory === 'All' ? 'Search products' : `Search ${selectedCategory.toLowerCase()}`;

    useEffect(() => {
        if(selectedCategory === 'All') {
            APIService.getProducts()
                .then(response => {
                    setProducts(response.data);
                    console.log(response.data);
                })
                .catch(error => console.log(error));
        } else {
            APIService.getProductsByCategory(selectedCategory)
                .then(response => {
                    setProducts(response.data);
                    console.log(response.data);
                })
                .catch(error => console.log(error));
        }
    }, [selectedCategory]);


    const filteredProducts = products.filter(product =>
        product.productName.toLowerCase().includes(search.toLowerCase()));

    const handleSearch = event => {
        setSearch(event.target.value);
    };

    const handleCategoryClick = category => {
        setSelectedCategory(category);
    };

    return (
        <div>
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
                            key={product.product_id}
                            id={product.product_id}
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