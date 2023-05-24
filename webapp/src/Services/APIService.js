import axios from 'axios';

const BACKEND_URL = "http://localhost:8080";
const PRODUCT_REST_API_URL = BACKEND_URL + "/products";
const RANDOM_PRODUCT_REST_API_URL = BACKEND_URL + "/products/random";
const LOGIN_API = BACKEND_URL + "/authenticate";
const REGISTER_API = BACKEND_URL + "/register";



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

    getLogin = () => {
        return axios.get(LOGIN_API)
    }

    getLogin = () => {
        return axios.get(REGISTER_API)
    }


}

export default new APIService();