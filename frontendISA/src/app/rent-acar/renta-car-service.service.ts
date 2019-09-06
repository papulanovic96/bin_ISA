import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {CarService} from "./carService";
import {Car} from "../car/car";
import {carReservation} from "../car/carReservation";
import {carRate} from "../car/carRate";
import {carServiceRate} from "./carServiceRate";

@Injectable({
  providedIn: 'root'
})
export class RentaCarServiceService {
  private getUrl : string;
  private addUrl : string;
  private removeUrl : string;
  private getServiceUrl : string;
  private changeUrl : string;
  private getCarsUrl : string;
  private reserveCarUrl:string;
  private isUserReservedCarUrl:string;
  private tryToRateUrl:string;
  private rateCarUrl:string;
  private tryToUnreserveUrl:string;
  private getAllVehiclesUrl:string;
  private getReservedUrl:string;
  private cancelReservationUrl:string;
  private tryToRateService:string;
  private rateServiceUrl:string;
  private searchUrl:string;
  constructor(private http:HttpClient) {
    this.searchUrl = 'http://localhost:4200/carService/search';
    this.rateServiceUrl = 'http://localhost:4200/carService/rateCarService';
    this.tryToRateService = 'http://localhost:4200/carService/tryToRate';
    this.cancelReservationUrl = 'http://localhost:4200/Car/deleteRes';
    this.getReservedUrl='http://localhost:4200/Car/getReservedCars';
    this.getUrl = 'http://localhost:4200/carService/getAllCarServices';
    this.addUrl = 'http://localhost:4200/carService/add';
    this.removeUrl = 'http://localhost:4200/carService/remove';
    this.getServiceUrl = 'http://localhost:4200/carService/findById';
    this.changeUrl = 'http://localhost:4200/carService/modifyCarService';
    this.getCarsUrl ='http://localhost:4200/Car/findAll';
    this.reserveCarUrl='http://localhost:4200/Car/reserve';
    this.isUserReservedCarUrl ="http://localhost:4200/Car/IsUserReservedCar";
    this.tryToRateUrl="http://localhost:4200/Car/tryToRate";
    this.rateCarUrl="http://localhost:4200/Car/rateCar";
    this.tryToUnreserveUrl="http://localhost:4200/Car/tryToUnreserve";
    this.getAllVehiclesUrl="http://localhost:4200/Car/getAllCars";
  }

  search(address:string,name:string): Observable<CarService[]>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({

        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',


      })
    };
    return this.http.get<CarService[]>(this.searchUrl+'/'+address+','+name,httpOptions);
  }


  getReserved(id:number): Observable<Car[]>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({

        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',


      })
    };
    return this.http.get<Car[]>(this.getReservedUrl+"/"+id,httpOptions);
  }

  setReservations(carR:carReservation): Observable<string>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({

        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',
        'responseType':'text',

      }),dataType: 'text/plain; charset=utf-8',responseType: 'text' as 'json'
    };
    return this.http.post<string>(this.reserveCarUrl,carR,httpOptions);
  }

  deleteReservation(carID:number): Observable<string>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');
    let userID = Number(sessionStorage.getItem("id"));
    const httpOptions = {
      headers: new HttpHeaders({

        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',
        'responseType':'text',

      }),dataType: 'text/plain; charset=utf-8',responseType: 'text' as 'json'
    };
    return this.http.post<string>(this.cancelReservationUrl+"/"+carID+","+userID,httpOptions);
  }

  rateCar(CarRate:carRate):Observable<string>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({

        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',
        'responseType':'text',

      }),dataType: 'text/plain; charset=utf-8',responseType: 'text' as 'json'
    };
    return this.http.post<string>(this.rateCarUrl,CarRate,httpOptions);

  }

  getAllVehicles(): Observable<Car[]>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.get<Car[]>(this.getAllVehiclesUrl,httpOptions);

  }


  getAllCars(name:string): Observable<Car[]>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.get<Car[]>(this.getCarsUrl+'/'+name,httpOptions);

  }


  getAllCarServices(): Observable<CarService[]>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.get<CarService[]>(this.getUrl,httpOptions);

  }


  addNewCarService(newCarService:CarService) : Observable<string>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({

        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',
        'responseType':'text',

      }),dataType: 'text/plain; charset=utf-8',responseType: 'text' as 'json'
    };
    return this.http.post<string>(this.addUrl,newCarService,httpOptions);
  }

  deleteCarService(id:number) : Observable<string>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({

        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',


      }),dataType: 'text/plain; charset=utf-8',responseType: 'text' as 'json'
    };
    return this.http.post<string>(this.removeUrl+'/'+id,null, httpOptions);
  }

  getCarServiceForUpdate(Id:number): Observable<CarService>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.get<CarService>(this.getServiceUrl+'/'+Id,httpOptions);

  }

  addChangedCarService (name:string,address:string,desc:string,locat:string,id:number) : Observable<string>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({

        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',
        'responseType':'text',

      }),dataType: 'text/plain; charset=utf-8',responseType: 'text' as 'json'
    };
    return this.http.post<string>(this.changeUrl+'/'+name+','+address+','+desc+','+locat+','+id,null,httpOptions);
  }
  isUserReservedCar(id:number,id2:number):Observable<boolean>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');

    const httpOptions = {
      headers: new HttpHeaders({

        'Authorization': 'Basic ' + btoa(username+':'+password),


      })
    };
    return this.http.get<boolean>(this.isUserReservedCarUrl+'/'+id+','+id2,httpOptions);
  }

  isUserAbleToRate(carId:number):Observable<boolean>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');
    let userId = sessionStorage.getItem("id");
    const httpOptions = {
      headers: new HttpHeaders({

        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',
        'responseType':'text',

      })
    };
    return this.http.get<boolean>(this.tryToRateUrl+'/'+carId+','+userId,httpOptions);
  }

  tryToUnreserve(id:number):Observable<boolean>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');

    const httpOptions = {
      headers: new HttpHeaders({

        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',
        'responseType':'text'

      })
    };
    return this.http.get<boolean>(this.tryToUnreserveUrl+"/"+id+","+Number(sessionStorage.getItem("id")),httpOptions);
  }

  tryToRateServices(idService:number):Observable<boolean>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');

    const httpOptions = {
      headers: new HttpHeaders({

        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',
        'responseType':'text'

      })
    };
    return this.http.get<boolean>(this.tryToRateService+"/"+Number(sessionStorage.getItem("id"))+','+idService,httpOptions);
  }

  rateCarService(rate : carServiceRate):Observable<string>{
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');

    const httpOptions = {
      headers: new HttpHeaders({

        'Authorization': 'Basic ' + btoa(username+':'+password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',
        'responseType':'text'

      }),dataType: 'text/plain; charset=utf-8',responseType: 'text' as 'json'
    };
    return this.http.post<string>(this.rateServiceUrl,rate,httpOptions);
  }
}

