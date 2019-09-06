import { Component, OnInit } from '@angular/core';
import {RegisterService} from "../service/register.service";
import {User} from "./user";
import {Router} from "@angular/router";




@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})


export class RegisterComponent  implements OnInit {

  name = ''
  lastName = ''
  city = ''
  email = ''
  telephone = ''
  username = ''
  password = ''
  role =''
  id = 0
  user : User
  constructor(private regService: RegisterService,private router: Router) {

  }

  ngOnInit() {
  }

  register() {
   this.user = new User(this.name, this.lastName, this.city, this.email, this.telephone, this.username, this.password,this.role,null);

    (this.regService.registerUser(this.user).subscribe(
      data => {
       if(data === true) {
         alert("SUCCESS! Check your email!");
         this.router.navigate(['/'])
       }
        else
         alert("ERROR");
      },
      error1 => {
        alert("Bad Email");
      }
    ));
  }
}





