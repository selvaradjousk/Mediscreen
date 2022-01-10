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
                          
                    </Switch>
                </div>
              <FooterComponent />
        </Router>
    </div>
    
  );
}
}

export default App;
