import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Reservation} from "./reservation";
import {Room} from "../room/room";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private getUrlAddReservation = 'http://localhost:4200/reservation'
  private findAvailable = 'http://localhost:4200/room/checkAvailable'
  private setReservationUrl = 'http://localhost:4200/room/setReservation'
  private username = sessionStorage.getItem('username');
  private password = sessionStorage.getItem('password');
  private httpOptionsWithDataTypes = {
    headers: new HttpHeaders({
      'Authorization': 'Basic ' + btoa(this.username + ':' + this.password),
    }), dataType: 'text/plain; charset=utf-8', responseType: 'text' as 'json'
  };
  private httpOptions = {
    headers: new HttpHeaders({
      'Authorization': 'Basic ' + btoa(this.username + ':' + this.password),
    })
  };

  constructor(private http: HttpClient) {
  }

  findAvailableRooms(reservation: Reservation, listOfTypeIds: number[], listOfPrices: number []) {
    return this.http.get<Room[][]>(this.findAvailable + '/' + listOfTypeIds + '/' + listOfPrices, this.httpOptions);
  }

  setReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(this.setReservationUrl, reservation, this.httpOptionsWithDataTypes);
  }

  addReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(this.getUrlAddReservation, reservation, this.httpOptionsWithDataTypes);
  }

  mostRecentReservationId(): Observable<number> {
    return this.http.get<number>(this.getUrlAddReservation + '/mostRecent', this.httpOptions);
  }
}
