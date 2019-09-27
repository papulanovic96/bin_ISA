import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "./service/authentication.service";
import { Ticket } from './plane-ticket/plane-ticket';
import { PlaneTicketServiceService } from './plane-ticket/plane-ticket-service.service';
import { FlightListService } from './flight/flight-list/flight-list.service';
import { Flight } from './flight/flight';
import { IfStmt } from '@angular/compiler';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{

  public query: string;

  title = 'MegaTravel';
  public vin = 'assets/binairlines.jpg';
  list: Ticket[];
  list2: Flight[];

  loginService : AuthenticationService;
  constructor(loginService: AuthenticationService, private serviceTicket: PlaneTicketServiceService,
    private serviceFlight: FlightListService){
    this.loginService = loginService;
    this.query = "starbucks";
  }

  ngOnInit(): void {
    this.serviceTicket.returnAll().subscribe(
      list => {this.list = list}
    )
    this.serviceFlight.pronadjiSve().subscribe(
      list2 => {this.list2 = list2}
    )
  }
  
  reserve(ticket: Ticket){
    if(this.loginService.isUserLoggedIn)
    {
      ticket.reservedBy = this.loginService.getLogged();
      this.serviceTicket.reserve(ticket.id, ticket).subscribe();
    } else {
      alert("You need to be logged in to reserve!");
    }
  }
}
