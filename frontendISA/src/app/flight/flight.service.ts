import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Flight } from './flight';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  private url = 'http://localhost:4200/flight/create';
  private urlSearch = 'http://localhost:4200/flight/check';
  private urlDelete = 'http://localhost:4200/flight/delete';

  constructor(private http: HttpClient) { }

  save(newFlight: Flight){
    return this.http.post<Flight>(this.url, newFlight);
  }

  search(from: string, to: string, fromDest: string, toDest: string){
    return this.http.get<Flight[]>(this.urlSearch + "/" + from + ", " + to + ", " + fromDest + ", " + toDest);
  }

  delete(id: number){
    return this.http.delete(this.urlDelete + "/" + id);
  }
}
