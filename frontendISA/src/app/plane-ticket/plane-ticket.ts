import { Airline } from '../airline/airline';

export class Ticket {
    constructor(
        public id: number,
        public airlineID: Airline
    ){

    }
}