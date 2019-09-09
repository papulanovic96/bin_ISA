import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import {AuthGaurdService} from "./service/auth-guard.service";
import {CarComponent} from "./car/car.component";
import {RegisterComponent} from "./register/register.component";
import {RentACarComponent} from "./rent-acar/rent-acar.component";
import {RentACarPriceListComponent} from "./rent-acar-price-list/rent-acar-price-list.component";
import {UserHistoryComponent} from "./user-history/user-history.component";
import { FriendshipComponent } from './friendship/friendship.component';
import { FlightComponent } from './flight/flight.component';
import { AirlineComponent } from './airline/airline.component';
import { PlaneSeatComponent } from './plane-seat/plane-seat.component';
import { FlightListComponent } from './flight/flight-list/flight-list.component';
import {HotelComponent} from "./hotel/hotel.component";
import {RoomComponent} from "./room/room.component";
import {ReservationComponent} from "./reservation/reservation.component";


const appRoutes: Routes = [

  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent},
  { path: 'cars', component: CarComponent},
  { path: 'register', component: RegisterComponent },
  { path: 'carServicePR', component: RentACarPriceListComponent },
  { path: 'carServices', component: RentACarComponent},
  { path: 'userHistory', component: UserHistoryComponent},
  { path: 'profile', component: FriendshipComponent},
  { path: 'airlines', component: AirlineComponent},
  { path: 'planeseats', component: PlaneSeatComponent},
  { path: 'flightList', component: FlightListComponent},
  { path: 'hotel', component: HotelComponent },
  { path: 'room', component: RoomComponent },
  { path: 'reservation', component: ReservationComponent },
  { path: '**', component: FlightComponent}

];

export const routing = RouterModule.forRoot(appRoutes);
@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})


export class AppRoutingModule {}

