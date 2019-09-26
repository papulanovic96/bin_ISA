import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PlaneTicketServiceService } from '../plane-ticket/plane-ticket-service.service';
import { Ticket } from '../plane-ticket/plane-ticket';
import { Flight } from '../flight/flight';
import { PlaneSeat } from '../plane-seat/plane-seat';
import { AuthenticationService } from '../service/authentication.service';
import { GetUserService } from '../service/get-user.service';
import { User } from '../register/user';
import { Observable } from 'rxjs';
import { Airline } from '../airline/airline';
import { PlaneSeatServiceService } from '../plane-seat/plane-seat-service.service';

@Component({
  selector: 'app-ticket-finalize',
  templateUrl: './ticket-finalize.component.html',
  styleUrls: ['./ticket-finalize.component.css']
})
export class TicketFinalizeComponent implements OnInit {

  public id: number;
  public fromDestination: string;
  public toDestination: string;
  public takeOff: string;
  public landing: string;
  public flightTime: string;
  public travelTime: string;
  public transNumber: number;
  public transLocation: string;
  public ticketPrice: number;
  public airlineId: number;

  public seat_id: number[];
  public reserved: boolean;
  public airlineseatID: number;
  public ticketID: number;
  public stri: string;
  public flight: Flight;
  public ticketNew: Ticket;
  public seatNew: PlaneSeat[];
  public reservedBy: User;
  public type: string;
  public class: string;
  public bag: string;

  constructor(private router: Router, private tService: PlaneTicketServiceService, private route: ActivatedRoute, 
    private aut: AuthenticationService, private getUserService: GetUserService, private seatService: PlaneSeatServiceService) { 
   this.route.queryParams.subscribe( params => {
     this.bag = params["bag"];
     this.type = params["type"];
     this.class = params["class"]; 
     this.flight = JSON.parse(params["flight"]);
     this.seatNew = JSON.parse(params["pSeats"]);
   })
   this.id = Math.floor((Math.random() * 100000) + 1);
   this.ticketNew = new Ticket(this.id, false, this.seatNew.length, this.type, this.class, this.bag, this.flight.id, null, aut.getLogged(), this.flight.airline);

   tService.create(this.ticketNew).subscribe(
     ticketNew => { this.ticketNew = ticketNew}
   );
   this.seatNew.forEach(element => {
    element.ticketID = this.ticketNew.id;
    seatService.modify(element.id, element).subscribe();
  });
  this.ticketNew.seat = this.seatNew;
  
  }

  ngOnInit() {
    console.log(this.ticketNew);
  }

  goHotels() {
    console.log(this.seatNew);
    this.router.navigate(["/hotel"]);
  }

}
