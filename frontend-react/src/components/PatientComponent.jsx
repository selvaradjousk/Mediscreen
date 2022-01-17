import React, {Component} from 'react';
import patientService from '../services/PatientService';
//import noteService from '../services/NoteService';



class PatientComponent extends Component {

    constructor(props){
        super(props)
        this.state = {
            patients:[]
        }
       this.addPatient = this.addPatient.bind(this);
       this.editPatient = this.editPatient.bind(this);
       this.deletePatient = this.deletePatient.bind(this); 
    }


    async componentDidMount(){
        patientService.getPatients().then((Response) =>{
            this.setState({patients: Response.data})
            console.log(Response);
        });
    }


    addPatient() {
      this.props.history.push(`/add-patient`);
    }

    
    editPatient(id){
      this.props.history.push(`/edit-patient/${id}`);
    }
  
    deletePatient(id){
      patientService.deletePatient(id).then( res => {
          this.setState({patients: this.state.patients.filter(patient => patient.id !== id)});
      });
    }
  
    viewPatient(id){
      this.props.history.push(`/view-patient/${id}`);
    }

    notePatient(id) {
      this.props.history.push(`/list-note/${id}`);
     }

     reportPatient(id) {
      this.props.history.push(`/assess/${id}`);
     }



render(){

   

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
      <h2 className="text-info">Patient List</h2>
   </div>

   
   <br/><br/>
   <div className="App-intro">
   </div>
   <button onClick={this.addPatient}  className="btn btn-primary btn-lg bg-info font-weight-bold "> + Add New Patient</button>
   <table className = "table table-striped">
      <thead>
         <tr>
            <th>Id</th>
            <th>Last Name</th>
            <th>First Name</th>
            <th>Date of birth</th>
            <th>Sex</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Action</th>
         </tr>
      </thead>
      <tbody>
         {
         this.state.patients.map(
         patient =>
         <tr key = {patient.id}>
            <td style={{width: "1%"}}>{patient.id}</td>
            <td style={{width: "8%"}}>{patient.firstName}</td>
            <td style={{width: "8%"}}>{patient.lastName}</td>
            <td style={{width: "5%"}}>{patient.birthDate}</td>
            <td style={{width: "1%"}}>{patient.sex}</td>
            <td style={{width: "18%"}}>{patient.address}</td>
            <td style={{width: "5%"}}>{patient.phoneNumber}</td>
            <td style= {{width: "60%"}} class="text-center">
            <div className="btn-toolbar">
               <button onClick={ () => this.editPatient(patient.id)} className="btn btn-primary bg-warning btn-sm mr-2 mb-2 font-weight-bold text-dark btn btn-outline-light">Edit</button>
               <button style={{marginLeft: "10px"}}  onClick={ () => this.notePatient(patient.id)} className="btn btn-primary btn-sm bg-success mr-2 mb-2 font-weight-bold btn btn-outline-light">Notes</button>
               <button style={{marginLeft: "10px"}}  onClick={ () => this.reportPatient(patient.id)} className="btn btn-primary btn-sm mr-2 mb-2 font-weight-bold btn btn-outline-light">Report</button>
               <button style={{marginLeft: "10px"}}  onClick={ () => this.deletePatient(patient.id)} className="btn btn-primary btn-sm bg-danger mr-2 mb-2 font-weight-bold btn btn-outline-light">Delete</button>
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

export default PatientComponent