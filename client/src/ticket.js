import jsPDF from 'jspdf';
import 'jspdf-autotable';

import React from 'react';
import {
    Col, 
    Button,
    Card,
    CardBody
} from 'reactstrap';
import ticketStyle from './styles/ticket.module.css'
  
export default class TicketPage extends React.Component{
   
    
constructor(props){
    super(props);
    console.log("ticket page..");
    var today = new Date(),
    date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate()+'  '+today.getHours()+':'+today.getMinutes()+':'+today.getSeconds()  ;
    this.state = {
      currentDate: date
    }
}

generatePDF = (data) => {
    console.log("generate pdf hereee.")

    let userName  = this.props.match.params.userName;
    let departure = this.props.match.params.stationorigin;
    let arrival   = this.props.match.params.stationdestination;
    let tarif   = this.props.match.params.tickettarif;
    let promocode   = this.props.match.params.promocode;
    let currenttime = this.state.currentDate ;

    var doc = new jsPDF('landscape','px','a4','false');
    var columns = ["ID", "FirstName", "Departure", "Arrival", "Tarif", "PromoCode"];
    var rows = [
       [1, userName, departure, arrival, tarif, promocode]
    ];

    doc.text(30,20,"Train Ticket : ")
    doc.text(490,20,currenttime)
    doc.autoTable(columns, rows);


    doc.save('ticket.pdf')   
    this.props.history.push("/Main/"+this.props.match.params.keysession+"/"+this.props.match.params.userName)
}

listHead = () => {
    return (
        <div className={ticketStyle.listHeader}>
            <Col sm={4}><h6>FirstName</h6></Col>
            <Col sm={2}><h6>Origin</h6></Col>
            <Col sm={2}><h6>Distination</h6></Col>
            <Col sm={2}><h6>Tarif</h6></Col>
            <Col sm={2}><h6>PromoCode</h6></Col>
        </div>
    );
}
//path="/Ticket/:userName/:stationorigin/:stationdestination/:tickettarif/"
render() {
    return (
        <div id="sch" className={ticketStyle.center} >
                    <Col sm={7}>
                        <h4> Ticket : </h4>
                        {this.listHead()}
                        <div className={ticketStyle.gapGrid}>
                        <Card>
                            <CardBody className={ticketStyle.cardbody}>
                                <Col sm={4}><label 
                                    className={ticketStyle.align}>{this.props.match.params.userName}</label></Col>
                                <Col sm={2}><label
                                    className={ticketStyle.align}>{this.props.match.params.stationorigin}</label></Col>
                                <Col sm={2}><label
                                     className={ticketStyle.align}>{this.props.match.params.stationdestination}</label></Col>
                                <Col sm={2}><label
                                    className={ticketStyle.align}>{this.props.match.params.tickettarif}</label></Col>
                                <Col sm={2}><label
                                    className={ticketStyle.align}>{this.props.match.params.promocode}</label></Col>
                            </CardBody>
                         </Card>
                         <div><h4> </h4></div>
                         <Button outline color="primary" size="sm" style={{float: 'right'}} 
                                  onClick={() => this.generatePDF(this.props.match.params)}>DownloadPDF</Button>
                    </div>
                    </Col>
        </div>
    )
}


}

