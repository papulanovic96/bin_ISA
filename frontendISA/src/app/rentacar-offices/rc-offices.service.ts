import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {rcOffice} from "./rcOffice";

@Injectable({
  providedIn: 'root'
})
export class RcOfficesService {
private getOneURL: string;
private getAllURLL: string;
private addURL: string;
private deleteURL: string;
private modifyURL: string;
  constructor(private http:HttpClient) {

    this.getOneURL = 'http://localhost:4200/carService/office/getOne';
    this.getAllURLL = 'http://localhost:4200/carService/office/getAll';
    this.addURL = 'http://localhost:4200/carService/office/add';
    this.deleteURL = 'http://localhost:4200/carService/office/delete';
    this.modifyURL = 'http://localhost:4200/carService/office/modify';

  }

  getAll(id:number) : Observable<rcOffice[]>{
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
   return this.http.get<rcOffice[]>(this.getAllURLL+'/'+id,httpOptions);
  }

  getOne(id:number) : Observable<rcOffice>{
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
    return this.http.get<rcOffice>(this.getOneURL+'/'+id,httpOptions);
  }

  create(rco:rcOffice) : Observable<boolean>{
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
    return this.http.post<boolean>(this.addURL,rco,httpOptions);

  }

  delete(id:number) : Observable<boolean>{
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
    return this.http.get<boolean>(this.deleteURL+'/'+id,httpOptions);

  }

  modify(address:string,id:number) : Observable<string>{
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
    return this.http.get<string>(this.modifyURL+'/'+address+','+id,httpOptions);

  }


}
