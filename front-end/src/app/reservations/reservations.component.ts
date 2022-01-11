import { Component, OnInit } from '@angular/core';
import { stringify } from 'querystring';
import { Equipment } from '../book-room/search-room/equipment-room.module';
import { LoginComponent } from '../login/login.component';
import { Reservation } from '../models/reservation';
import { Room } from '../models/room';
import { AuthService } from '../services/auth.service';
import { RoomService } from '../services/room.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css'],
})
export class ReservationsComponent implements OnInit {
  /*public id: number;
  public room: Room;
  public startTime: string;
  public endTime: string;*/
  
  constructor(private userService: UserService, public loginComponent: LoginComponent, public authService: AuthService, public roomService: RoomService){ }

  userSessionToken: string;
  reservations: Reservation[];
  errorMessage = '';
  dataLenght: number = 0;
  equipment: string[];

  getUserBoookings() {
    const user = this.authService.getUser();
    this.userSessionToken = user.sessionToken;

    this.userService.getUserBookings(this.userSessionToken)
      .subscribe({
        next: data => {
           this.reservations = data as Reservation[];
           
        },
        error: err => {
          this.errorMessage = err.error.message;
        }
      });
  }

    getRoomEquipment(roomId: string) {
      const user = this.authService.getUser();
      this.userSessionToken = user.sessionToken;
  
      this.roomService.getRoomEquipment(this.userSessionToken, roomId)
      .subscribe({
        next: data => {
           this.equipment = data as string[];
           
        },
        error: err => {
          this.errorMessage = err.error.message;
        }
      });
  }

  getEquipment() {
    return this.equipment;
  }

  deleteUserReservation(reservationId: string) {
    const user = this.authService.getUser();
    this.userSessionToken = user.sessionToken;

    this.userService.deleteUserReservation(this.userSessionToken, reservationId)
    .subscribe({
        next: data => {
           console.log(data);
           
        },
        error: err => {
          this.errorMessage = err.error.message;
        }
      });
  }


  ngOnInit(): void {
    this.getUserBoookings();
  }

  getDateOnly(date: Date): string{
    return date.toDateString();
  }
}



