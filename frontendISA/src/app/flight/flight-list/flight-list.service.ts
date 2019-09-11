import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Flight } from '../flight';

@Injectable({
  providedIn: 'root'
})
export class FlightListService {

  private url = 'http://localhost:4200/flight/findAll';

  constructor(private http: HttpClient) { }

  pronadjiSve() {
    return this.http.get<Flight[]>(this.url);
  }

}