import React, {Component} from 'react';
import assessService from '../services/AssessService';



export default class AssessComponent extends Component {

    constructor(props){
        super(props)
        this.state = {
            // step 2
        id: this.props.match.params.id,
        lastName: '',
        firstName: '',
        patientAge: '',
        diabetesRiskLevel: '',
        report: [],
        patientDTO: []
          }

   console.log('generate report called for id: ' + this.state.id);

    }


    async componentDidMount(){
        assessService.generateReport(this.state.id).then((Response) =>{
           let report = Response.data;
           this.setState({
            lastName: report.patientDTO.lastName,
            firstName: report.patientDTO.firstName,
            patientAge: report.patientAge,
            diabetesRiskLevel: report.diabetesRiskLevel,   

        });
        console.log(report.patientDTO.lastName);
        console.log('Response: ' + Response.data);
        console.log('report: ' + report);
        console.log('report.lastName: ' + report.lastName);
        console.log('report.firstName: ' + report.firstName);
        console.log('report.patientAge: ' + report.patientAge);
        console.log('report.diabetesRiskLevel: ' + report.diabetesRiskLevel);
      });
    }





    getRiskLevelColor() {
      switch (this.assess.diabetesRiskLevel) {
        case 'None': {
          return '#d9e1f2';
         }
        case 'Borderline': {
          return '#c6e0b4';
        }
        case 'In danger': {
          return '#ffe699';
        }
        case 'Early onset': {
          return '#ff9999';
        }
        default: {
          return '';
        }
      }
    }



render(){

  /*  const {assesss, isLoading} = this.state;


    if (isLoading) {
        return <p>Loading...</p>;
      }*/
  
      const riskLevel = this.state.diabetesRiskLevel
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
   <div className="row mt-4">
     <div className="col-6">
     </div>
     <div className="col-6 text-right">
        <br /> <br /> <br /> <br />
        <a href="/patient/list"><button type="button"
           className="btn btn-primary bg-warning btn-sm mr-2 mb-2 font-weight-bold text-dark btn btn-outline-light"> ☛ Return
        to Patient List</button></a>
     </div>
  </div>
   <div className="row mt-4">
      <h2 className="text-info">Diabete risk possibility assessment report</h2>
   </div>
   <br/><br/>

   <br/><br/>
   <div className="App-intro">
   </div>
   <br /><br /><br />
<div className="row ">
   <table className = "table table-bordered">
      <thead>
         <tr>
         <th className="text-center">Last name </th>
           <th className="text-center">First name</th>
           <th className="text-center">Age</th>
           <th className="text-center">Diabetes assessment is:</th>
         </tr>
      </thead>
      <tbody>
         <tr>
            <td style={{width: "10%",  backgroundColor: riskLevel ==='Early onset' ? '#ff9999' : riskLevel ==='In danger' ? '#ffe699' : riskLevel === 'Borderline'? '#c6e0b4': '#d9e1f2' }}>{this.state.lastName}</td>
            <td style={{width: "10%",  backgroundColor: riskLevel ==='Early onset' ? '#ff9999' : riskLevel ==='In danger' ? '#ffe699' : riskLevel === 'Borderline'? '#c6e0b4': '#d9e1f2' }}>{this.state.firstName}</td>
            <td style={{width: "5%",  backgroundColor: riskLevel ==='Early onset' ? '#ff9999' : riskLevel ==='In danger' ? '#ffe699' : riskLevel === 'Borderline'? '#c6e0b4': '#d9e1f2' }}>{this.state.patientAge}</td>
            <td style={{width: "25%",  backgroundColor: riskLevel ==='Early onset' ? '#ff9999' : riskLevel ==='In danger' ? '#ffe699' : riskLevel === 'Borderline'? '#c6e0b4': '#d9e1f2' }} >{this.state.diabetesRiskLevel}</td>
         </tr>
     
      </tbody>
   </table>
</div>
</div>

    )

}

}

