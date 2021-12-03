import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BookRoomComponent } from './book-room/book-room.component';
import { SearchRoomComponent } from './book-room/search-room/search-room.component';
import { RoomsListComponent } from './book-room/rooms-list/rooms-list.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { LoginComponent } from './login/login.component';
import { ReservationEditComponent } from './reservations/reservation-edit/reservation-edit.component';
import { HeaderComponent } from './shared/header/header.component';
import { NavBarComponent } from './shared/nav-bar/nav-bar.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { RegistrationComponent } from './registration/registration.component';
import { FaqComponent } from './faq/faq.component';


import { CreateSpaceComponent } from './create-space/create-space.component';
import { JoinWCodeComponent } from './join-w-code/join-w-code.component';;

import { CustomMaterialModule } from './core/material.module';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './core/app.routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    BookRoomComponent,
    SearchRoomComponent,
    RoomsListComponent,
    ReservationsComponent,
    ReservationEditComponent,
    HeaderComponent,
    NavBarComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    RegistrationComponent,
    ProfileComponent,
    FaqComponent,
    CreateSpaceComponent,
    JoinWCodeComponent
  ],
  imports: [
    BrowserModule,
    BrowserModule,
    CustomMaterialModule,
    RouterModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [CreateSpaceComponent]
})
export class AppModule {
}
