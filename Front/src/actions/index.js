import {SET_DATA, CLEAR} from "./types";
import Service from '../service';

export const handleException = exception => {
  console.log("An error ocurred while requesting", exception);
};

export const loadData = () => dispatch => {
  Service.getData()
    .then(response => dispatch({type: SET_DATA, payload: response.data}))
    .catch(handleException);
}

export const clear = () => dispatch => {
  dispatch({type: CLEAR});
}