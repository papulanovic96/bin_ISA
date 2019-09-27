import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from '../service/authentication.service';
import { Observable } from 'rxjs';
import { GetUserService } from '../service/get-user.service';

@Injectable({
  providedIn: 'root'
})
export class FriendInviteService {

  private url = 'http://localhost:4200/user/getFriends';
  private urlInvite = 'http://localhost:4200/user/friendinvite';

  constructor(private http: HttpClient, private getgets: AuthenticationService,
    private getUserService: GetUserService) { }

  getUsersFriends() : Observable<String[]> {
    return this.http.get<String[]>(this.url + "/" + this.getgets.getLogged());
  }

  invite(username: string) {
    return this.http.post(this.urlInvite + "/" + username, null)
  }


}
