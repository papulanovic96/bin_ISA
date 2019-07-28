import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Airline } from './airline';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AirlineServiceService {

  private getURL : string;

  constructor(private http: HttpClient) { 
    this.getURL = 'http://localhost:4200/airline/findAll';
  }

  getAllAirlines() : Observable<Airline[]> {
    return this.http.get<Airline[]>(this.getURL);
  }
}
