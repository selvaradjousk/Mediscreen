
import axios from 'axios'

const NOTES_REST_API_URL = 'http://localhost:8082';

class NoteService {
    
    getNotes(patientId){
        return axios.get(`${NOTES_REST_API_URL}/note/list/${patientId}`);
    }

    createNote(newNote){
        console.log(Response);
        return axios.post(NOTES_REST_API_URL + '/note/add', newNote);
    }

    getNoteById(id){
        console.log(Response);
        console.log(id);
        return axios.get(`${NOTES_REST_API_URL}/note/get/${id}`);
    }

    updateNote(editedNote, id){
        return axios.post(NOTES_REST_API_URL + '/note/update/' + id, editedNote);
    }

    deleteNote(id){
        return axios.get(`${NOTES_REST_API_URL}/note/delete/${id}`);
    }


}



export default new NoteService();