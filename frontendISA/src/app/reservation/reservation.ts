export class Reservation {
  constructor(
    public id: number,
    public hotelId: number,
    public roomId: number,
    public userId: number,
    public arrivalDate: Date,
    public numberOfNights: number,
    public numberOfGuests: number,
    public additionalServices: String[]
  ) {
  }
}
