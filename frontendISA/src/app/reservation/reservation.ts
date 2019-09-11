export class Reservation {
  constructor(
    public id: number,
    public hotelId: number,
    public roomId: number,
    public sumPrice: number,
    public userUsername: String,
    public arrivalDate: Date,
    public numberOfNights: number,
    public numberOfGuests: number,
    public additionalServices: String[]
  ) {
  }
}
