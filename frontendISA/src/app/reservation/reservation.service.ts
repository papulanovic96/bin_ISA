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
  //private getRoomsByHotelAndType = 'http://localhost:4200/room/hotelType'
  private findAvailable = 'http://localhost:4200/room/checkAvailable'
  private setReservationUrl = 'http://localhost:4200/room/setReservation'
  private saveReservationUrl='http://localhost:4200/reservation'

  // private setHotelUrl = 'http://localhost:4200/room/setHotel'

  constructor(private http: HttpClient) {
  }

  findAvailableRooms(reservation: Reservation, listOfTypeIds: number[], listOfPrices: number []) {
    return this.http.get<Room[][]>(this.findAvailable + '/' + listOfTypeIds + '/' + listOfPrices);
  }

  setReservation(reservation: Reservation): Observable<Reservation> {
    const httpOptions = {
      headers: new HttpHeaders({}), dataType: 'text/plain; charset=utf-8', responseType: 'text' as 'json'
    };
    return this.http.post<Reservation>(this.setReservationUrl, reservation, httpOptions);
  }


  // checkIfThereAreAvailableRoom(listOfTypeIds: number[]) {
  //   return this.http.get<Boolean>(this.checkIfAvailable + '/' + listOfTypeIds);
  // }
  //
  // setHotel(hotelId: number): Observable<number> {
  //   const httpOptions = {
  //     headers: new HttpHeaders({}), dataType: 'text/plain; charset=utf-8', responseType: 'text' as 'json'
  //   };
  //   return this.http.get<number>(this.setHotelUrl + '/' + hotelId,httpOptions);
  // }
  //
  // getRoomsByHotelIdAndTypeId(hotelId: number, typeId: number): Observable<Room[]> {
  //   return this.http.get<Room[]>(this.getRoomsByHotelAndType + '/' + hotelId + ',' + typeId);
  // }

  addReservation(reservation: Reservation): Observable<Reservation> {
    const httpOptions = {
      headers: new HttpHeaders({}), dataType: 'text/plain; charset=utf-8', responseType: 'text' as 'json'
    };
    console.log(reservation.roomId +" SERVICE")
    return this.http.post<Reservation>(this.getUrlAddReservation, reservation, httpOptions);
  }

  mostRecentReservationId(): Observable<number> {
    return this.http.get<number>(this.getUrlAddReservation + '/mostRecent');
  }
}
