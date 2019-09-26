import { Component, OnInit } from '@angular/core';
import { Ticket } from './plane-ticket';
import { PlaneTicketServiceService } from './plane-ticket-service.service';

@Component({
  selector: 'app-plane-ticket',
  templateUrl: './plane-ticket.component.html',
  styleUrls: ['./plane-ticket.component.css']
})
export class PlaneTicketComponent implements OnInit {

  listOfTickets: Ticket[];

  constructor(private ptService: PlaneTicketServiceService) { }

  ngOnInit() {
    this.ptService.returnAll().subscribe(
      listOfTickets => this.listOfTickets = listOfTickets
    )
  }

}
