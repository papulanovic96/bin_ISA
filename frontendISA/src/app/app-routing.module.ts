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

const appRoutes: Routes = [

  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent,canActivate:[AuthGaurdService] },
  { path: 'cars', component: CarComponent,canActivate:[AuthGaurdService] },
  { path: 'register', component: RegisterComponent },
  { path: 'carServicePR', component: RentACarPriceListComponent,canActivate:[AuthGaurdService] },
  { path: 'carServices', component: RentACarComponent,canActivate:[AuthGaurdService] },
  { path: 'userHistory', component: UserHistoryComponent},
  { path: 'profile', component: FriendshipComponent}
];

export const routing = RouterModule.forRoot(appRoutes);
@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
