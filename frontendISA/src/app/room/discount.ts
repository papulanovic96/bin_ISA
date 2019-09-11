export class Discount {
  constructor(
    public id: number,
    public roomId: number,
    public newPrice: number,
    public startDate: Date,
    public duration: number,
    public additionalServices: String[],
    public destination: String
  ) {
  }
}
