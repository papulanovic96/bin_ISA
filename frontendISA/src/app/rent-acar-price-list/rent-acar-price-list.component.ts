import { Component, OnInit } from '@angular/core';
import {carServicePriceLIst} from "./carServicePriceList";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {CarService} from "../rent-acar/carService";
import {RentACarComponent} from "../rent-acar/rent-acar.component";
import {RCPriceListServiceService} from "./rcprice-list-service.service";
import {Router} from "@angular/router";
import {RentaCarServiceService} from "../rent-acar/renta-car-service.service";
import {Car} from "../car/car";
import {AuthenticationService} from "../service/authentication.service";

@Component({
  selector: 'app-rent-acar-price-list',
  templateUrl: './rent-acar-price-list.component.html',
  styleUrls: ['./rent-acar-price-list.component.css']
})
export class RentACarPriceListComponent implements OnInit {
  public cars : Car[];
  public PriceListItem = new carServicePriceLIst('',0,'',0,0,0,'',0);
  public newPriceListItem =  new carServicePriceLIst('',0,'',0,0,0,'',0);
  public carServices : CarService[];
  public csName:string;
  public csId:number;
  public carId:number;
  public actualIdService:number;
  public listOfItems : carServicePriceLIst[];
  public show : boolean = false;
  display='none';
  display2='none';
  constructor(private loginService:AuthenticationService,private rcService:RentaCarServiceService,private http:HttpClient, private service:RCPriceListServiceService,  public router:Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = function(){
      return false;
    }
  }

  ngOnInit() {
    this.service.getAllServices().subscribe(
      data => this.carServices = data
    );


  }


getCars(){
    this.rcService.getAllVehicles().subscribe(
      data=>{
        this.cars = [];
        for(let c of data){
          if (c.serviceId === this.actualIdService)
            this.cars.push(c);
        }
        this.openModalDialog();
      }
    );
}

  showDiv(){
    this.show = true;
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

getItemsOfCarService(name : string){

    for(let cs of this.carServices)
    {
      if(cs.carServiceName === name)
        this.csId = cs.id;
    }
    this.actualIdService = this.csId;
  this.service.getAll(this.csId).subscribe(
    data=> {
      this.listOfItems = data;
      this.show = true;

    },
    error1 => alert(error1)
  );
}

deleteItem(name:string){
    this.service.delete(name).subscribe(
      data=>{
        alert(data);
        this.getItemsOfCarService(this.csName);
        this.display = 'none';
      },
      error1 => alert(error1)
    );
}

getItem(id:number){
    this.service.getForUpdate(id).subscribe(
      data=> {this.PriceListItem = data; this.openModalDialog2()}
    );

}

createItem(){
    this.newPriceListItem.carServiceName = this.csName;
    this.newPriceListItem.carId = this.carId;
    this.newPriceListItem.carServiceId = this.actualIdService;
    this.service.create(this.newPriceListItem).subscribe(
      data=> {
        alert(data);
        this.getItemsOfCarService(this.csName);
        this.display = 'none';

      },
      error1 => alert(error1)

    );
}


addChangedItem (){
    this.service.addUpdated(this.PriceListItem.price,this.PriceListItem.id).subscribe(
      data=> {
        alert(data);
        this.router.navigated = false;
        this.router.navigate([this.router.url]);
      },
      error1 => alert(error1)

    );
}

}
