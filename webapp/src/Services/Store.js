// store.js
import { createStore } from 'redux';

// Action Types
const SET_CART_COUNT = 'SET_CART_COUNT';
const SET_LOGGED_IN = 'SET_LOGGED_IN';

// Action Creators
const setCartCount = (count) => ({
    type: SET_CART_COUNT,
    payload: count,
});

const setLoggedIn = (isLoggedIn) => ({
    type: SET_LOGGED_IN,
    payload: isLoggedIn,
});

// Initial State
const initialState = {
    shoppingCartCount: 0,
    isLoggedIn: false,
};

// Reducer
const rootReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_CART_COUNT:
            return { ...state, shoppingCartCount: action.payload };
        case SET_LOGGED_IN:
            return { ...state, isLoggedIn: action.payload };
        default:
            return state;
    }
};

// Store
const store = createStore(rootReducer);

export { store, setCartCount, setLoggedIn };
