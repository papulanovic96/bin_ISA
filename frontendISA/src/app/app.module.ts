import {HotelComponent} from './hotel/hotel.component';
import {RoomComponent} from './room/room.component';
import {ReservationComponent} from './reservation/reservation.component';
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
import { CarComponent } from './car/car.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MDBBootstrapModule } from "angular-bootstrap-md";
import { RentACarComponent } from './rent-acar/rent-acar.component';
import { RentACarPriceListComponent } from './rent-acar-price-list/rent-acar-price-list.component';
import { RatingModule } from 'ngx-bootstrap/rating';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { UserHistoryComponent } from './user-history/user-history.component';
import { FriendshipComponent } from './friendship/friendship.component';
import { FlightListComponent } from './flight/flight-list/flight-list.component';
import { FriendInviteComponent } from './friend-invite/friend-invite.component';

@NgModule({
  declarations: [
    AppComponent,
    PlaneSeatComponent,
    AirlineComponent,
    PlaneTicketComponent,
    HotelComponent,
    RoomComponent,
    ReservationComponent,
    FlightComponent,
    CarComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    RentACarComponent,
    RentACarPriceListComponent,
    UserHistoryComponent,
    FriendshipComponent,
    FlightListComponent,
    FriendInviteComponent,
  ],

  imports: [
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    MDBBootstrapModule,
    AppRoutingModule,
    RatingModule.forRoot(),
    BsDatepickerModule.forRoot(),
    BrowserAnimationsModule
  ],


providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }

