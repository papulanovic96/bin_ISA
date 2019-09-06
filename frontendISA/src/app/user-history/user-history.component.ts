import { Component, OnInit } from '@angular/core';
import {RentACarComponent} from "../rent-acar/rent-acar.component";
import {RentaCarServiceService} from "../rent-acar/renta-car-service.service";
import {Car} from "../car/car";
import {log} from "util";
import {Observable} from "rxjs";
import {carRate} from "../car/carRate";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-history',
  templateUrl: './user-history.component.html',
  styleUrls: ['./user-history.component.css']
})
export class UserHistoryComponent implements OnInit {
  public listOfCars: Car[];
  public flag : boolean;
  public service: RentaCarServiceService;
  display5='none';
  display6='none';
  public CarRate = new carRate(0,0,null,0);

  constructor(rentService: RentaCarServiceService,public router:Router) {
    this.service = rentService;
    this.router.routeReuseStrategy.shouldReuseRoute = function(){
      return false;
    }
  }

  ngOnInit() {
    this.service.getReserved(Number(sessionStorage.getItem("id"))).subscribe(data=>this.listOfCars=data);
  }

  cancelRes(idCar:number){
    this.service.deleteReservation(idCar).subscribe(data=>alert(data));
  }

  isUserAbleToRate(idCar:number){
    this.service.isUserAbleToRate(idCar).subscribe(
      data=> {
        if (data === true)
          this.setCarId(idCar);
        else
          alert("ERROR");
      },
      error1 => alert(error1)

    );
  }

  isUserAbleToUnreserve(id:number){
    this.service.tryToUnreserve(id).subscribe(
    data=>{
      if (data === true) {
       this.cancelRes(id);
        alert("SUCCESS");
        this.router.navigated = false;
        this.router.navigate([this.router.url]);
      }
      else
        alert("YOU CAN'T CANCEL");
    }
    );
  }

  rateCar(){
    this.CarRate.userID = Number(sessionStorage.getItem("id"));
    this.service.rateCar(this.CarRate).subscribe(
      data=> {
        alert(data);
        this.closeModalDialog5();
        this.router.navigated = false;
        this.router.navigate([this.router.url]);

      }
    );
  }

  setCarId(id:number){
    this.CarRate.carID=id;
    this.openModalDialog5();
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


}
