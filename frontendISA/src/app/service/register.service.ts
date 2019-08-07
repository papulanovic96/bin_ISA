import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../register/user";


@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpClient: HttpClient) {
  }


  registerUser(user : User){


    return this.httpClient.post<any>('http://localhost:8080/user/register',user);
  }

}
