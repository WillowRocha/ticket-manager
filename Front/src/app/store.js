import {configureStore} from '@reduxjs/toolkit';

import createRootReducer from './reducers';

const reducer = createRootReducer();

const store = configureStore({
  reducer,
  devTools: process.env.NODE_ENV !== 'production'
});

export default store;
