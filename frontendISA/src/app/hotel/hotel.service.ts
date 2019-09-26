import {Injectable} from '@angular/core';
import {Hotel} from './hotel';
import {Type} from './type';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Address} from './address';
import {FastHotelReservation} from './fastHotelReservation';
import {Discount} from '../room/discount';

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  private getUrlHotels = 'http://localhost:4200/hotel';
  private getUrlRoomTypes = 'http://localhost:4200/room/roomTypes';
  private getMenuUrl = 'http://localhost:4200/hotel/getMenu';
  private addMenuItemUrl = 'http://localhost:4200/hotel/addMenuItem';
  private removeMenuItemUrl = 'http://localhost:4200/hotel/removeMenuItem';
  private getDescriptionUrl = 'http://localhost:4200/hotel/getDescription';
  private getValidDiscountsUrl = 'http://localhost:4200/newRoomPrice/validDiscounts';
  private addFastResUrl = 'http://localhost:4200/reservation/discount';
  private rateHotelUrl = 'http://localhost:4200/reservation/check';
  private rateRoomUrl = 'http://localhost:4200/room/rate';


  private username = sessionStorage.getItem('username');
  private password = sessionStorage.getItem('password');
  private httpOptionsWithDataTypes = {
    headers: new HttpHeaders({
      Authorization: 'Basic ' + btoa(this.username + ':' + this.password),
    }), dataType: 'text/plain; charset=utf-8', responseType: 'text' as 'json'
  };
  private httpOptions = {
    headers: new HttpHeaders({
      Authorization: 'Basic ' + btoa(this.username + ':' + this.password),
    })
  };

  constructor(private http: HttpClient) {
  }

  rateRoom(idRoom: number, rate: number): Observable<boolean> {
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
    return this.http.get<boolean>(this.rateRoomUrl + '/' + idRoom + ',' + sessionStorage.getItem('username') + ',' + rate, httpOptions);
  }

  rateHotel(idHotel: number, rate: number): Observable<boolean> {
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
    return this.http.get<boolean>(this.rateHotelUrl + '/' + idHotel + ',' + sessionStorage.getItem('username') + ',' + rate, httpOptions);
  }

  addFastRes(fastRes: FastHotelReservation): Observable<FastHotelReservation> {
    console.log(fastRes);
    return this.http.post<FastHotelReservation>(this.addFastResUrl, fastRes, this.httpOptionsWithDataTypes);
  }

  getValidDiscounts(fastReservation: FastHotelReservation): Observable<Discount[]> {
    return this.http.put<Discount[]>(this.getValidDiscountsUrl, fastReservation, this.httpOptions);
  }

  getAllAddresses(): Observable<Address[]> {
    return this.http.get<Address[]>(this.getUrlHotels + '/addresses', this.httpOptions);
  }

  removeHotel(hotelId: number): Observable<Boolean> {
    return this.http.put<Boolean>(this.getUrlHotels + '/remove/' + hotelId, this.httpOptionsWithDataTypes);
  }

  getHotel(hotelId: number): Observable<Hotel> {
    return this.http.get<Hotel>(this.getUrlHotels + '/' + hotelId, this.httpOptions);
  }

  changeHotel(hotelId: number, hotel: Hotel): Observable<Hotel> {
    return this.http.put<Hotel>(this.getUrlHotels + '/' + hotelId, hotel, this.httpOptionsWithDataTypes);
  }

  getDescription(hotelId: number): Observable<String> {
    console.log(this.httpOptionsWithDataTypes);
    return this.http.get<String>(this.getDescriptionUrl + '/' + hotelId, this.httpOptionsWithDataTypes);
  }

  getMenu(hotelId: number): Observable<Map<String, Number>> {
    return this.http.get<Map<String, Number>>(this.getMenuUrl + '/' + hotelId, this.httpOptions);
  }

  addMenuItem(name: String, price: number, hotelId: number): Observable<Object> {
    return this.http.get<Object>(this.addMenuItemUrl + '/' + name + '/' + price + '/' + hotelId, this.httpOptionsWithDataTypes);
  }

  removeMenuItem(name: String, hotelId: number): Observable<Object> {
    return this.http.get<Object>(this.removeMenuItemUrl + '/' + name + '/' + hotelId, this.httpOptionsWithDataTypes);
  }

  getAllHotels(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(this.getUrlHotels, this.httpOptions);
  }

  getAllRoomTypes(): Observable<Type[]> {
    return this.http.get<Type[]>(this.getUrlRoomTypes, this.httpOptions);
  }

}
