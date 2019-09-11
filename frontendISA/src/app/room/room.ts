export class Room {
  constructor(
    public number: number,
    public hotelId: number,
    public pricePerNight: number,
    public newPrice: number,
    public floor: number,
    public roomType: number,
    public avgGrade: number,
    public reserved: boolean
  ) {
  }
}
