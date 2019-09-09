import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Flight } from './flight';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  private url = 'http://localhost:4200/flight/create';
  constructor(private http: HttpClient) { }

  save(newFlight: Flight){
    return this.http.post<Flight>(this.url, newFlight);
  }
}
