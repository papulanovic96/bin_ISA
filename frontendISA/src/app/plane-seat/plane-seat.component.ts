import { Component, OnInit } from '@angular/core';
import { PlaneSeat } from './plane-seat';
import { PlaneSeatServiceService } from './plane-seat-service.service';
import { Airline } from '../airline/airline';
import { Ticket } from '../plane-ticket/plane-ticket';

@Component({
  selector: 'app-plane-seat',
  templateUrl: './plane-seat.component.html',
  styleUrls: ['./plane-seat.component.css']
})
export class PlaneSeatComponent implements OnInit {

  listOfSeats: PlaneSeat[];
  seat = new PlaneSeat(15, false, 2, 12);

  constructor(private planeSeatService: PlaneSeatServiceService) { }

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

}
