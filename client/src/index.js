import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Main from './home';
import Signup from './register';
import BookPage from './book';
import TicketPage from './ticket';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter, Route} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

ReactDOM.render(
  
  <BrowserRouter>
    
    <Route exact path="/" component={Main} />
    <Route     path="/Main/:keysession/:firstName" component={Main} />
    <Route     path="/Signup" component={Signup} />
    <Route     path="/Booking/:origin/:destination/:tarif1/:tarif2/:keysession/:Name" component={BookPage} />
    <Route     path="/Ticket/:userName/:stationorigin/:stationdestination/:tickettarif/:promocode/:keysession" component={TicketPage} />
  </BrowserRouter>,
  document.getElementById('root')
);

reportWebVitals();
