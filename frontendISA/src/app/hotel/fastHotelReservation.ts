export class FastHotelReservation {
  constructor(
    public id: number,
    public discountId: number,
    public sumPrice: number,
    public userUsername: String,
    public arrivalDate: Date,
    public numberOfNights: number,
    public destination:String
  ) {
  }
}
