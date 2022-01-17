import React, {Component} from 'react';
import noteService from '../services/NoteService';



class NoteComponent extends Component {

    constructor(props){
        super(props)
        this.state = {
            notes:[],
            patientId: this.props.match.params.id,
        }
        
   //    this.addNote = this.addNote.bind(this);
   //    this.editNote = this.editNote.bind(this);
    }


    async componentDidMount(){
        noteService.getNotes(this.props.match.params.id).then((Response) =>{
            this.setState({notes: Response.data})
            console.log(Response);
        });
    }


    addNote() {
      this.props.history.push(`/add-note/${this.state.patientid}`);
    }

    
    editNote(noteId, patientId){
      this.props.history.push(`/edit-note/${patientId}/${noteId}`);
    }

  /*
    viewNote(id){
      this.props.history.push(`/view-note/${id}`);
    }
*/


render(){

  /*  const {notes, isLoading} = this.state;


    if (isLoading) {
        return <p>Loading...</p>;
      }*/
  

    return(



<div className="App">
   <div className="container">
      <div className="row">
         <nav className="col navbar navbar-expand-lg navbar-dark bg-info font-weight-bold">
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon" />
            </button>
            <div id="navbarContent" className="collapse navbar-collapse w-100 order-3 dual-collapse2">
               <ul className="navbar-nav ml-auto">
                  <li  className="nav-item"><a className="nav-link" href="/">Home</a>
                  </li>
                  <li  className="nav-item"><a className="nav-link" href="#">About</a>
                  </li>
                  <li  className="nav-item"><a className="nav-link" href="/patient/list">Services</a>
                  </li>
                  <li  className="nav-item"><a className="nav-link" href="#">Resources</a>
                  </li>
                  <li className="nav-item"><a className="nav-link" href="#">News Blog</a>
                  </li>
               </ul>
            </div>
         </nav>
      </div>
   </div>
   <br/><br/>
   <div className="row mt-4">
      <div className="col-6">
      </div>
      <div className="col-6">
         <a href="/patient/list/"><button type="button" className="btn btn-primary bg-warning btn-sm mr-2 mb-2 font-weight-bold text-dark btn btn-outline-light"> ☛ Return to Patient List</button></a>
      </div>
   </div>
   <div className="row mt-4">
      <h2 className="text-info">Note List</h2>
   </div>
 
   
   <br/><br/>
   <div className="App-intro ">
   </div>
   <button onClick={this.addNote}  className="btn btn-primary btn-lg bg-info font-weight-bold "> + Add New Note</button>
   <table className = "table table-striped">
      <thead>
         <tr>
            <th>Date</th>
            <th>Note</th>
            <th>Action</th>
         </tr>
      </thead>
      <tbody>
         {
         this.state.notes.map(
         note =>
         <tr key = {note.id}>
            <td style={{width: "10%"}}>{note.date}</td>
            <td style={{width: "50%"}}>{note.note}</td>
            <td style= {{width: "10%"}} >
            <div className="btn-toolbar">
               <button onClick={ () => this.editNote(note.id,note.patientId)} className="btn btn-primary bg-warning btn-sm mr-2 mb-2 font-weight-bold text-dark btn btn-outline-light">Edit</button>
            </div>
            </td>
         </tr>
         )
         }
      </tbody>
   </table>
</div>


    )

}

}

export default NoteComponent