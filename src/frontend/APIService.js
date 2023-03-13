import axios from 'axios';

const PRODUCT_REST_API_URL = "http://localhost:8080/products";

class APIService {

    getProducts(){
        return axios.get(PRODUCT_REST_API_URL);
    }

}

export default new APIService();