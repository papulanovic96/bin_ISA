import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FlightListService } from './flight-list.service';
import { Flight } from '../flight';
import { ActivatedRoute, Router, NavigationExtras } from '@angular/router';
import { FlightService } from '../flight.service';
import { AuthenticationService } from 'src/app/service/authentication.service';

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
  public type: string;
  public class: string;
  public bagNum: string;
  public rateOfFlight:number;
  public idOfFlight:number;
  public display = 'none';

  constructor(private flightListService:FlightListService,private flightService: FlightService, private route: ActivatedRoute,
     private router: Router, private fService: FlightListService,private loginService: AuthenticationService) {
    this.route.queryParams.subscribe( params => {
      this.bagNum = params["bag"];
      this.type = params["type"];
      this.class = params["class"];
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

  public preRate(id:number){
    this.idOfFlight = id;
    this.display = 'block';
  }

  public onTap(flight: Flight) {
    let navigationExtras: NavigationExtras = {
        queryParams: {
            "bag": this.bagNum,
            "type" : this.type,
            "class" : this.class,
            "flight" : JSON.stringify(flight)
        }
    };
    this.router.navigate(["planeseats"], navigationExtras);
}

public rateFlight(){
    this.flightListService.rateFlight(this.idOfFlight,this.rateOfFlight).subscribe(
      data=>{
        if(data === true){
          alert("SUCCESS");
          this.display='none';
        }
        else{
          alert("YOU CANT RATE");
          this.display = 'none';
        }
      },
      error1 => alert(error1)
    );
}
closeModal(){
    this.display = 'none';
}}


