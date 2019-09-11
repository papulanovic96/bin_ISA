import { Component, OnInit } from '@angular/core';
import {rcOffice} from "./rcOffice";
import {RcOfficesService} from "./rc-offices.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {CarService} from "../rent-acar/carService";
import {RentaCarServiceService} from "../rent-acar/renta-car-service.service";
import {AuthenticationService} from "../service/authentication.service";

@Component({
  selector: 'app-rentacar-offices',
  templateUrl: './rentacar-offices.component.html',
  styleUrls: ['./rentacar-offices.component.css']
})
export class RentacarOfficesComponent implements OnInit {

  public offices:rcOffice[];
  display='none';
  display2='none';
  public csId:number;
  public newAddress:string;
  public modifiedAddress:string;
  public modifiedId:number;
  public show:boolean = false;
  public listOfServices:CarService[];
  public updated = new rcOffice('',0,0,'');
  public newOffice = new rcOffice('',0,0,'');

  constructor(private loginService:AuthenticationService,private rcService:RentaCarServiceService,private service : RcOfficesService,private http:HttpClient, public router:Router) {

    this.router.routeReuseStrategy.shouldReuseRoute = function(){
      return false;
    }

  }

  ngOnInit() {
    this.rcService.getAllCarServices().subscribe(
      data => this.listOfServices = data
    );

  }

  get(id:number,address:string){
    this.modifiedAddress = address;
    this.modifiedId = id;
    this.service.getOne(id).subscribe(
      data=>{

        this.updated = data;
        this.openModalDialog2();
      }
    );
  }
  update(){
    this.service.modify(this.modifiedAddress,this.modifiedId).subscribe(
      data=>{
        alert(data);
        this.closeModalDialog2();
        this.getAllOffices(this.csId);
      }

    );
  }
  getAllOffices(id:number){
    this.service.getAll(id).subscribe(
      data=> {
        this.offices = data
        this.show = true;
      }
      );
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

  deleteOffice(id:number){
    this.service.delete(id).subscribe(
      data=>{
        if(data ===true){
          alert("SUCCESS");
        this.getAllOffices(this.csId);
        }
        else
          alert("ERROR");
      }
    );
  }
  addOffice() {
    this.newOffice.serviceId = this.csId;
    this.service.create(this.newOffice).subscribe(
      data => {
        if (data === true) {
          alert("SUCCESS");
          this.closeModalDialog();
          this.getAllOffices(this.csId);
        } else alert("ERROR");
      }
    );

  }


}
