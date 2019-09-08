import {Injectable} from '@angular/core';
import {Hotel} from "./hotel";
import {Type} from "./type"
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  private getUrlHotels = 'http://localhost:4200/hotel'
  private getUrlRoomTypes = 'http://localhost:4200/room/roomTypes'
  private getMenuUrl = 'http://localhost:4200/hotel/getMenu'
  private addMenuItemUrl = 'http://localhost:4200/hotel/addMenuItem'
  private removeMenuItemUrl = 'http://localhost:4200/hotel/removeMenuItem'
  private getDescriptionUrl = 'http://localhost:4200/hotel/getDescription'
  private httpOptions = {
    headers: new HttpHeaders({}), dataType: 'text/plain; charset=utf-8', responseType: 'text' as 'json'
  };

  constructor(private http: HttpClient) {
  }

  removeHotel(hotelId: number): Observable<Boolean> {
    return this.http.put<Boolean>(this.getUrlHotels + '/remove/' + hotelId, this.httpOptions);
  }

  getHotel(hotelId: number): Observable<Hotel> {
    return this.http.get<Hotel>(this.getUrlHotels + '/' + hotelId);
  }

  changeHotel(hotelId: number, hotel: Hotel): Observable<Hotel> {
    return this.http.put<Hotel>(this.getUrlHotels + '/' + hotelId, hotel, this.httpOptions);
  }

  getDescription(hotelId: number): Observable<String> {
    return this.http.get<String>(this.getDescriptionUrl + '/' + hotelId, this.httpOptions);
  }

  getMenu(hotelId: number): Observable<Map<String, Number>> {
    return this.http.get<Map<String, Number>>(this.getMenuUrl + '/' + hotelId);
  }

  addMenuItem(name: String, price: number, hotelId: number): Observable<Object> {
    return this.http.get<Object>(this.addMenuItemUrl + '/' + name + '/' + price + '/' + hotelId, this.httpOptions);
  }

  removeMenuItem(name: String, hotelId: number): Observable<Object> {
    return this.http.get<Object>(this.removeMenuItemUrl + '/' + name + '/' + hotelId, this.httpOptions);
  }

  getAllHotels(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(this.getUrlHotels);
  }

  getAllRoomTypes(): Observable<Type[]> {
    return this.http.get<Type[]>(this.getUrlRoomTypes);
  }

}
