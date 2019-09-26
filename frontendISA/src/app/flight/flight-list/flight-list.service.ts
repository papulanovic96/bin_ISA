import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Flight } from '../flight';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FlightListService {

  private url = 'http://localhost:4200/flight/findAll';
  private rateurl = 'http://localhost:4200/flight/rate';
  constructor(private http: HttpClient) { }

  pronadjiSve() {
    return this.http.get<Flight[]>(this.url);
  }

  rateFlight(flightId: number, rate: number): Observable<boolean> {
    const username = sessionStorage.getItem('username');
    const password = sessionStorage.getItem('password');
    const userId = sessionStorage.getItem('id');
    const httpOptions = {
      headers: new HttpHeaders({

        Authorization: 'Basic ' + btoa(username + ':' + password),
        'Access-Control-Allow-Methods': 'GET, POST, PUT, PATCH, POST, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Max-Age': '86400',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text',

      })
    };
   return this.http.get<boolean>(this.rateurl + '/' + userId + ',' + flightId + ',' + rate, httpOptions);
  }

}
