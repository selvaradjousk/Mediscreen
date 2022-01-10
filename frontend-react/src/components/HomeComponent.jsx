import React, { Component } from 'react'
import mediscreenPeople from '../img/mediscreen-people.png';


class ListEmployeeComponent extends Component {
 

    render() {
        return (
            <div>
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



        <div className="container">
        <div className="row">
          <div className="col-12 col-md-12 order-lg-2 col-lg-9 px-0">
            <img src={mediscreenPeople} className="img-fluid" />
          </div>
          <div className="col-12 col-md-12 order-lg-1 col-lg-3 px-1 pt-2 text-left ">
            <p>
            </p><h2 className="text-primary text-bold font-weight-bold text-info pt-4">We care.</h2><br />
            Mediscreen specializes in detecting risk factors for disease.
            Our screenings using predictive analysis of patient populations at
            an affordable cost.
            <p />
            <ul>
              <li>Are you a small or rural clinic or practice?</li>
              <li>Do you nedd screenings for heart disease or hypertension precursors?</li>
            </ul>
            <h5 className="text-primary text-bold font-weight-bold text-info pt-4">We have a solution for you.</h5>
          </div>
        </div>
      </div>



           </div>





        )
    }
}

export default ListEmployeeComponent
