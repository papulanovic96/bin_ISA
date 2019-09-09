import {Injectable} from '@angular/core';
import {Room} from "./room";
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {NewRoomPrice} from "./newRoomPrice";

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private getUrlRooms = 'http://localhost:4200/room';
  private getUrlAddRoom = 'http://localhost:4200/room/addRoom'
  private reservedRoomUrl = 'http://localhost:4200/room/reserved'
  private addNewPriceUrl = 'http://localhost:4200/newRoomPrice'
  private checkExistenceUrl = 'http://localhost:4200/newRoomPrice/checkExistence'
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

  reservedRoom(roomId: number): Observable<Boolean> {
    return this.http.get<Boolean>(this.reservedRoomUrl + '/' + roomId, this.httpOptions);
  }

  removeRoom(roomId: number): Observable<Object> {
    return this.http.delete(this.getUrlRooms + '/' + roomId, this.httpOptionsWithDataTypes);
  }

  checkIfPriceExist(newPrice: NewRoomPrice): Observable<Boolean> {
    return this.http.post<Boolean>(this.checkExistenceUrl, newPrice, this.httpOptions);
  }

  addNewPrice(newPrice: NewRoomPrice): Observable<NewRoomPrice> {
    return this.http.post<NewRoomPrice>(this.addNewPriceUrl, newPrice, this.httpOptionsWithDataTypes);
  }

  addRoom(room: Room): Observable<Room> {
    return this.http.post<Room>(this.getUrlAddRoom, room, this.httpOptionsWithDataTypes);
  }

  changeRoom(roomId: number, room: Room): Observable<Room> {
    return this.http.put<Room>(this.getUrlRooms + '/' + roomId, room, this.httpOptionsWithDataTypes);
  }

  getRoom(roomId: number): Observable<Room> {
    return this.http.get<Room>(this.getUrlRooms + '/' + roomId, this.httpOptions);
  }

  getRoomsByHotelId(hotelId: number): Observable<Room[]> {
    return this.http.get<Room[]>(this.getUrlRooms + '/hotel' + '/' + hotelId, this.httpOptions);
  }

  getAllRooms(): Observable<Room[]> {
    return this.http.get<Room[]>(this.getUrlRooms, this.httpOptions);
  }

}

