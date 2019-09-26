import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Car } from './car';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CrossOrigin} from '@angular-devkit/build-angular/src/browser/schema';
import {CarService} from '../rent-acar/carService';
import {carType} from './carType';
import {carDiscount} from './carDiscount';

@Injectable({
  providedIn: 'root'
})
export class CarServiceService {

  private getURL: string;
  private createURL: string;
  private deleteURL: string;
  private getCarForUpdateURL: string;
  private postModifiedCarURL: string;
  private searchUrl: string;
  private typesUrl: string;
  private getAvailable: string;
  private fullPriceUrl: string;
  private updateProfitUrl: string;
  private getPriceUrl: string;
  private setDiscount: string;
  constructor(private http: HttpClient) {
    this.setDiscount = 'http://localhost:4200/Car/setDiscount';
    this.getPriceUrl = 'http://localhost:4200/carService/getPrice';
    this.updateProfitUrl = 'http://localhost:4200/carService/updateProfit';
    this.fullPriceUrl = 'http://localhost:4200/carService/totalPrice';
    this.searchUrl = 'http://localhost:4200/Car/search';
    this.getURL = 'http://localhost:4200/Car/getAllCars';
    this.createURL = 'http://localhost:4200/Car/add';
    this.deleteURL = 'http://localhost:4200/Car/remove';
    this.getCarForUpdateURL = 'http://localhost:4200/Car/find';
    this.postModifiedCarURL = 'http://localhost:4200/Car/modifyCar';
    this.typesUrl = 'http://localhost:4200/Car/types';
    this.getAvailable = 'http://localhost:4200/Car/getAvailable';
  }


  setDisc(discount: carDiscount): Observable<boolean> {


    const username = sessionStorage.getItem('username');
    const password = sessionStorage.getItem('password');


    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: 'Basic ' + btoa(username + ':' + password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.post<boolean>(this.setDiscount, discount, httpOptions);
  }


  getcarPrice(id: number): Observable<number> {


    const username = sessionStorage.getItem('username');
    const password = sessionStorage.getItem('password');


    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: 'Basic ' + btoa(username + ':' + password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.get<number>(this.getPriceUrl + '/' + id, httpOptions);
  }


  updateProfit(amount: number, id: number): Observable<any> {


    const username = sessionStorage.getItem('username');
    const password = sessionStorage.getItem('password');


    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: 'Basic ' + btoa(username + ':' + password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.get<any>(this.updateProfitUrl + '/' + amount + ',' + id, httpOptions);
  }

  getFullPrice(array: Number[]): Observable<number> {

    const username = sessionStorage.getItem('username');
    const password = sessionStorage.getItem('password');


    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: 'Basic ' + btoa(username + ':' + password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.get<number>(this.fullPriceUrl + '/' + array, httpOptions);
  }

  getAvailableFun(type: string, f: number, t: number, start: string, end: string): Observable<Car[]> {
    const username = sessionStorage.getItem('username');
    const password = sessionStorage.getItem('password');


    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: 'Basic ' + btoa(username + ':' + password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.get<Car[]>(this.getAvailable + '/' + type + ',' + f + ',' + t + ',' + start + ',' + end, httpOptions);

  }

  getTypes(): Observable<carType[]> {


    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',

      })
    };
    return this.http.get<carType[]>(this.typesUrl, httpOptions);

  }

  search(model: string, type: string, from: number, to: number, nos: number): Observable<Car[]> {


    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',

      })
    };
    return this.http.get<Car[]>(this.searchUrl + '/' + model + ',' + type + ',' + from + ',' + to + ',' + nos, httpOptions);
  }

  getAllCars(): Observable<Car[]> {
    const username = sessionStorage.getItem('username');
    const password = sessionStorage.getItem('password');


    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: 'Basic ' + btoa(username + ':' + password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.get<Car[]>(this.getURL, httpOptions);
  }
  addNewCar(newCar: Car): Observable<string> {
    const username = sessionStorage.getItem('username');
    const password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({

        Authorization: 'Basic ' + btoa(username + ':' + password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text',

      }), dataType: 'text/plain; charset=utf-8', responseType: 'text' as 'json'
    };
    return this.http.post<string>(this.createURL, newCar, httpOptions);
  }



  deleteCar(regID: string): Observable<string> {
    const username = sessionStorage.getItem('username');
    const password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({

        Authorization: 'Basic ' + btoa(username + ':' + password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',


      }), dataType: 'text/plain; charset=utf-8', responseType: 'text' as 'json'
    };
    return this.http.post<string>(this.deleteURL + '/' + regID, null, httpOptions);
  }


  getCarForUpdate(regID: string): Observable<Car> {
    const username = sessionStorage.getItem('username');
    const password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: 'Basic ' + btoa(username + ':' + password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.get<Car>(this.getCarForUpdateURL + '/' + regID, httpOptions);

  }

  addModifiedCar(model: string, type: string, year: number, conv: boolean, regid: string, sID: number, cID: number): Observable<string> {
    const username = sessionStorage.getItem('username');
    const password = sessionStorage.getItem('password');
    const httpOptions = {
      headers: new HttpHeaders({

        Authorization: 'Basic ' + btoa(username + ':' + password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text',

      }), dataType: 'text/plain; charset=utf-8', responseType: 'text' as 'json'
    };
    return this.http.post<string>(this.postModifiedCarURL + '/' + model + ',' + type + ',' + year + ',' + conv + ',' + regid + ',' + sID + ',' + cID, null, httpOptions);
  }

}
