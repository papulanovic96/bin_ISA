import { Airline } from '../airline/airline';
import { Flight } from '../flight/flight';
import { PlaneSeat } from '../plane-seat/plane-seat';
import { User } from '../register/user';

export class Ticket {
    constructor(
        public id: number,
        public discount: boolean,
        public userNumber: number,
        public tripType: string,
        public tripClass: string,
        public bag: string,
        public flight: number,
        public seat: PlaneSeat[],
        public reservedBy: string,
        public airline: number
    ){

    }
}