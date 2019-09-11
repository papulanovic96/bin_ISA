import { Component, OnInit } from '@angular/core';
import { FriendInviteService } from './friend-invite.service';
import { User } from '../register/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-friend-invite',
  templateUrl: './friend-invite.component.html',
  styleUrls: ['./friend-invite.component.css']
})
export class FriendInviteComponent implements OnInit {

  friendsList: String[];
  public userIMG = 'assets/user.png'
  public user1 = 'assets/user1.png'

  constructor(private fService: FriendInviteService, private router: Router) { }

  invite(user: string){
    return this.fService.invite(user).subscribe(
      data => {
        if(data === true) {
          alert("SUCCESS! Check your email!");
//          this.router.navigate(['/login'])
        }
         else
          alert("ERROR");
       },
       error1 => {
         alert("Bad Email");
       }
    )
  }

  ngOnInit() {
    this.fService.getUsersFriends().subscribe(
      friendsList => this.friendsList = friendsList
    );
  }

}
