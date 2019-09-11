import {Component, Injectable, OnInit, Pipe} from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";
import {CarServiceService} from "../car/car-service.service";
import {RentaCarServiceService} from "../rent-acar/renta-car-service.service";
import {Router} from "@angular/router";
import {carType} from "../car/carType";
import {Car} from "../car/car";
import { DatePipe } from '@angular/common';
import {carReservation} from "../car/carReservation";

@Component({
  selector: 'app-reserve-car',
  templateUrl: './reserve-car.component.html',
  styleUrls: ['./reserve-car.component.css']
})

export class ReserveCarComponent implements OnInit {
  carReservation:carReservation = new carReservation(null,null,null,null,null);
  types: carType[]=[];
  cars :Car[]=[];
  listOfReservation:carReservation[]=[];
  type : carType = new carType('',0,0,0);
  flag:number = 0;
  profit:number = 0;
  forReservation: Car[] = [];
  length:Number  =  0;
  startDate :string = '';
  pricefrom : number = 0;
  priceto:number = 1000000;
  endDate:string = ''
  nos : number = 0;
  listOfIds: Number[] = [];
 numberOfSeats:number = 0;
 name:string = '';
 numberOfDays:any = 0;
 totalCount:number = 0;
 show:boolean = false;
 newDate:Date;
 newDate2:Date;


  constructor(private pipe:DatePipe,private loginService:AuthenticationService,private carService : CarServiceService,private rentacar : RentaCarServiceService, public router:Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = function(){
      return false;
    }
  }

  ngOnInit() {
    this.getTypes();
  }
  checkSeats(){
    if(this.nos<=this.numberOfSeats){
      this.getTotal();
    }else alert("Not enough seats");
  }

  getTotal(){

    for (let c of this.forReservation)
      this.listOfIds.push(c.id);
    this.carService.getFullPrice(this.listOfIds).subscribe(
      data=> {
        this.totalCount = data * this.numberOfDays;

        this.show = true;
      }
    );

  }
  sendReservations(){

    for (let c of this.forReservation){
      this.carReservation.id = null;
      this.carReservation.carId = c.id;
      this.carReservation.userId = Number(sessionStorage.getItem("id"));
      this.carReservation.startDate = this.newDate;
      this.carReservation.endDate = this.newDate2;

      this.rentacar.setReservations(this.carReservation).subscribe();
      this.carService.getcarPrice(c.serviceId).subscribe(
        data=> {
          this.profit = data;
          this.carService.updateProfit(this.profit*this.numberOfDays,c.serviceId).subscribe();
        },

      );


    }

    alert("SUCCESS");
    this.router.navigated = false;
    this.router.navigate([this.router.url]);
  }
  push(car:Car){
    this.flag = 0;
    for (let c of this.forReservation){
      if(c.id == car.id){
        this.flag = 1;
      }
    }
    if(this.flag == 0) {
      this.forReservation.push(car);
    this.numberOfSeats += this.type.seats;
    }
    else
      alert("Already have that car");

  }

  pop(index:number){
    for (let c of this.forReservation){
      if(c.id == index){
        this.numberOfSeats -= this.type.seats;
        this.forReservation.splice(this.forReservation.indexOf(c),1);
      }
    }
  }
  
  checkDate(){
     this.newDate = new Date(this.startDate);
     this.newDate2 = new Date(this.endDate);

    this.startDate = this.pipe.transform(this.newDate, 'yyyy-MM-dd'); //whatever format you need.
    this.endDate = this.pipe.transform(this.newDate2, 'yyyy-MM-dd'); //whatever format you need.


    if(this.newDate>this.newDate2)
    alert("WRONG DATA ARRAGEMENT")
    else{
      this.numberOfDays = Math.abs(this.newDate.getTime() - this.newDate2.getTime());
      var diffDays = Math.ceil(this.numberOfDays / (1000 * 3600 * 24));
      this.numberOfDays = Number(diffDays);
      this.getCars();
    }



  }

  getCars(){

    this.carService.getAvailableFun(this.type.name,this.pricefrom,this.priceto,this.startDate,this.endDate).subscribe(
      data => {

        this.cars = data
        this.length = data.length;

      }
      );
  }

  getTypes(){
    this.carService.getTypes().subscribe(
      data=> this.types = data
    );
  }

}
