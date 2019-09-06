import { Injectable } from '@angular/core';
import { PlaneSeat } from './plane-seat';
import { Observable } from 'rxjs';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PlaneSeatServiceService {

  private getURL : string;
  private createNewURL: string;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };
  
  constructor(private http: HttpClient) { 
    this.getURL = 'http://localhost:4200/seats/findAll';
    this.createNewURL = "http://localhost:4200/seats/addSeat";
  }

  getAllSeats(): Observable<PlaneSeat[]> {
    return this.http.get<PlaneSeat[]>(this.getURL);
  }

  addSeat(newSeat : PlaneSeat) : Observable<PlaneSeat> {
    console.log(newSeat);
    return this.http.post<PlaneSeat>(this.createNewURL, newSeat).pipe(
        catchError(this.handleError)
    );
  }

  private handleError(err: HttpErrorResponse) {
    // in a real world app, we may send the server to some remote logging infrastructure
    // instead of just logging it to the console
    let errorMessage = '';
    if (err.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      errorMessage = `An error occurred: ${err.error.message}`;
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
    }
    console.error(errorMessage);
    return throwError(errorMessage);
  }
}
