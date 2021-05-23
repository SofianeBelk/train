/* eslint-disable jsx-a11y/anchor-is-valid */
import React from 'react';
import {
    Col, Form,
    FormGroup, Input,
    Button,
    InputGroupText,
    Card,
    CardBody
} from 'reactstrap';
import listStyle from './styles/searchTrain.module.css'
import {Navbar, Nav} from 'react-bootstrap';
import Logo from "./ressources/sncf.svg";
import {
    GoogleMap, InfoWindow, withGoogleMap,
    withScriptjs,
} from "react-google-maps";

// eslint-disable-next-line no-unused-vars
const list = [
    {lat: 43.600000, lng: 1.433333},
    {lat: 44.0141948, lng: 1.3410576},
    {lat: 43.6112422, lng: 3.8767337},
    {lat: 43.1837661, lng: 3.0042121},
    {lat: 43.4014434, lng: 3.6959771},
    {lat: 43.3032635, lng: 5.3811927},
    {lat: 44.5007939, lng: 0.164274},
]

const center = function (arr) {
    var x = arr.map(xy => xy.lattitude);
    var y = arr.map(xy => xy.longitude);
    var cx = (Math.min(...x) + Math.max(...x)) / 2;
    var cy = (Math.min(...y) + Math.max(...y)) / 2;
    return [cx, cy];
}

function Map(props) {

    let centerC = [48.864716, 2.349014];
    if (props && props.length) {
        centerC = center(props);
    }
    // props.map(item => {
    //     //list.push({lat: item.lattitude,lng:  item.longitude})
    //     console.log("=====>",item)
    // })
    return (

        <GoogleMap
            defaultZoom={6.5}
            defaultCenter={{lat: centerC[0], lng: centerC[1]}}>
            {
                props && props.length &&
                props.map(item => <InfoWindow options={{maxWidth: 100}}
                                              position={{
                                                  lat: Number.parseFloat(item.lattitude),
                                                  lng: Number.parseFloat(item.longitude)
                                              }}>
                    <span>{item.villenom ? item.villenom : '--'}</span>
                </InfoWindow>)
            }
        </GoogleMap>
    );
}

const MapWrapped = withScriptjs(withGoogleMap((coordinates) => Map(coordinates.coordinates)));



export default class Main extends React.Component {


    constructor(props) {
        super(props);
        this.state = {
            origin: "",
            destinations: [],
            coordinates: [],
        }
        this.canBeSubmitted = this.canBeSubmitted.bind(this)
    }

    canBeSubmitted() {
        const {origin} = this.state;
        return (
            origin.length > 0
        )
    }

