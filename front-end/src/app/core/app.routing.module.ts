import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import {BookRoomComponent} from "../book-room/book-room.component";
import {ReservationsComponent} from "../reservations/reservations.component";
import {ProfileComponent} from "../profile/profile.component";
import {LoginComponent} from "../login/login.component";
import {RegistrationComponent} from "../registration/registration.component";

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'bookRoom', component: BookRoomComponent },
  { path: 'reservations', component: ReservationsComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
