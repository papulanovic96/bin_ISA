import { Airline } from '../airline/airline';
import { Ticket } from '../plane-ticket/plane-ticket';

export class PlaneSeat {
    constructor(
        public id: number,
        public reserved: boolean,
        public airlineID: Airline,
        public ticketID: Ticket
    ) {}
}