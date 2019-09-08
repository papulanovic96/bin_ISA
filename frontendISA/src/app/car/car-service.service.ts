import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Car } from './car';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CrossOrigin} from "@angular-devkit/build-angular/src/browser/schema";
import {CarService} from "../rent-acar/carService";

@Injectable({
  providedIn: 'root'
})
export class CarServiceService {

  private getURL : string;
  private createURL : string;
  private deleteURL : string;
  private getCarForUpdateURL : string;
  private postModifiedCarURL:string;
  private searchUrl:string;
  constructor(private http: HttpClient) {
    this.searchUrl = 'http://localhost:4200/Car/search';
    this.getURL = 'http://localhost:4200/Car/getAllCars';
    this.createURL = 'http://localhost:4200/Car/add';
    this.deleteURL = 'http://localhost:4200/Car/remove';
    this.getCarForUpdateURL = 'http://localhost:4200/Car/find';
    this.postModifiedCarURL ='http://localhost:4200/Car/modifyCar';

  }

  search(model:string,type:string,from:number,to:number,nos:number) : Observable<Car[]> {


    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',

      })
    };
    return this.http.get<Car[]>(this.searchUrl+'/'+model+','+type+','+from+','+to+','+nos,httpOptions);
  }

  getAllCars() : Observable<Car[]> {
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
    return this.http.get<Car[]>(this.getURL,httpOptions);
  }
  addNewCar(newCar:Car) : Observable<string>{
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
    return this.http.post<string>(this.createURL,newCar,httpOptions);
  }



  deleteCar(regID:string) : Observable<string>{
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
    return this.http.post<string>(this.deleteURL+'/'+regID,null, httpOptions);
  }


  getCarForUpdate(regID:string): Observable<Car>{
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
    return this.http.get<Car>(this.getCarForUpdateURL+'/'+regID,httpOptions);

  }

  addModifiedCar(model:string,type:string,year:number,conv:boolean,regid:string,sID:number,cID:number) : Observable<string>{
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
    return this.http.post<string>(this.postModifiedCarURL+'/'+model+','+type+','+year+','+conv+','+regid+','+sID+','+cID,null,httpOptions);
  }

}
