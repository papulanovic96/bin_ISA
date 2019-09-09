import { Component, OnInit } from '@angular/core';
import { FlightService } from './flight.service';
import { Flight } from './flight';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.css']
})
export class FlightComponent implements OnInit {

  newFlight = new Flight(0, '', '', '', '', '', '', 0, '', 0, null);

  constructor(private flightService: FlightService) { }

  save(flight: Flight) {
    return this.flightService.save(flight).subscribe(
      newFlight => this.newFlight = newFlight
    )
  }

  ngOnInit() {
  }

}
