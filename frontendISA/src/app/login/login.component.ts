
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import {GetUserService} from "../service/get-user.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = ''
  password = ''
  invalidLogin = false

  constructor(private router: Router,
              private loginservice: AuthenticationService,
              private getRole:GetUserService,
              ) { }

  ngOnInit() {
  }

  checkLogin() {
    (this.loginservice.authenticate(this.username, this.password).subscribe(
        data => {
          if(data === true) {

            sessionStorage.setItem("username",this.username);
            sessionStorage.setItem("password",this.password);

            (this.getRole.getLoggedUser(this.username).subscribe(
              data=> {
                sessionStorage.setItem("role", String(data.role)),
                  sessionStorage.setItem("id", String(data.id))
              },
                    error1 => alert("Error when trying to get ROLE OF USER!")
            ));



            this.router.navigate([''])
            this.invalidLogin = false
          }
          else{
            this.invalidLogin = true
          }
          },
        error => {
          this.invalidLogin = true

        }
      )
    );

  }

}
