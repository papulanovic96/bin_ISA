import { Component, OnInit, ViewChild } from '@angular/core';
import {Car} from "../car/car";
import {CarService} from "./carService";
import {RentaCarServiceService} from "./renta-car-service.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "../service/authentication.service";
import {carReservation} from "../car/carReservation";
import {carRate} from "../car/carRate";
import {carServiceRate} from "./carServiceRate";
import {datepickerAnimation} from "ngx-bootstrap/datepicker/datepicker-animations";
import {CarServiceService} from "../car/car-service.service";
import {CarDiscount} from './carDiscount';


@Component({
  selector: 'app-rent-acar',
  templateUrl: './rent-acar.component.html',
  styleUrls: ['./rent-acar.component.css']
})
export class RentACarComponent implements OnInit {

  private listOfCarServices: CarService[];
  private listOfCarServices2: CarService[];
  public listOfCars: Car[];
  public cars:Car[];
  public updateCarService  = new CarService(0,null,'','','','',false);
  ime : string;
  deleteID : string;
  public newCarService  = new CarService(0,null,'','','','',false);
  public newCarService2  = new CarService(0,null,'','','','',false);

  public radioModel:string
  display='none';
  path:string = 'frontendISA/src/assets/rentCar.png';
  display2='none';
  display4='none';
  display5='none';
  display6='none';
  display7='none';
  address:string = '';
  name:string = '';
  serviceIDForSearch:number = -5;
  bsValue = new Date();
  bsRangeValue: Date[];
  maxDate = new Date();
  able:boolean=false;
  show:boolean = false;
  model:string='';
  type:string='';
  from:number = 0;
  to:number = 3000;
  nos:number = -5;
  public display3 = 'none';
  x: number = 5;
  y: number = 2;
  searchFlag:boolean = false;
  today=new Date();
  startDate = new Date();
  public listOfFastReservations: CarDiscount[] = [];
  public carFastReservation = new carReservation(null,null,null,0,0);
  dFast = 'none';
  endDate = new Date();
  public idCar : number =0;
  public CarRate = new carRate(0,0,null,0);
  public CarServiceRate = new carServiceRate(null,0,0,null);
  public CarReservation = new carReservation(null,null,null,0,0);
  constructor(private carservice:CarServiceService,private rentacar : RentaCarServiceService, public router:Router,public loginService:AuthenticationService) {

    this.router.routeReuseStrategy.shouldReuseRoute = function(){
      return false;
    }
  }

  onKey(event) {this.address = event.target.value;}
  onKey2(event) {this.name = event.target.value;}


  onKeyM(event) {this.model = event.target.value;}
  onKeyT(event) {this.type = event.target.value;}
  onKeyF(event) {this.from = event.target.value;}
  onKeyTo(event) {this.to = event.target.value;}
  onKeyN(event) {this.nos = event.target.value;}


  ngOnInit() {
    this.search();
  }
  disp(){
    alert(this.y);
  }


  closeFastModal() {
    this.dFast = 'none';
  }

  setFastReservation(idCar:number,startDate:Date,endDate:Date){
    this.carFastReservation.endDate = endDate;
    this.carFastReservation.startDate = startDate;
    this.carFastReservation.carId = idCar;
    this.carFastReservation.id = null;
    this.carFastReservation.userId = Number(sessionStorage.getItem('id'));
    this.rentacar.setReservations(this.carFastReservation).subscribe(
      data=> {
        alert("SUCCESS!");
        this.dFast = 'none';
      },error1 => alert("ERROR")
      );
  }

  fastReservation() {
    this.rentacar.getForFastReservation().subscribe(
      data => {
        this.listOfFastReservations = data;
        this.dFast = 'block';
      },
      error1 => alert('ERROR')
    );
  }



  searchCars(){
    if(String(this.from)==="")
      this.from = 0;
    if(String(this.to)==="")
      this.to= 3000;
    this.nos =-5;
    this.type='';

    this.carservice.search(this.model,this.type,this.from,this.to,this.nos).subscribe(
      data=> {
        this.listOfCars = data;
        this.cars =[];


        for (let c of data) {
          if (c.serviceId === this.serviceIDForSearch) {
            this.cars.push(c);
          }
        }
        this.listOfCars = this.cars;

        this.display3='none';
        this.display3='display';



      }

    );
  }



  search(){
    this.rentacar.search(this.address,this.name).subscribe(
      data=> {this.listOfCarServices = data;

      },
      error1 => alert(error1)
    );
  }



  tryToRateService(idService:number) {
    this.rentacar.tryToRateServices(idService).subscribe(
      data=>{
        if(data === true){
          this.CarServiceRate.serviceID = idService;
          this.openModalDialog7();
        }else{
          alert("YOU CAN'T RATE");
        }
      },error1 => alert("Exception")
    );
  }


