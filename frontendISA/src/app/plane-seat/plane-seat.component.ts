import { Component, OnInit } from '@angular/core';
import { PlaneSeat } from './plane-seat';
import { PlaneSeatServiceService } from './plane-seat-service.service';

@Component({
  selector: 'app-plane-seat',
  templateUrl: './plane-seat.component.html',
  styleUrls: ['./plane-seat.component.css']
})
export class PlaneSeatComponent implements OnInit {

  listOfSeats: PlaneSeat[];
  seat = new PlaneSeat(0, false, null, null);

  constructor(private planeSeatService: PlaneSeatServiceService) { }

  ngOnInit() {
    this.planeSeatService.getAllSeats().subscribe(
      listOfSeats =>  this.listOfSeats = listOfSeats
    );
  }

}
