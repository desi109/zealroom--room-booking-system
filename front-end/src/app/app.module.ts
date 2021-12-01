import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { BookRoomComponent } from './book-room/book-room.component';
import { SearchRoomComponent } from './book-room/search-room/search-room.component';
import { RoomsListComponent } from './book-room/rooms-list/rooms-list.component';
import { ReservationsListComponent } from './reservations-list/reservations-list.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { LoginComponent } from './login/login.component';

import { ReservationEditComponent } from './reservations/reservation-edit/reservation-edit.component';
import { ReservationItemComponent } from './reservations/reservation-item/reservation-item.component';
import { RoomItemComponent } from './book-room/rooms-list/room-item/room-item.component';
import { HeaderComponent } from './shared/header/header.component';
import { NavBarComponent } from './shared/nav-bar/nav-bar.component';
import { FooterComponent } from './shared/footer/footer.component';
import {HomeComponent} from "./home/home.component";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { CustomMaterialModule } from './core/material.module';

import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatMenuModule} from "@angular/material/menu";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { RegistrationComponent } from './registration/registration.component';
import { ProfileComponent } from './profile/profile.component';

import { RouterModule } from '@angular/router';
import { AppRoutingModule } from "./core/app.routing.module";
import { FaqComponent } from './faq/faq.component';
import {MatExpansionModule} from "@angular/material/expansion";
import { CreateSpaceComponent } from './create-space/create-space.component';
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatDialogModule} from '@angular/material/dialog';
import { JoinWCodeComponent } from './join-w-code/join-w-code.component';;


@NgModule({
  declarations: [
    AppComponent,
    BookRoomComponent,
    SearchRoomComponent,
    RoomsListComponent,
    ReservationsListComponent,
    ReservationsComponent,
    ReservationEditComponent,
    ReservationItemComponent,
    RoomItemComponent,
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
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    FormsModule,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatCardModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,
    FormsModule,
    RouterModule,
    AppRoutingModule,
    MatExpansionModule,
    MatOptionModule,
    MatSelectModule,
    MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [CreateSpaceComponent]
})
export class AppModule { }
