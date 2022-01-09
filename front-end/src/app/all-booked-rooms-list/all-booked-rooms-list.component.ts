import { Component, OnInit } from '@angular/core';
import { Reservation } from "../models/reservation";
import { Equipment } from "../book-room/search-room/equipment-room.module";
import { LoginComponent } from 'src/app/login/login.component';
import { UserService } from '../services/user.service';
import { RoomService } from '../services/room.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-all-booked-rooms-list',
  templateUrl: './all-booked-rooms-list.component.html',
  styleUrls: ['./all-booked-rooms-list.component.css']
})
export class AllBookedRoomsListComponent implements OnInit {

  userId: string = this.loginComponent.userId;
  userFirstName: string = this.loginComponent.userFirstName;
  userLastName: string = this.loginComponent.userLastName;
  userSessionToken: string = this.loginComponent.userSessionToken;
  userEmail: string = this.loginComponent.userEmail;
  userIsAdmin: boolean = this.loginComponent.userIsAdmin;
  reservations: Reservation[];
  errorMessage = '';
  dataLenght: number = 0;
  equipment: string[];

  getUserBoookings() {
    this.userService.getUserBookings(this.userSessionToken)
    .toPromise()
    .then(response => response as Reservation[])
    .catch(this.handleError);
      /*.subscribe({
        next: data => {
           this.reservations = data;
           
        },
        error: err => {
          this.errorMessage = err.error.message;
        }
      });*/
  }

  private handleError(error: any): Promise<Array<any>> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

  getRoomEquipment(roomId: string): any {
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

  /*reservations2: Reservation[] = [
    new Reservation(
      1,
      new Room(1, 'Room 1', 100, [
        new Equipment(3, 'Projector screen', ''),
        new Equipment(4, 'Projector', ''),
        new Equipment(5, 'HDMI', ''),
      ]),
      '2021, 12, 21 10:20 AM',
      '2021, 12, 21 11:00 AM'
    ),
    new Reservation(
      2,
      new Room(2, 'Room 2', 20, [
        new Equipment(1, 'Computers', ''),
        new Equipment(2, 'Board', ''),
      ]),
      '2021, 12, 23 8:00 AM',
      '2021, 12, 23 8:40 AM'
    ),
    new Reservation(
      3,
      new Room(3, 'Room 3', 30, [
        new Equipment(1, 'Board', ''),
        new Equipment(6, 'TV', ''),
        new Equipment(5, 'HDMI', ''),
      ]),
      '2021, 12, 22 9:20 AM',
      '2021, 12, 22 12:00 AM'
    ),
  ];*/

  constructor( private userService: UserService, public loginComponent: LoginComponent, 
    private roomService: RoomService, private authService: AuthService){ }

  ngOnInit(): void { }

}
