import axios from 'axios';

const BACKEND_URL = "http://backend:8080/api";
const PRODUCT_REST_API_URL = BACKEND_URL + "/products";
const RANDOM_PRODUCT_REST_API_URL = BACKEND_URL + "/products/random";

class APIService {

    getProducts(){
        return axios.get(PRODUCT_REST_API_URL);
    }

    getRandomProducts() {
        return axios.get(RANDOM_PRODUCT_REST_API_URL);
    }

    getSelectedProduct = (productID) => {
        return axios.get(PRODUCT_REST_API_URL + "/" + productID)
    }
}

export default new APIService();