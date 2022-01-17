
import axios from 'axios'

const ASSESSMENT_REST_API_URL = 'http://localhost:8083';

class AssessService {
    
  

    generateReport(id) {
        console.log(Response);
        console.log('generate report called for id: ' + id);
        return axios.get(`${ASSESSMENT_REST_API_URL}/assess/${id}`);
      }


}



export default new AssessService();