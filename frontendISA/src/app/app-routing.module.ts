import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import {AuthGaurdService} from "./service/auth-guard.service";
import {CarComponent} from "./car/car.component";
import {RegisterComponent} from "./register/register.component";


const appRoutes: Routes = [

  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent,canActivate:[AuthGaurdService] },
  { path: 'cars', component: CarComponent,canActivate:[AuthGaurdService] },
  { path: 'register', component: RegisterComponent },


];

export const routing = RouterModule.forRoot(appRoutes);
@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
