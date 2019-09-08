export class CarService{
  constructor(
    public id:number,
  public avgGrade:number,
  public carServiceName:string,
  public carServiceAddress:string,
  public carServiceDescription:string,
  public carServiceLocation:string,
  public deleted:boolean
  ){}
}
