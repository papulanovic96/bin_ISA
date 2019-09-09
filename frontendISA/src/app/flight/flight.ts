import { Airline } from '../airline/airline';

export class Flight {
    constructor(
        public id: number,
        public fromDest: string,
        public toDest: string,
        public dateAndTimeTakeOff: string,
        public dateAndTimeLanding: string,
        public flightTime: string,
        public flightTravelTime: string,
        public transferNumber: number,
        public transferLocation: string,
        public ticketPrice: number,
        public airlineId: Airline
    ) {}
}