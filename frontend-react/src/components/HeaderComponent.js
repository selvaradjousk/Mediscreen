import React, { Component } from 'react'
import mediscreenLogo from '../img/mediscreen-logo.png';

class HeaderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                 
        }
    }

    render() {
        return (
                            <div>
        <div className="container"></div>
                <header>

                <nav className="navbar navbar-light bg-light justify-content-between">

                <img src={mediscreenLogo} width="240" height="60" alt="Site logo"/>
                <form className="form-inline">
  

                        <input className="form-control mr-sm-2" type="search" placeholder="Search..." aria-label="Search"/>
                        <button className="btn btn-outline-success my-2 my-sm-0" type="button"><i className="fas fa-search">🔎︎</i></button>
                    </form>
                    </nav>
                </header>
            </div>
        )
    }
}

export default HeaderComponent
