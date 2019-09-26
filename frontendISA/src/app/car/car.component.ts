import {Component, Inject, Injectable, OnInit} from '@angular/core';
import {Car} from './car';
import {CarServiceService} from './car-service.service';
import {RentaCarServiceService} from '../rent-acar/renta-car-service.service';
import {CarService} from '../rent-acar/carService';
import {Route, Router} from '@angular/router';
import {AuthenticationService} from '../service/authentication.service';
import {carType} from './carType';




@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})


export class CarComponent implements OnInit {
  private listOfCars: Car[];
  private listOfCServices: CarService[];
  public updateCar  = new Car(0, '', '', 0, null, '', '', false, false, false, null, 0, 0);
  public types: carType[] = [];
  ime: string;
  id: number;
  newType:carType = new carType('',0,0,0);
  nos = -5;
  from = 0;
  to = 3000;
  model = '';
  type = '';
  tid :number;
  deleteREGID: string;
  public car  = new Car(0, '', '', null, null, '', '', false, false, false, null, 0, 0);
  public radioModel: string;
  display = 'none';
  display2 = 'none';


  constructor(private loginService: AuthenticationService, private carService: CarServiceService, private rentacar: RentaCarServiceService, public router: Router) {

    this.router.routeReuseStrategy.shouldReuseRoute = function() {
      return false;
    };

  }

  ngOnInit() {

    this.search();
    this.carService.getTypes().subscribe(
      data => this.types = data
    );

  }

  onKey(event) {this.model = event.target.value; }
  onKey2(event) {this.type = event.target.value; }
  onKey3(event) {this.from = event.target.value; }
  onKey4(event) {this.to = event.target.value; }
  onKey5(event) {this.nos = event.target.value; }

  search() {
    if (String(this.from) === '') {
      this.from = 0;
    }
    if (String(this.to) === '') {
      this.to = 3000;
    }
    if (String(this.nos) !== 'a') {
      this.nos = -5;
    }



    this.carService.search(this.model, this.type, this.from, this.to, this.nos).subscribe(
      data => {
        this.listOfCars = data;
        this.rentacar.getAllCarServices().subscribe(
          listOfCservices => this.listOfCServices = listOfCservices
        );
      }

      );
  }


  isUserReservedCar(id:number){
    this.rentacar.isUserReservedCar(id, Number(sessionStorage.getItem('id'))).subscribe();
  }

  addCar() {
    if (this.radioModel === 'Yes') {
      this.car.convertible = true;
    } else {
      this.car.convertible = false;
    }
    this.car.serviceId = this.id;
  this.car.typeId=this.tid;

    this.carService.addNewCar(this.car).subscribe(
      data => {
        if (data === 'SUCCESS') {
          alert('Uspesno');
          this.router.navigated = false;
          this.router.navigate([this.router.url]);

        } else { alert('ERROR!'); }
      }, error => {console.log(error); }


    );
  }


  getCarForUpdate(reg: string) {
    this.carService.getCarForUpdate(reg).subscribe(
      data => { this.updateCar = data;
                this.openModalDialog2();
      }, error => {console.log(error); }


    );
  }



  addCarForUpdate() {
    if (this.radioModel === 'Yes') {
      this.updateCar.convertible = true;
    } else {
      this.updateCar.convertible = false;
    }
    this.carService.addModifiedCar(this.updateCar.model, this.updateCar.typeC, this.updateCar.year, this.updateCar.convertible, this.updateCar.regID, this.updateCar.serviceId, this.updateCar.id).subscribe(
      data => {
        alert(data);
        this.router.navigated = false;
        this.router.navigate([this.router.url]);
      }, error => {console.log(error); }


    );
  }




  openModalDialog() {
    this.display = 'block'; // Set block css
  }

  closeModalDialog() {
    this.display = 'none'; // set none css after close dialog
  }

  openModalDialog2() {
    this.display2 = 'block'; // Set block css
  }

  closeModalDialog2() {
    this.display2 = 'none'; // set none css after close dialog
  }

  deleteCar(regid: string) {
    if (this.radioModel === 'Yes') {
      this.car.convertible = true;
    } else {
      this.car.convertible = false;
    }
    this.car.serviceId = this.id;
    this.carService.deleteCar(regid).subscribe(
      data => {
        if (data === 'SUCCESS') {
          alert('SUCCESS');
          this.router.navigated = false;
          this.router.navigate([this.router.url]);

        } else { alert('ERROR!'); }
      }, error => {console.log(error); }


    );
  }



}
