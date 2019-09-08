export class Car {
  constructor (
    public nos:number,
  public regID: string,
  public model: string,
  public year: number,
  public serviceId:number,
  public serviceName:string,
  public type: string,
  public convertible : boolean,
  public deleted : boolean,
  public reserved : boolean,
  public id:number,
  public avgGrade:number
  ){};
}
