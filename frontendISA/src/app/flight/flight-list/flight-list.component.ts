import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FlightListService } from './flight-list.service';
import { Flight } from '../flight';
import { ActivatedRoute, Router } from '@angular/router';
import { FlightService } from '../flight.service';

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.css']
})
export class FlightListComponent implements OnInit {

  lista: Flight[];
  public from: string;
  public to: string;
  public fromDest: string;
  public toDest: string;
  
  constructor(private flightService: FlightService, private route: ActivatedRoute,
     private router: Router, private fService: FlightListService) { 
    this.route.queryParams.subscribe( params => {
      this.from = params["from"];
      this.to = params["to"];
      this.fromDest = params["fromDest"];
      this.toDest = params["toDest"];
    })
  }

  ngOnInit() {
      this.flightService.search(this.from, this.to, this.fromDest, this.toDest).subscribe(
        lista => {this.lista = lista}
      )
  }

  nextPage(flight: Flight){
    console.log(flight);
    this.router.navigate(['/planeseats']);
  }

}
