import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ticket } from './plane-ticket';

@Injectable({
  providedIn: 'root'
})
export class PlaneTicketServiceService {

  urlGetAll = 'http://localhost:4200/ticket/findAll';
  urlCreate = 'http://localhost:4200/ticket/create';
  urlResrve = 'http://localhost:4200/ticket/reserve';

  constructor(private http: HttpClient) { }

  returnAll() {
    return this.http.get<Ticket[]>(this.urlGetAll);
  }

  create(ticket: Ticket){
    return this.http.post<Ticket>(this.urlCreate, ticket);
  }

  reserve(id: number, ticket: Ticket) {
    return this.http.put(this.urlResrve + "/" + id, ticket);
  }

}
