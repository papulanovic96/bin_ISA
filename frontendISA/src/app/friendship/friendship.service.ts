import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../register/user';
import { GetUserService } from '../service/get-user.service';
import { AuthenticationService } from '../service/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class FriendshipService {

  private url = 'http://localhost:4200/user/getFriends';
  private urlUsers = 'http://localhost:4200/user/findAll';
  private urlRequests = 'http://localhost:4200/user/getRequests';
  private urlAccept = 'http://localhost:4200/user/acceptFriendRequest';
  private urlDecline = 'http://localhost:4200/user/declineFriendRequest';
  private urlSend = 'http://localhost:4200/user/sendFriendRequest';
  private urlUnfriend = 'http://localhost:4200/user/deleteFriendRequest';
  private urlModify = 'http://localhost:4200/user/modify';

  private listaUsera : Observable<User[]>;

  constructor(private http: HttpClient, private getgets: AuthenticationService) { }

  modifyUser(usr: User) {
    return this.http.put(this.urlModify + "/" + this.getgets.getLogged(), usr);
  }

  getUsersFriends() : Observable<String[]> {
    return this.http.get<String[]>(this.url + "/" + this.getgets.getLogged());
  }

  getAllUsers() : Observable<User[]> {
    this.listaUsera = this.http.get<User[]>(this.urlUsers + "/" + this.getgets.getLogged());
    return this.listaUsera;
  }
  
  getAllRequests() : Observable<String[]> {
    return this.http.get<String[]>(this.urlRequests + "/" + this.getgets.getLogged());
  }

  acceptRequest(username: string){
    return this.http.post(this.urlAccept + "/" + this.getgets.getLogged() + ", " + username, null);
  }

  declineRequest(username: string){
    return this.http.post(this.urlDecline + "/" + this.getgets.getLogged() + ", " + username, null);
  }

  sendRequest(username: string){
    return this.http.post(this.urlSend + "/" + this.getgets.getLogged() + ", " + username, null);
  }

  unfriend(username: string) : Observable<String[]>{
    return this.http.post<String[]>(this.urlUnfriend + "/" + this.getgets.getLogged() + ", " + username, null);
  }
}
