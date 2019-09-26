import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FlightService } from './flight.service';
import { Flight } from './flight';
import { Router, NavigationExtras } from '@angular/router';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.css']
})
export class FlightComponent implements OnInit {

  newFlight = new Flight(0, '', '', '', '', '', '', 0, '', 0, 16, null);
  lista: Flight[];
  selectedOption: string;
  selectedOption2: string;
  bagNumber: string;

  options = [
    { name: "One way", value: 1 },
    { name: "Round-trip", value: 2 }
  ]

  options2 = [
    { name: "First class", value: 1 },
    { name: "Economy", value: 2 }
  ]
  constructor(private flightService: FlightService, private router: Router) { }

  public onTap(tripType: string, tripClass: string , bagNum: string, from: string, to: string, fromDest: string, toDest: string) {
    let navigationExtras: NavigationExtras = {
        queryParams: {
            "type": tripType,
            "class": tripClass,
            "bag": bagNum,
            "from": from,
            "to": to,
            "fromDest": fromDest,
            "toDest": toDest
        }
    };
    console.log(tripType, tripClass, bagNum);
    this.router.navigate(["flightList"], navigationExtras);
}

  check(from: string, to: string, fromDest: string, toDest: string){
    return this.flightService.search(from, to, fromDest, toDest).subscribe(
      lista => {this.lista = lista,
      this.router.navigate(['/flightList', lista])}
    )
  }

  ngOnInit() {
  }

}
