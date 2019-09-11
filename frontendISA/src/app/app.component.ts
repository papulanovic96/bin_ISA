import {Component} from "@angular/core";
import {AuthenticationService} from "./service/authentication.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  title = 'MegaTravel';
  public vin = 'assets/binairlines.jpg';

  loginService : AuthenticationService;
  constructor(loginService:AuthenticationService){
    this.loginService = loginService;
  }


}
