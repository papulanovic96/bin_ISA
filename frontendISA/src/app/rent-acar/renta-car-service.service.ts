import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {CarService} from "./carService";

@Injectable({
  providedIn: 'root'
})
export class RentaCarServiceService {
  private getUrl : string;

  constructor(private http:HttpClient) {
    this.getUrl = 'http://localhost:8080/carService/getAllCarServices';
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
}
