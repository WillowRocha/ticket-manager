import {SET_DATA, CLEAR} from '../actions/types';

const initialState = {
  currentTicketCode: null,
  lastTickets: []
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case SET_DATA:
      return {...state, ...action.payload};
    case CLEAR:
      return {...state, ...initialState};
    default:
      return state;
  }
};

export default reducer;