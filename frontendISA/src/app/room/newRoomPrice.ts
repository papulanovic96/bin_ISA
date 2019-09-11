export class NewRoomPrice {
  constructor(
    public id: number,
    public roomId: number,
    public newPrice: number,
    public startDate: Date,
    public duration: number
  ) {
  }
}
