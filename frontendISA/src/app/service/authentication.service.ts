import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(
    private httpClient:HttpClient
  ) {
  }

  authenticate(username, password) {
    sessionStorage.clear();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
   return this.httpClient.post<any>('http://localhost:4200/user/validateLogin/'+username+","+password, {headers});


   }



  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
   
    return !(user === null)
  }

  isUserUSER() {
    let user = sessionStorage.getItem('role')
    return (user !== null && user === "ROLE_USER")
  }

  isUserADMIN() {
    let user = sessionStorage.getItem('role')
    return (user !== null && user === "ROLE_CAR_ADMIN")
  }

  isUserAirlineAdmin() {
    let user = sessionStorage.getItem('role')
    return (user !== null && user === "ROLE_ADMIN_AIRLINE")
  }

  getLogged(): string{
    return sessionStorage.getItem('username');
  }

  getRole(): string{
    return sessionStorage.getItem("role");
  }


  logOut() {
    sessionStorage.removeItem('username')
  }
}
