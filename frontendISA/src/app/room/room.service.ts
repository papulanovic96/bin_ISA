import {Injectable} from '@angular/core';
import {Room} from "./room";
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private getUrlRooms = 'http://localhost:4200/room';
  private getUrlAddRoom = 'http://localhost:4200/room/addRoom'
  private reservedRoomUrl = 'http://localhost:4200/room/reserved'
  private httpOptions = {
    headers: new HttpHeaders({}), dataType: 'text/plain; charset=utf-8', responseType: 'text' as 'json'
  };

  constructor(private http: HttpClient) {
  }

  reservedRoom(roomId: number): Observable<Boolean> {
    return this.http.get<Boolean>(this.reservedRoomUrl + '/' + roomId);
  }

  removeRoom(roomId: number): Observable<Object> {
    return this.http.delete(this.getUrlRooms + '/' + roomId, this.httpOptions);
  }

  addRoom(room: Room): Observable<Room> {
    return this.http.post<Room>(this.getUrlAddRoom, room, this.httpOptions);
  }

  changeRoom(roomId: number, room: Room): Observable<Room> {
    return this.http.put<Room>(this.getUrlRooms + '/' + roomId, room, this.httpOptions);
  }

  getRoom(roomId: number): Observable<Room> {
    return this.http.get<Room>(this.getUrlRooms + '/' + roomId);
  }

  getRoomsByHotelId(hotelId: number): Observable<Room[]> {
    return this.http.get<Room[]>(this.getUrlRooms + '/hotel' + '/' + hotelId);
  }

  getAllRooms(): Observable<Room[]> {
    return this.http.get<Room[]>(this.getUrlRooms);
  }

}

