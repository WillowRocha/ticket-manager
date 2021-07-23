import axios from 'axios';

class PanelDataService {
  
  getData() {
    return axios.get('/api/tickets/panel');
  }
}

export default new PanelDataService();