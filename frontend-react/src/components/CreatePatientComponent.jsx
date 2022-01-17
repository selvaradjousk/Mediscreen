import React, { Component } from 'react';
import PatientService from '../services/PatientService';

class CreatePatientComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
          // step 2
          id: this.props.match.params.id,
          lastName: '',
          firstName: '',
          birthDate: '',
          sex: '',
          address: '',
          phoneNumber: ''
        }

        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeBirthDateHandler = this.changeBirthDateHandler.bind(this);
        this.changeSexHandler = this.changeSexHandler.bind(this);
        this.changeAddressHandler = this.changeAddressHandler.bind(this);
        this.changePhoneNumberHandler = this.changePhoneNumberHandler.bind(this);
        this.savePatient = this.savePatient.bind(this);


    }

    savePatient = (e) => {
        e.preventDefault();
        let patient = {lastName: this.state.lastName, firstName: this.state.firstName, birthDate: this.state.birthDate, sex: this.state.sex, address: this.state.address, phoneNumber: this.state.phoneNumber};
        console.log('patient => ' + JSON.stringify(patient))

        PatientService.createPatient(patient).then(res => {
            console.log('patient Added Successfully => ' + JSON.stringify(patient));
            this.props.history.push('/patient/list')
        })

    }


    cancel(){
        this.props.history.push('/patient/list');
    }




// something here
    changeLastNameHandler= (event) => {
        this.setState({lastName: event.target.value});
    }

    changeFirstNameHandler= (event) => {
        this.setState({firstName: event.target.value});
    }

    changeBirthDateHandler= (event) => {
        this.setState({birthDate: event.target.value});
    }
 
    changeSexHandler= (event) => {
        this.setState({sex: event.target.value});
    }
     
    changeAddressHandler= (event) => {
        this.setState({address: event.target.value});
    }
    
    changePhoneNumberHandler= (event) => {
        this.setState({phoneNumber: event.target.value});
    }   











    render() {
        return (
            

<div>
   <div className="container">
      <br/>
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
      <div className="row mt-4">
         <div className="col-6">
         </div>
         <div className="col-6 text-right">
            <a href="/patient/list"><button type="button" className="btn btn-primary bg-warning btn-sm mr-2 mb-2 font-weight-bold text-dark btn btn-outline-light"> ☛ Return to Patient List</button></a>
         </div>
      </div>
      <div className="row">
         <h2 className="text-info">Add New Patient</h2>
      </div>
      <br/>
      <div className="row">
         <div className = "card-body">
            <form>
               <div className="form-group">
                  <label className="col-sm-2 control-label">Last Name</label>
                  <div className="col-sm-10">
                     <input placeholder="Last Name" name="lastName" className="form-control" 
                        value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                  </div>
               </div>
               <div className="form-group">
                  <label className="col-sm-2 control-label">Last Name</label>
                  <div className="col-sm-10">
                     <input placeholder="First Name" name="firstName" className="form-control" 
                        value={this.state.firstName} onChange={this.changeFirstNameHandler}/>
                  </div>
               </div>
               <div className="form-group">
                  <label className="col-sm-2 control-label">Date of birth</label>
                  <div className="col-sm-10">
                     <input placeholder="Birth Date" name="birthDate" className="form-control" 
                        value={this.state.birthDate} onChange={this.changeBirthDateHandler}/>
                  </div>
               </div>
               <div className="form-group">
                  <label className="col-sm-2 control-label">Sex</label>
                  <div className="col-sm-10">
                     <input placeholder="Sex" name="sex" className="form-control" 
                        value={this.state.sex} onChange={this.changeSexHandler}/>
                  </div>
               </div>
               <div className="form-group">
                  <label className="col-sm-2 control-label">Address</label>
                  <div className="col-sm-10">
                     <input placeholder="address" name="address" className="form-control" 
                        value={this.state.address} onChange={this.changeAddressHandler}/>
                  </div>
               </div>
               <div className="form-group">
                  <label className="col-sm-2 control-label">Phone</label>
                  <div className="col-sm-10">
                     <input placeholder="phoneNumber" name="phoneNumber" className="form-control" 
                        value={this.state.phoneNumber} onChange={this.changePhoneNumberHandler}/>
                  </div>
               </div>
               {/*
               <div className="form-group">
                  <div className="col-sm-12">
                     <a className="btn btn-danger btn-sm" href="/patient/list">Cancel</a>
                     <input className="btn btn-primary btn-sm" type="submit" value="Add patient"/>
                  </div>
               </div>
               */}
               <div className="form-group">
                  <div className="col-sm-12">
                     <span><button onClick={this.savePatient}  className="btn btn-primary btn-sm "> Save</button>&nbsp;&nbsp;
                     <button onClick={this.cancel.bind(this)}  className="btn btn-danger btn-sm "> Cancel</button></span>
                  </div>
               </div>
            </form>
         </div>
      </div>
   </div>
</div>


        );
    }
}



export default CreatePatientComponent;
    
