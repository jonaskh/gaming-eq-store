import axios from 'axios';

const BACKEND_URL = "http://group09.web-tek.ninja:8080/api";
const PRODUCT_REST_API_URL = BACKEND_URL + "/products";
const RANDOM_PRODUCT_REST_API_URL = BACKEND_URL + "/products/random";
const PRODUCTS_BY_CATEGORY_REST_API_URL = PRODUCT_REST_API_URL + "/category/";
const CART_BY_EMAIL_REST_API_URL = BACKEND_URL + "/users/cart/";
const SAVE_CART_TO_USER = BACKEND_URL + "/save/cart/"

class APIService {

    getProducts(){
        return axios.get(PRODUCT_REST_API_URL);
    }

    getProductsByCategory(category) {
        return axios.get(PRODUCTS_BY_CATEGORY_REST_API_URL + category);
    }

    getRandomProducts() {
        return axios.get(RANDOM_PRODUCT_REST_API_URL);
    }

    getMoreRandomProducts = (productID) => {
        return axios.get(RANDOM_PRODUCT_REST_API_URL + "/" + productID);
    }

    getSelectedProduct = (productID) => {
        return axios.get(PRODUCT_REST_API_URL + "/" + productID);
    }

    getCartItemsByUserEmail(email) {
        return axios.get(CART_BY_EMAIL_REST_API_URL + email);
    }

    addCartToUser(email) {
        return axios.get(SAVE_CART_TO_USER + email);
    }

    addProductToCart(email, product) {
        return axios.get(CART_BY_EMAIL_REST_API_URL + email + "/" + product);
    }
}

export default new APIService();