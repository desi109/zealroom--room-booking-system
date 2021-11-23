import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { BookRoomComponent } from './book-room/book-room.component';
import { SearchRoomComponent } from './book-room/search-room/search-room.component';
import { RoomsListComponent } from './book-room/rooms-list/rooms-list.component';
import { ReservationsListComponent } from './reservations-list/reservations-list.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { ReservationEditComponent } from './reservations/reservation-edit/reservation-edit.component';
import { ReservationItemComponent } from './reservations/reservation-item/reservation-item.component';
import { RoomItemComponent } from './book-room/room-item/room-item.component';
import { HeaderComponent } from './shared/header/header.component';
import { NavBarComponent } from './shared/nav-bar/nav-bar.component';
import { FooterComponent } from './shared/footer/footer.component';
import {HomeComponent} from "./home/home.component";
import {IconsModule, MDBRootModule} from "angular-bootstrap-md";

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
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    IconsModule,
    MDBRootModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
