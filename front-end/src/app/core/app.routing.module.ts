import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import {BookRoomComponent} from "../book-room/book-room.component";
import {ReservationsComponent} from "../reservations/reservations.component";
import {ProfileComponent} from "../profile/profile.component";
import {LoginComponent} from "../login/login.component";
import {RegistrationComponent} from "../registration/registration.component";
import {FaqComponent} from "../faq/faq.component";
import {CreateSpaceComponent} from "../create-space/create-space.component";
import {ModeratorComponent} from "../moderator/moderator.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'book-room', component: BookRoomComponent },
  { path: 'reservations', component: ReservationsComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'faq', component: FaqComponent },
  { path: 'moderator', component: ModeratorComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
