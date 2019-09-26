import { Component, OnInit } from '@angular/core';
import { PlaneSeat } from './plane-seat';
import { PlaneSeatServiceService } from './plane-seat-service.service';
import { Airline } from '../airline/airline';
import { Ticket } from '../plane-ticket/plane-ticket';
import { Router, NavigationExtras, ActivatedRoute } from '@angular/router';
import { Flight } from '../flight/flight';

@Component({
  selector: 'app-plane-seat',
  templateUrl: './plane-seat.component.html',
  styleUrls: ['./plane-seat.component.css']
})
export class PlaneSeatComponent implements OnInit {

  listOfSeats: PlaneSeat[];
  seat = new PlaneSeat(15, false, 2, 12);
  listOfReservedSeats: Array<PlaneSeat> = [];

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
  public flight: Flight;
  public navigationExtras: NavigationExtras;
  public newList: PlaneSeat[];
  public type: string;
  public class: string;
  public bag: string;

  constructor(private planeSeatService: PlaneSeatServiceService, private router: Router, private route: ActivatedRoute) {
    this.route.queryParams.subscribe( params => {
      this.bag = params["bag"];
      this.type = params["type"];
      this.class = params["class"];
      this.flight = JSON.parse(params["flight"]);
 })

   }

  ngOnInit() {
    this.planeSeatService.getAllSeats().subscribe(
      listOfSeats =>  this.listOfSeats = listOfSeats
    );
  }

  action(item: PlaneSeat){
    if(item.reserved == true){
      item.reserved = false;
      console.log(item); 
        var idx = this.listOfReservedSeats.indexOf(item);
        if (idx != -1) {
            console.log(this.listOfReservedSeats);
             this.listOfReservedSeats.splice(idx, 1);
        } else {
           return false;
        }
    } else {
      item.reserved = true;
      this.planeSeatService.modify(item.id, item);
      console.log(item);
      this.listOfReservedSeats.push(item);
      console.log(this.listOfReservedSeats);
    }
  }

nextFunc() {
  if(this.listOfReservedSeats.length > 1){
    this.navigationExtras = {
      queryParams: {
            "bag" : this.bag,
            "type" : this.type,
            "class" : this.class,
            "flight": JSON.stringify(this.flight),
            "pSeats": JSON.stringify(this.listOfReservedSeats)
      }
    }
    this.router.navigate(['/friendinvite'], this.navigationExtras);
    console.log(this.navigationExtras);
  } else {
    this.navigationExtras = {
      queryParams: {
        "bag" : this.bag,
        "type" : this.type,
        "class" : this.class,
        "flight": JSON.stringify(this.flight),
        "pSeats": JSON.stringify(this.listOfReservedSeats)
      }
    }
    this.router.navigate(['/ticketFinale'], this.navigationExtras);
    console.log(this.navigationExtras);
  }
}

}
