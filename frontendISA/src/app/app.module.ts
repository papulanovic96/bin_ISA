import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PlaneSeatComponent } from './plane-seat/plane-seat.component';
import { RouterModule } from '@angular/router';
import { AirlineComponent } from './airline/airline.component';
import { PlaneTicketComponent } from './plane-ticket/plane-ticket.component';
import { FlightComponent } from './flight/flight.component';

@NgModule({
  declarations: [
    AppComponent,
    PlaneSeatComponent,
    AirlineComponent,
    PlaneTicketComponent,
    FlightComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule.forRoot([
      {path: 'planeseats', component: PlaneSeatComponent},
      {path: 'airlines', component: AirlineComponent}
    ], {useHash: true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
