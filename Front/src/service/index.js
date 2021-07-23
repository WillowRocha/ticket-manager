import axios from 'axios';

const axiosConfig = { headers: {'Content-Type': 'application/json'} };

class PanelDataService {
  prefix = '/api/tickets'

  getData() {
    return axios.get(`${this.prefix}/panel`);
  }

  generateNewTicket(ticketType) {
    axios.post(`${this.prefix}/generate`, JSON.stringify(ticketType), axiosConfig);
  }

  callNextTicket() {
    axios.post(`${this.prefix}/next`);
  }

  resetCouting() {
    axios.post(`${this.prefix}/reset`);
  }
}

export default new PanelDataService();