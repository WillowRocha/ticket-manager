import axios from 'axios';

const axiosConfig = {headers: {'Content-Type': 'application/json'}};

class PanelDataService {
  prefix = '/api/tickets'

  getData() {
    return axios.get(`${this.prefix}/panel`, {
      params: {
        userType: 'CUSTOMER'
      }
    });
  }

  generateNewTicket(ticketType) {
    axios.post(`${this.prefix}/generate`, JSON.stringify(ticketType), {
      ...axiosConfig,
      params: {
        userType: 'CUSTOMER'
      }
    });
  }

  callNextTicket() {
    axios.post(`${this.prefix}/next`, null, {
      params: {
        userType: 'MANAGER'
      }
    });
  }

  resetCouting() {
    axios.post(`${this.prefix}/reset`, null, {
      params: {
        userType: 'MANAGER'
      }
    });
  }
}

export default new PanelDataService();