
import axios from 'axios'

const PATIENTS_REST_API_URL = 'http://localhost:8081';

class PatientService {

    getPatients(){
        return axios.get(PATIENTS_REST_API_URL + '/patient/list');
    }

    createPatient(patient){
        console.log(Response);
        return axios.post(PATIENTS_REST_API_URL + '/patient/add', patient);
    }

    getPatientById(id){
        console.log(Response);
        console.log(id);
        return axios.get(`${PATIENTS_REST_API_URL}/patient/get/${id}`);
    }

    updatePatient(patient, id){
        return axios.post(PATIENTS_REST_API_URL + '/patient/update/' + id, patient);
    }

    deletePatient(id){
        return axios.get(`${PATIENTS_REST_API_URL}/patient/delete/${id}`);
    }


}



export default new PatientService();