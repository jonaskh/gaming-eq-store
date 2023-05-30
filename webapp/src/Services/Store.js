import { createStore } from 'redux';

// This is a reducer - a function that takes a current state value and an action object, and returns a new state
const cartItemCountReducer = (state = 0, action) => {
    switch (action.type) {
        case 'INCREMENT':
            return state + 1;
        case 'DECREMENT':
            return state - 1;
        case 'SET':
            return action.payload;
        default:
            return state;
    }
};

// Create a Redux store holding the state of your app.
const store = createStore(cartItemCountReducer);

export default store;
