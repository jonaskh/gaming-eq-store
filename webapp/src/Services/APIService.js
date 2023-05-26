import axios from 'axios';

const BACKEND_URL = "http://localhost:8080";
const PRODUCT_REST_API_URL = BACKEND_URL + "/products";
const RANDOM_PRODUCT_REST_API_URL = BACKEND_URL + "/products/random";

class APIService {

    getProducts(){
        return axios.get(PRODUCT_REST_API_URL);
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

    deleteSelectedProduct = (productID) => {
        return axios.delete(PRODUCT_REST_API_URL + "/delete/" + productID)
    }
}

export default new APIService();