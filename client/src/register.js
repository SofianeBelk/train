import React from 'react';
import 'whatwg-fetch';
import 'bootstrap/dist/css/bootstrap.min.css';
import regstyle from './styles/register.module.css';
import './styles/register.css'


class Signup extends React.Component{

    constructor(props) {
        super(props);
  
        this.state = {firstname:"",
                      lastname:"",
                      pseudo:"",
                      email:"",
                      password:"", 
                      age:"",
                      telephone:"",
                      pseudoLogin:"",
                      passwordLogin:""}
      this.myInputRef = React.createRef();
    }

    
    handleChange(event) {
        this.setState({ signUpusername: event.target.value })
    }

    signup = (evt) => {
        alert("Registration Succeeded")
        evt.preventDefault()
        let url = '/Registration?nom='+this.state.firstname+'&prenom='+this.state.lastname+'&pseudo='+this.state.pseudo+'&motDePasse='+this.state.password+'&email='+this.state.email+'&age='+this.state.age+'&telephone='+this.state.telephone;
        let headers = new Headers();
        headers.set('Accept', 'application/json');
        fetch(url, {method : "GET",
        credentials: 'same-origin',
        headers: {
            'Access-Control-Allow-Origin':'*',
            'Access-Control-Allow-Methods': 'GET, OPTIONS',
            'Access-Control-Allow-Headers' : 'Content-Type',
            'Access-Control-Allow-Credentials' : true,
         },  
        }).then((response) =>{
        response.json()
         .then((rjson)=> {
             console.log(rjson)
             if(rjson.error===1) {
                alert(" nom or prenom or pseudo or email or pass or telephone is Null")
             }else if(rjson.error===2) {
                alert(" user already exist")
             }else {
                this.props.history.push("/Main/"+rjson.session.key+"/"+this.state.firstname)
             }
            })
            .catch((error)=>{
                          alert("error  : "+error.message) });
        })
    .catch(
          (error) => {
          alert(error)
         }
    )
 }

    signin = (evt) => {
        alert("Authentication Succeeded")
        evt.preventDefault()
        let url = '/Authentification?pseudo='+this.state.pseudoLogin+'&motDePasse='+this.state.passwordLogin;
        let headers = new Headers();
        headers.set('Accept', 'application/json');
        fetch(url, {method : "GET",
        credentials: 'same-origin',
        headers: {
            'Access-Control-Allow-Origin':'*',
            'Access-Control-Allow-Methods': 'GET, OPTIONS',
            'Access-Control-Allow-Headers' : 'Content-Type',
            'Access-Control-Allow-Credentials' : true,
    },   
    }).then(
    (response) =>{
        response.json()
         .then((rjson)=> {
             console.log(rjson)
             if(rjson.error===1) {
                alert(" usrname  or  password  is Null")
             }else if(rjson.error===2) {
                alert(" username n'existe pas ")
             }else if(rjson.error === 3) {
                alert(" Mauvaise password ")
             }else {
                 this.props.history.push("/Main/"+rjson.session.key+"/"+this.state.pseudoLogin)
             }
            })
            .catch((error)=>{
                          alert("error  : "+error.message) });
        })
    .catch(
          (error) => {
          alert(error)
         }
    )
    }

    getButtonId = () => {
        const signUpButton = document.getElementById('signUp');
        const signInButton = document.getElementById('signIn');
        const container = document.getElementById('container');
        
        signUpButton.addEventListener('click', () => {
            container.classList.add("right-panel-active");
        });
        
        signInButton.addEventListener('click', () => {
            container.classList.remove("right-panel-active");
        });       
     }

    render() {
        return (
        <html>
          <head title='React and CSS Modules' />
            <body className={regstyle.body}>
            <div class="containerPanel" id="container">
                <div class="form-container sign-up-container">
                    <form className = {regstyle.form} action="#">
                        <h3>Create Account</h3>
                        <div class="social-container">
                            <a href="xx" class="social"><i class="fab fa-facebook-f"></i></a>
                            <a href="xx" class="social"><i class="fab fa-google-plus-g"></i></a>
                            <a href="xx" class="social"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                        <span>or use your email for registration</span>
                        <input type="text"  placeholder="First name" value={this.state.firstname} onChange={(evt) => {this.setState({firstname:evt.target.value}) }}/>
                        <input type="text"  placeholder="Last name"  value={this.state.lastname} onChange={(evt) => {this.setState({lastname:evt.target.value}) }} />
                        <input type="text"  placeholder="pseudo"     value={this.state.pseudo} onChange={(evt) => {this.setState({pseudo:evt.target.value}) }} />
                        <input type="text"  placeholder="age"        value={this.state.age} onChange={(evt) => {this.setState({age:evt.target.value}) }} />
                        <input type="email" placeholder="email"      value={this.state.email} onChange={(evt) => {this.setState({email:evt.target.value}) }}/>
                        <input type="password" placeholder="password" value={this.state.password} onChange={(evt) => {this.setState({password:evt.target.value}) }}/>
                        <button type="submit" onClick={this.signup} >Sign Up</button>
                    </form>
                </div>
                <div class="form-container sign-in-container">
                    <form className = {regstyle.form} action="#">
                        <h1>Sign in</h1>
                        <div class="social-container">
                            <a href="x" class="social"><i class="fab fa-facebook-f"></i></a>
                            <a href="x" class="social"><i class="fab fa-google-plus-g"></i></a>
                            <a href="x" class="social"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                        <span>or use your account</span>
                        <input type="text"    placeholder="username"    value={this.state.pseudoLogin} onChange={(evt) => {this.setState({pseudoLogin:evt.target.value}) }} />
                        <input type="password" placeholder="password" value={this.state.passwordLogin} onChange={(evt) => {this.setState({passwordLogin:evt.target.value}) }} />
                        <a href="x">Forgot your password?</a>
                        <button type="submit" onClick={this.signin} >Sign In</button>
                    </form>
               </div>
                <div class="overlay-container">
                    <div class="overlay">
                        <div class="overlay-panel overlay-left">
                            <h1>Welcome Back!</h1>
                            <p>To keep connected with us please login with your personal info</p>
                            <button class="ghost" id="signIn" onClick={this.getButtonId}>Sign In Now</button>
                        </div>
                        <div class="overlay-panel overlay-right">
                            <h1>Hello, Friend!</h1>
                            <p>Enter your personal details and start journey with us</p>
                            <button class="ghost" id="signUp" onClick={this.getButtonId} >Sign Up Now</button>
                        </div>
                    </div>
                </div>
            </div>    
            </body>
          </html>
        );
    }

}

export default Signup;
