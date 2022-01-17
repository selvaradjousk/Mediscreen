import React, { Component } from 'react'
import NoteService from '../services/NoteService';

export default class UpdateNoteComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
          // step 2
          noteId:this.props.match.params.noteId,
          patientId:this.props.match.params.patientId,
          id: this.props.match.params.id,
          date: '',
          note: '',
  //        currentDate: new Date().toLocaleString(),
        }
        console.log('this.state.id : ' + this.id);


        this.changeNoteDateHandler = this.changeNoteDateHandler.bind(this);
        this.changeNoteHandler = this.changeNoteHandler.bind(this);
        this.updateNote = this.updateNote.bind(this);


    }

    componentDidMount(){
        NoteService.getNoteById(this.state.noteId).then((res) => {
            let note = res.data;
            this.setState({
                date: note.date,
                note: note.note

            });
        });
    }




    updateNote = (e) => {
        e.preventDefault();
        let updatedNote = {noteId: this.state.noteId, patientId: this.state.patientId, date: this.state.date, note: this.state.note};
        console.log('note => ' + JSON.stringify(updatedNote))


        NoteService.updateNote(updatedNote, this.state.noteId).then( res => {
            this.props.history.push(`/list-note/${this.state.patientId}`);

    });
}



cancel() {
   this.props.history.push(`/list-note/${this.state.patientId}`);
  }




// something here
    
    changeNoteDateHandler= (event) => {
        this.setState({date: event.target.value});
    }
 
    changeNoteHandler= (event) => {
        this.setState({note: event.target.value});
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
         <a href="/patient/list/"><button type="button" className="btn btn-primary bg-warning btn-sm mr-2 mb-2 font-weight-bold text-dark btn btn-outline-light"> ☛ Return to Patient List</button></a>
      </div>
   </div>
   <div className="row">
      <h2 className="text-info">Update Note</h2>
   </div>
   <br/>
   <div className="row">
      <div className = "card-body">
         <form>

            <div className="form-group">
               <label className="col-sm-2 control-label">Note Date</label>
               <div className="col-sm-10">
                  <input placeholder="Note Date" name="date" className="form-control" 
                     value={this.state.date} onChange={this.changeNoteDateHandler}/>
               </div>
            </div>
            <div className="form-group">
               <label className="col-sm-2 control-label">Note</label>
               <div className="col-sm-10">
                  <input placeholder="Note" name="note" className="form-control" 
                     value={this.state.note} onChange={this.changeNoteHandler}/>
               </div>
            </div>
 
            {/*
            <div className="form-group">
               <div className="col-sm-12">
                  <a className="btn btn-danger btn-sm" href="/note/list">Cancel</a>
                  <input className="btn btn-primary btn-sm" type="submit" value="Add note"/>
               </div>
            </div>
            */}
            <div className="form-group">
               <div className="col-sm-12">
                  <span><button onClick={this.updateNote}  className="btn btn-primary btn-sm "> Update</button>&nbsp;&nbsp;
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
