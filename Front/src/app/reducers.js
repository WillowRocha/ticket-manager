import {combineReducers} from 'redux';

import ticketsData from '../reducer';

const reducers = () => combineReducers({
  ticketsData
});

export default reducers;
