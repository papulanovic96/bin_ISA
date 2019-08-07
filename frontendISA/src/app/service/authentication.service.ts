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
   return this.httpClient.post<any>('http://localhost:8080/validateLogin/'+username+","+password, {headers});


   }



  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))
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

  getLogged(): string{
    return sessionStorage.getItem('username');
  }


  logOut() {
    sessionStorage.removeItem('username')
  }
}
