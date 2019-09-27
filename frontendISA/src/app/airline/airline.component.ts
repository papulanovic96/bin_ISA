import { Component, OnInit } from '@angular/core';
import { AirlineServiceService } from './airline-service.service';
import { Airline } from './airline';

@Component({
  selector: 'app-airline',
  templateUrl: './airline.component.html',
  styleUrls: ['./airline.component.css']
})
export class AirlineComponent implements OnInit {

  private listOfAirline: Airline[];
  private airline = new Airline(0, '', '', '', '');

  constructor(private airlineService : AirlineServiceService) { }

  ngOnInit() {
    this.airlineService.getAllAirlines().subscribe(
      listOfAirline => {this.listOfAirline = listOfAirline,
        this.listOfAirline.forEach(element => {
          this.airline = element;
        });}
    );
    
  }

  change(airlineNew: Airline) {
    return this.airlineService.changeIT(airlineNew.id, airlineNew).subscribe();
  }

}