    getCoordinates = () => {
        const cities = this.state.destinations.map(item => item.fields.destination)
        let citiesParam = cities.join(',');
        fetch('/Coordinate?cities=' + citiesParam, {
            method: "GET",// mode: 'no-cors',
            credentials: 'same-origin',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'GET, OPTIONS',
                'Access-Control-Allow-Headers': 'Content-Type',
                'Access-Control-Allow-Credentials': true,
            },
        }).then((res) => {
            res.json()
            .then((rjson) => {
                this.setState({coordinates: rjson})
            })

        })
    }

    handleSubmit = (evt) => {
        evt.preventDefault()
        console.log("origin = " + this.state.origin)
        let url = '/Search?origin=' + this.state.origin;
        let headers = new Headers();
        headers.set('Accept', 'application/json');
        fetch(url, {
            method: "GET",// mode: 'no-cors',
            credentials: 'same-origin',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'GET, OPTIONS',
                'Access-Control-Allow-Headers': 'Content-Type',
                'Access-Control-Allow-Credentials': true,
            },
        })
            .then(
                (response) => {
                    response.json().then((rjson) => {
                        this.setState({destinations: rjson.records});
                        this.getCoordinates();
                    })
                        .catch((error) => {
                            alert("error  : " + error.message)
                        });
                })
            .catch(
                (error) => {
                    alert(error)
                }
            )
    }

    componentDidMount() {
        //TODO
    }

    componentWillUnmount() {
        //TODO
    }

    booking = (data) => {
        let sr = this.props;
        let url = '/ValidSession?key=' + sr.match.params.keysession;
        let headers = new Headers();
        headers.set('Accept', 'application/json');
        console.log(data)
        // {prix_d_appel_2nde: 15, plein_tarif_2nde: 50, plein_tarif_1ere: 79, destination: "TOULOUSE MATABIAU", origine: "ARLES"}
        fetch(url,
            {
                method: "GET",// mode: 'no-cors',
                credentials: 'same-origin',
                headers: {
                    'Access-Control-Allow-Origin': '*',
                    'Access-Control-Allow-Methods': 'GET, OPTIONS',
                    'Access-Control-Allow-Headers': 'Content-Type',
                    'Access-Control-Allow-Credentials': true,
                },
            }).then(
            (response) => {
                response.json()
                    .then((rjson) => {
                        if (rjson.id === 1) {
                            sr.history.push("/Booking/" +this.state.origin +"/"+ data.destination + "/" + data.plein_tarif_1ere + "/" + data.plein_tarif_2nde + "/" +
                                sr.match.params.keysession + "/" + sr.match.params.firstName);
                        } else {
                            sr.history.push("/Signup");
                        }
                    })
                    .catch((error) => {
                        alert("error  : " + error.message)
                    });
            })
            .catch(
                (error) => {
                    alert(error);
                }
            )
    }

    listHead = () => {
        return (
            <div className={listStyle.listHeader}>
                <Col sm={4}><h6>City</h6></Col>
                <Col sm={2}><h6>Rate 1</h6></Col>
                <Col sm={2}><h6>Rate 2</h6></Col>
                <Col sm={2}><h6>Call price</h6></Col>
            </div>
        );
    }

    city = (props) => {
        return (
            <div className={listStyle.gapGrid}>
                <Card>
                    <CardBody className={listStyle.cardbody}>
                        <Col sm={4}><label className={listStyle.align}>{props.fields.destination}</label></Col>
                        <Col sm={2}><label
                            className={listStyle.align}>{props.fields.plein_tarif_1ere ? props.fields.plein_tarif_1ere : '--'}</label></Col>
                        <Col sm={2}><label
                            className={listStyle.align}>{props.fields.plein_tarif_2nde ? props.fields.plein_tarif_2nde : '--'}</label></Col>
                        <Col sm={2}><label
                            className={listStyle.align}>{props.fields.prix_d_appel_2nde ? props.fields.prix_d_appel_2nde : '--'}</label></Col>
                        <Col sm={1}>
                            <Button outline color="primary" size="sm"
                                    onClick={() => this.booking(props.fields)}>Réserver</Button>
                        </Col>
                    </CardBody>
                </Card>
            </div>
        );
    };

    render() {
        return (
            <div id="sch">
                <Navbar bg="light" expand="lg">
                <Navbar.Brand href="#home">
                        <img
                            alt="logo"
                            src={Logo}
                            width="30"
                            height="30"
                            className="d-inline-block align-top"
                        />{" "}
                        SNCF
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="mr-auto">
                            <Nav.Link  href="/">Home</Nav.Link>
                            <Nav.Link href="/Signup">Signup</Nav.Link>    
                        </Nav>
                </Navbar.Collapse>
                <Navbar.Collapse class="topnav-right"> 
                        <Navbar.Text>
                               <h4>Welcome {this.props.match.params.firstName}</h4>
                        </Navbar.Text>
                     </Navbar.Collapse>
                </Navbar>
                <div className="searchForm" id="search">
                    <div className={listStyle.searchPage} style={{display: 'flex', height: '85vh'}}>
                        <Col sm={7}>
                            <div className={listStyle.searchContainer}>
                                <Form>
                                    <FormGroup row>
                                        <Col sm={5}>
                                                <InputGroupText  min={0} max={100}> Please enter the city of departure :  </InputGroupText>
                                        </Col>
                                        <Col sm={10}>        
                                                <Input type="text" name="originInput"
                                                       height= '8110vh'
                                                       placeholder="Paris, Bordeaux,...."
                                                       value={this.state.origin} onChange={(evt) => {
                                                    this.setState({origin: evt.target.value})
                                                }}/>
                                           
                                        </Col>
                                        <Col sm={2} style={{display: 'flex'}}>
                                            <Button className={listStyle.searchBtn}
                                                    type="submit"
                                                    color="primary" style={{float: 'right'}}
                                                    onClick={this.handleSubmit}>
                                                Search
                                            </Button>
                                        </Col>
                                    </FormGroup>
                                </Form>
                            </div>
                            <h4> List of destinations: </h4>
                            {this.listHead()}
                            {
                                this.state.destinations.length ?
                                    (<div className={listStyle.scrollable}>
                                        {
                                            this.state.destinations.map(item => this.city(item))
                                        }
                                    </div>) :
                                    <div className={listStyle.emptyList}>
                                        - None destination -
                                    </div>
                            }


                        </Col>
                        <Col sm={5}>
                            <div style={{height: "100%"}}>
                                <MapWrapped
                                    googleMapURL={`https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyCyWrYkLph7QhvIYq1y0wgQVe0YA7U68fY`}
                                    loadingElement={<div style={{height: `100%`}}/>}
                                    containerElement={<div style={{height: `100%`}}/>}
                                    mapElement={<div style={{height: `100%`}}/>}
                                    coordinates={this.state.coordinates}
                                />
                            </div>
                        </Col>
                    </div>
                </div>
            </div>
        )
    }

}
