import React from 'react';
import {
    Container, Col, Form,
    FormGroup, Label, Input,
    ButtonToggle,
    InputGroupAddon,
    InputGroup,
  } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Document, Page, Text, View, StyleSheet } from "@react-pdf/renderer";

const styles = StyleSheet.create({
    page: {
      flexDirection: "row"
    },
    section: {
      flexGrow: 1
    }
  });


  // eslint-disable-next-line no-unused-vars
  const MyDocument = (
    <Document>
      <Page size="A4" style={styles.page}>
        <View style={styles.section}>
          <Text>Hello World!</Text>
        </View>
        <View style={styles.section}>
          <Text>We're inside a PDF!</Text>
        </View>
      </Page>
    </Document>
  );
  
  
class BookPage extends React.Component{
   
    
constructor(props){
    super(props);
    console.log("here  ::  ", this.props.match.params);
    this.state = {value: 'plein_tarif_1ere'};
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
}

// handleSubmit(event) {
//         console.log("generate pdf hereee...")
//         console.log(this.props.match.params.Name)
//         console.log(this.props.match.params.destination)
//         if(this.state.value === "plein_tarif_1ere"){
//           console.log('tarif 1 : ' + this.props.match.params.tarif1);
//         }else{
//           console.log('tarif 2 : ' + this.props.match.params.tarif2);
//         }
//         event.preventDefault();
// }

handleSubmit = (evt) => {
     alert("Réservation Success.")
     let tarifm = "";
        if(this.state.value === "plein_tarif_1ere"){
          tarifm = this.props.match.params.tarif1;
        }else{
          tarifm = this.props.match.params.tarif2;
        }

     evt.preventDefault()
      //path="/Ticket/:userName/:stationorigin/:stationdestination/:tickettarif/promocode"

       let url = '/PromoCodeGen?userName='+this.props.match.params.Name+'&destination='+this.props.match.params.destination;
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
       }).then((res) => {
              res.json().then((rjson) => {
                console.log("code promo : ",rjson.promo)
                this.props.history.push(
                  "/Ticket/"+
                  this.props.match.params.Name+"/"+
                  this.props.match.params.origin+"/"+
                  this.props.match.params.destination+"/"+
                  tarifm+"/"+
                  rjson.promo+"/"+
                  this.props.match.params.keysession
              )
            
        })
    })
    }

    handleChange(event) {
      this.setState({value: event.target.value});
    }
 
    
    render(){
        return (
            <Container className="bookForm"  >
                <div> <h1> Booking </h1></div>
            <div className="bookContainer">
                <Form>
                <FormGroup row>
                   
                    <Col sm={10}>

                    <InputGroup>
                        <InputGroupAddon addonType="prepend">
                        </InputGroupAddon>

                        <Label sm={2}>Origin  :  </Label>
                        <Input type="text" name="destInput" placeholder={this.props.match.params.origin}
                               value={this.props.match.params.origin} 
                               onChange={(evt) => {this.setState({origin:evt.target.value}) }}
                                />
                     </InputGroup>  

                    <InputGroup>
                        <InputGroupAddon addonType="prepend">
                        </InputGroupAddon>

                        <Label sm={2}>Destination  :  </Label>
                        <Input type="text" name="destInput" placeholder={this.props.match.params.destination}
                               value={this.props.match.params.destination} 
                               onChange={(evt) => {this.setState({origin:evt.target.value}) }}
                                />
                     </InputGroup>                            
                     </Col>
                     </FormGroup>
                          <FormGroup row>
                            <Col sm={10}>
                            <InputGroup>
                            <InputGroupAddon addonType="prepend">
                       
                           </InputGroupAddon>
                          <Label sm={2}> plein_tarif_1ere  :  </Label>
                          <Input type="text" name="tarif1Input" placeholder={this.props.match.params.tarif1}
                               value={this.props.match.params.tarif1} 
                               onChange={(evt) => {this.setState({origin:evt.target.value}) }}
                            />

                    </InputGroup>                            
                      </Col>
                          </FormGroup>

                      <FormGroup row>
                      <Col sm={10}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                        </InputGroupAddon>
                        <Label sm={2}> plein_tarif_2nde  :  </Label>
                          <Input type="text" name="tarif2Input" placeholder={this.props.match.params.tarif2}
                               value={this.props.match.params.tarif2} onChange={(evt) => 
                               {this.setState({origin:evt.target.value}) }}
                            />     

                    </InputGroup>                            
                    </Col>
                </FormGroup>
                <FormGroup row>
                    <Col sm={10}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend"></InputGroupAddon>
                        <Label sm={2}> Tarif  :  </Label>
                          <select value={this.state.value} onChange={this.handleChange}>
                            <option value="plein_tarif_1ere">plein_tarif_1ere</option>
                            <option value="plein_tarif_2nde">plein_tarif_2nde</option>
                        </select>
                      </InputGroup>                            
                    </Col>
                </FormGroup>
            </Form>
            <ButtonToggle className="sendBtn" type="submit" 
                style={{float : 'right'}} 
                onClick={this.handleSubmit} >Book</ButtonToggle>
            </div>
        </Container>
        )
    }

}
export default BookPage;

