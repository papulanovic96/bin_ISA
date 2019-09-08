import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {PlaneSeatComponent} from './plane-seat/plane-seat.component';
import {RouterModule} from '@angular/router';
import {AirlineComponent} from './airline/airline.component';
import {PlaneTicketComponent} from './plane-ticket/plane-ticket.component';
import {HotelComponent} from './hotel/hotel.component';
import {FormsModule} from '@angular/forms';
import {RoomComponent} from './room/room.component';
import {ReservationComponent} from './reservation/reservation.component';

@NgModule({
  declarations: [
    AppComponent,
    PlaneSeatComponent,
    AirlineComponent,
    PlaneTicketComponent,
    HotelComponent,
    RoomComponent,
    ReservationComponent,
  ],
  imports: [
    FormsModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule.forRoot([
      {path: 'planeseats', component: PlaneSeatComponent},
      {path: 'airlines', component: AirlineComponent},
      {path: 'hotels', component: HotelComponent},
      {path: 'rooms', component: RoomComponent},
      {path: 'reservations', component: ReservationComponent}
    ], {useHash: true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
