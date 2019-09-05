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


@NgModule({
  declarations: [
    AppComponent,
    PlaneSeatComponent,
    AirlineComponent,
    PlaneTicketComponent,
    FlightComponent,
    CarComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    RentACarComponent,


  ],

  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    MDBBootstrapModule,
    AppRoutingModule,
    RouterModule.forRoot([
      {path: 'planeseats', component: PlaneSeatComponent},
      {path: 'airlines', component: AirlineComponent},
      {path: 'cars', component: CarComponent},
      {path: 'register', component:RegisterComponent},


    ], {useHash: true}),
    BrowserAnimationsModule
  ],


providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }