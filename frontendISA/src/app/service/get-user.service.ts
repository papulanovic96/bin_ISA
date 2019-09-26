import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../register/user";
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GetUserService {

  constructor(private httpClient: HttpClient) {
  }


  getLoggedUser(username: string) : Observable<User>{

    return this.httpClient.get<User>('http://localhost:4200/user/get/' + username);
  }

  getMeAUser(username: string){
    return this.httpClient.get('http://localhost:4200/user/get/' + username);
  }
}