  rateService(){
    this.CarServiceRate.userID = Number(sessionStorage.getItem("id"));
    this.rentacar.rateCarService(this.CarServiceRate).subscribe(
      data=> {

        alert(data);
        this.closeModalDialog7();
        this.router.navigated = false;
        this.router.navigate([this.router.url]);

      },
      error1 => alert(error1)
    );}

  isUserReservedCar(id:number){
    this.rentacar.isUserReservedCar(id, Number(sessionStorage.getItem('id'))).subscribe();
  }

  isUserAbleToRate(id:number){
    this.rentacar.isUserAbleToRate(id).subscribe(

    );
  }
  isUserAbleToUnreserve(id:number){
    this.rentacar.tryToUnreserve(id).subscribe(

    );
  }

  rateCar(){
    this.CarRate.userID = Number(sessionStorage.getItem("id"));
    this.rentacar.rateCar(this.CarRate).subscribe(
      data=> {
        alert(data);
        this.closeModalDialog5();
        this.router.navigated = false;
        this.router.navigate([this.router.url]);

      }
    );
  }

  setId(id:number){
    this.CarRate.carID=id;
    this.openModalDialog5();
  }

  setReseravtion(id:number){
    if(this.startDate>this.endDate) {
      alert("WRONG DATE ARRAGEMENT!");


    }
    else{

      this.CarReservation.carId=id;
      this.CarReservation.startDate = this.startDate;
      this.CarReservation.endDate = this.endDate;
      this.CarReservation.userId = Number(sessionStorage.getItem('id'));
      this.rentacar.setReservations(this.CarReservation).subscribe(
        data=>{
          alert("RESERVED");
          this.closeModalDialog4();
          this.closeModalDialog3();
          this.router.navigated = false;
          this.router.navigate([this.router.url]);
        }
      );
    }
  }



  showS(){
    this.show=true;
  }

  middleWear(id:number){
    this.idCar = id;
    this.openModalDialog4();
  }
  getCars(name:string,id:number){
    this.serviceIDForSearch = id;
    this.rentacar.getAllCars(name).subscribe(
      data=> {
        this.listOfCars = data;

        this.openModalDialog3();
      });
  }

  openModalDialog3(){
    this.display3='block'; //Set block css
  }


  closeModalDialog3(){
    this.display3='none'; //set none css after close dialog
  }

  openModalDialog4(){
    this.display4='block'; //Set block css
  }


  closeModalDialog4(){
    this.display4='none'; //set none css after close dialog
  }

  openModalDialog5(){
    this.display5='block'; //Set block css
  }


  closeModalDialog5(){
    this.display5='none'; //set none css after close dialog
  }

  openModalDialog6(){
    this.display6='block'; //Set block css
  }


  closeModalDialog6(){
    this.display6='none'; //set none css after close dialog
  }

  openModalDialog7(){
    this.display7='block'; //Set block css
  }


  closeModalDialog7(){
    this.display7='none'; //set none css after close dialog
  }


  openModalDialog(){
    this.display='block'; //Set block css
  }

  closeModalDialog(){
    this.display='none'; //set none css after close dialog
  }

  openModalDialog2(){
    this.display2='block'; //Set block css
  }

  closeModalDialog2(){
    this.display2='none'; //set none css after close dialog
  }

  addCarService(){
    this.rentacar.addNewCarService(this.newCarService).subscribe(
      data=> {alert(data)
        this.router.navigated = false;
        this.router.navigate([this.router.url]);
      },
      error1 => alert(error1)
    );
  }
  deleteCarService(id:number){
    this.rentacar.deleteCarService(id).subscribe(
      data=> {
        alert(data);
        this.router.navigated = false;
        this.router.navigate([this.router.url]);
      },
      error1 => alert(error1)
    );
  }

  getCarServiceForUpdate(id:number){
    this.rentacar.getCarServiceForUpdate(id).subscribe(
      data => { this.updateCarService = data;
        this.openModalDialog2();
      },error => {console.log(error)}


    );
  }

  getCarService(id:number){
    this.rentacar.getCarServiceForUpdate(id).subscribe(
      data => { this.newCarService2 = data;
        this.openModalDialog6();
      },error => {console.log(error)}


    );
  }

  addCarServiceForUpdate(){
    this.rentacar.addChangedCarService(this.updateCarService.carServiceName,this.updateCarService.carServiceAddress,this.updateCarService.carServiceDescription,this.updateCarService.carServiceLocation,this.updateCarService.id).subscribe(
      data => {
        alert(data);
        if(data === "Service name is already in use!") {
          alert(data);
        }
        else{
          this.router.navigated = false;
          this.router.navigate([this.router.url]);
        }
      },error => {console.log(error)}


    );
  }



}
