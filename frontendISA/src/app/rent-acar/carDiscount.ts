export class CarDiscount {
  constructor(
    public id: number,
    public carId: number,
    public userId: number,
    public Model: string,
    public nos: number,
    public startDate: Date,
    public endDate: Date,
    public price: number,
    public percent: number
  ) {}
}
