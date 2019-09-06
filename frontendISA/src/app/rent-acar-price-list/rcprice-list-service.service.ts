import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {carServicePriceLIst} from "./carServicePriceList";
import {Observable} from "rxjs";
import {CarService} from "../rent-acar/carService";

@Injectable({
  providedIn: 'root'
})
export class RCPriceListServiceService {


  private getURL : string;
  private createURL : string;
  private deleteURL : string;
  private getForUpdateURL : string;
  private modifyURL : string;


  constructor(private http:HttpClient) {
    this.getURL = 'http://localhost:4200/carService/getAllItems';
    this.createURL = 'http://localhost:4200/carService/addItem';
    this.deleteURL = 'http://localhost:4200/carService/deleteItem';
    this.getForUpdateURL = 'http://localhost:4200/carService/getItem';
   this.modifyURL = 'http://localhost:4200/carService/modifyItem';
  }

  addUpdated(name:string,price:number,id:number) : Observable<string>{
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
      }),dataType: 'text/plain; charset=utf-8',responseType: 'text' as 'json'
    };
    return this.http.post<string>(this.modifyURL+'/'+name+','+price+','+id,null,httpOptions);

  }

  getForUpdate(id:number) : Observable<carServicePriceLIst>{
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
    return this.http.get<carServicePriceLIst>(this.getForUpdateURL+'/'+id,httpOptions);

  }


  create(newItem:carServicePriceLIst) : Observable<string> {
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
      }),dataType: 'text/plain; charset=utf-8',responseType: 'text' as 'json'
    };
    return this.http.post<string>(this.createURL,newItem,httpOptions);
  }

  delete(itemName:string) : Observable<string>{
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
      }),dataType: 'text/plain; charset=utf-8',responseType: 'text' as 'json'
    };
    return this.http.post<string>(this.deleteURL+'/'+itemName,null,httpOptions);
  }

  getAll(serviceName : string) : Observable<carServicePriceLIst[]> {
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
    return this.http.get<carServicePriceLIst[]>(this.getURL+'/'+serviceName,httpOptions);

}

  getAllServices(): Observable<CarService[]>{
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
    return this.http.get<CarService[]>('http://localhost:4200/carService/getAllCarServices',httpOptions);

  }

}
