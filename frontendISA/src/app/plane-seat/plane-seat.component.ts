import { Component, OnInit } from '@angular/core';
import { PlaneSeat } from './plane-seat';
import { PlaneSeatServiceService } from './plane-seat-service.service';
import { Airline } from '../airline/airline';
import { Ticket } from '../plane-ticket/plane-ticket';
import { Router } from '@angular/router';

@Component({
  selector: 'app-plane-seat',
  templateUrl: './plane-seat.component.html',
  styleUrls: ['./plane-seat.component.css']
})
export class PlaneSeatComponent implements OnInit {

  listOfSeats: PlaneSeat[];
  seat = new PlaneSeat(15, false, 2, 12);
  listOfReservedSeats: Array<PlaneSeat> = [];

  constructor(private planeSeatService: PlaneSeatServiceService, private router: Router) { }

  ngOnInit() {
    this.planeSeatService.getAllSeats().subscribe(
      listOfSeats =>  this.listOfSeats = listOfSeats
    );
  }

  onSubmit() {
    this.planeSeatService.addSeat(this.seat).subscribe(
      seat => this.listOfSeats.push(seat)
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
      console.log(item);
      this.listOfReservedSeats.push(item);
      console.log(this.listOfReservedSeats);
    }
  }

  nextFunc() {
    if(this.listOfReservedSeats.length > 1){
      this.router.navigate(['/friendinvite']);
    } else {
      this.router.navigate(['/hotel']);
    }
  }

}
