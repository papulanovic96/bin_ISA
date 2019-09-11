import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';
import { GetUserService } from '../service/get-user.service';
import { User } from '../register/user';
import { Observable } from 'rxjs';
import { FriendshipService } from './friendship.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-friendship',
  templateUrl: './friendship.component.html',
  styleUrls: ['./friendship.component.css']
})
export class FriendshipComponent implements OnInit {

  
  public userIMG = 'assets/user.png'
  public user1 = 'assets/user1.png'

  loginService : AuthenticationService;
  a = new User('Meho','Sulejmani','Sarajevo', 'superMeho@narcis.com', '066 928 123', 'superMeho', '1232sd', 'ROLE_USER', 2);
  friendsList: String[];
  usersList: User[];
  requestsList: String[];

  constructor(loginService:AuthenticationService, private serviceUser: GetUserService, 
    private friendshipService: FriendshipService, private _route: ActivatedRoute){
    this.loginService = loginService;
  }

  ngOnInit() {
    this.friendshipService.getUsersFriends().subscribe(
      friendsList => this.friendsList = friendsList
    );
    this.friendshipService.getAllUsers().subscribe(
      usersList => this.usersList = usersList
    )
    this.friendshipService.getAllRequests().subscribe(
      requestsList => this.requestsList = requestsList
    )
      this.getLoggedUser(this.loginService.getLogged());
  }

  save() {
    return this.friendshipService.modifyUser(this.a).subscribe()
  }

  getLoggedUser(username : string) {
    return this.serviceUser.getLoggedUser(username).subscribe(
      a => this.a = a
    );
  }

  acceptRequest(username : string) {
    return this.friendshipService.acceptRequest(username).subscribe(
      friendsList => {this.friendshipService.getAllRequests().subscribe()}
    );
  }

  declineRequest(username : string) {
    return this.friendshipService.declineRequest(username).subscribe(
      friendsList => {this.friendshipService.getAllRequests().subscribe()}
    );
  }

  sendRequest(username : string) {
    return this.friendshipService.sendRequest(username).subscribe()
  }

  unfriend(username : string){
    return this.friendshipService.unfriend(username).subscribe(
      response =>  this.friendsList = response
    )
  }

  reload() {  
    window.location.reload();
  }

}
