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

  newFlight = new Flight(0, '', '', '', '', '', '', 0, '', 0, 10);
  lista: Flight[];
  
  constructor(private flightService: FlightService, private router: Router) { }

  public onTap(from: string, to: string, fromDest: string, toDest: string) {
    let navigationExtras: NavigationExtras = {
        queryParams: {
            "from": from,
            "to": to,
            "fromDest": fromDest,
            "toDest": toDest
        }
    };
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
