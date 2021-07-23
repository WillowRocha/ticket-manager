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

export const generateNewTicket = ticketType => () => {
  Service.generateNewTicket(ticketType);
}

export const callNextTicket = () => {
  Service.callNextTicket();
}

export const resetCouting = () => {
  Service.resetCouting();
}

export const clear = () => dispatch => {
  dispatch({type: CLEAR});
}