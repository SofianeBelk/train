import React, { Component } from 'react';
import 'whatwg-fetch';

class Signup extends Component{


    // class ChildComponent extends React.Component { Inside the ChildComponent constructor we could access the prop:
    //     constructor(props) {
    //         super(props)
    //         console.log(props.color)
    //     }
    // }

    constructor(props) {
        super(props);

        this.state = {firstname:"", lastname:"", pseudo:"",email:"",  password:"", birthdate:"", telephone=""
        }

        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleChange(event) {
        this.setState({ signUpusername: event.target.value })
    }

    validatEntry(){
        return (this.state.firstname.length > 0 && this.state.lastname.length > 0 && this.state.pseudo.length > 0 
                && this.state.password.length > 0 && this.state.email.length && this.state.birthdate.length > 0 && this.state.telephone.length > 0
        )
    }
    validatEntry = (evt) => {
        evt.preventDefault()
        if(this.canBeSubmitted()){
            axios.post('http://localhost:8080/backend/Signup', require('querystring').stringify(this.state)).then(
                (resp) => {
                    if(resp.data.status==="ok"){
                        alert(resp.data.message)
                        this.props.setPage("Dashboard")
                    }else{
                        alert(resp.data.message)
                }
                    },(err) => {
                        alert(err)
                    }
                );
            }
    }

    render() {
        return (
                <form>
                    <h3>Sign Up</h3>
    
                    <div className="form-group">
                        <label>First name</label>
                        <input type="text" className="form-control" placeholder="First name" />
                    </div>
    
                    <div className="form-group">
                        <label>Last name</label>
                        <input type="text" className="form-control" placeholder="Last name" />
                    </div>
    
                    <div className="form-group">
                        <label>Email address</label>
                        <input type="email" className="form-control" placeholder="Enter email" />
                    </div>
    
                    <div className="form-group">
                        <label>Password</label>
                        <input type="password" className="form-control" placeholder="Enter password" />
                    </div>
    
                    <button type="submit" className="btn btn-primary btn-block">Sign Up</button>
                    <p className="forgot-password text-right">
                        Already registered <a href="#">sign in?</a>
                    </p>
                </form>
            );
  }

}

export default Signup;
