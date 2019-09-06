import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../register/user";

@Injectable({
  providedIn: 'root'
})
export class GetUserService {

  constructor(private httpClient: HttpClient) {
  }


  getLoggedUser(username: string) {

    return this.httpClient.get<User>('http://localhost:4200/user/get/' + username);
  }
}
