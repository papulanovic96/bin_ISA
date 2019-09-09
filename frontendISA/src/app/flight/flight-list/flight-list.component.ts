import { Component, OnInit } from '@angular/core';
import { FlightListService } from './flight-list.service';
import { Flight } from '../flight';

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.css']
})
export class FlightListComponent implements OnInit {

  lista: Flight[];

  constructor(private flightService: FlightListService) { }

  ngOnInit() {
    this.flightService.pronadjiSve().subscribe(
      lista => this.lista = lista
    )
  }

}
