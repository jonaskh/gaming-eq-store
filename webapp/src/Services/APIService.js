import axios from 'axios';

const BACKEND_URL = "http://localhost:8080";
const PRODUCT_REST_API_URL = BACKEND_URL + "/products";
const RANDOM_PRODUCT_REST_API_URL = BACKEND_URL + "/products/random";
const PRODUCTS_BY_CATEGORY_REST_API_URL = PRODUCT_REST_API_URL + "/category/"

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
        return axios.get(PRODUCT_REST_API_URL + "/" + productID)
    }
}

export default new APIService();