import React, { Component } from 'react';
import './css/App.css';
import './css/custom.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import HomeComponent from './components/HomeComponent';
import PatientComponent from './components/PatientComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreatePatientComponent from './components/CreatePatientComponent';
import UpdatePatientComponent from './components/UpdatePatientComponent';
import NoteComponent from './components/NoteComponent';
import UpdateNoteComponent from './components/UpdateNoteComponent';
import CreateNoteComponent from './components/CreateNoteComponent';
import AssessComponent from './components/AssessComponent';



class App extends Component {
  render() {
  return (
    <div>
        <Router>
              <HeaderComponent />
                <div className="container">
                    <Switch> 
                          <Route path = "/" exact component = {HomeComponent}></Route>
                          <Route path = "/patient/list" exact component = {PatientComponent}></Route>
                          <Route path = "/add-patient" exact component = {CreatePatientComponent}></Route>
                          <Route path = "/edit-patient/:id" exact component = {UpdatePatientComponent}></Route>
                          <Route path = "/list-note/:id" exact component = {NoteComponent}></Route>
                          <Route path = "/edit-note/:patientId/:noteId" exact component = {UpdateNoteComponent}></Route>
                          <Route path = "/add-note/:patientId" exact component = {CreateNoteComponent}></Route>
                          <Route path = "/assess/:id" exact component = {AssessComponent}></Route>
                          
                    </Switch>
                </div>
              <FooterComponent />
        </Router>
    </div>
    
  );
}
}

export default App;
