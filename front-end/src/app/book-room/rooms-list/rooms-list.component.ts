import { Component, OnInit } from '@angular/core';
import { Room } from 'src/app/models/room';
import { AuthService } from 'src/app/services/auth.service';
import { RoomService } from 'src/app/services/room.service';
import { UserService } from 'src/app/services/user.service';
import { Equipment } from '../search-room/equipment-room.module';

@Component({
  selector: 'app-rooms-list',
  templateUrl: './rooms-list.component.html',
  styleUrls: ['./rooms-list.component.css'],
})
export class RoomsListComponent implements OnInit {
  rooms: Room[];
  userSessionToken: string;
  errorMessage: any;
  equipment: string = "";

  getAllRooms() {
    const user = this.authService.getUser();
    this.userSessionToken = user.sessionToken;

    this.roomService.getAllRooms(this.userSessionToken)
      .subscribe({
        next: data => {
           this.rooms = data as Room[];
           
        },
        error: err => {
          this.errorMessage = err.error.message;
        }
      });
  }

  getEquipment(roomId: string) {
    const user = this.authService.getUser();
    this.userSessionToken = user.sessionToken;

    this.roomService.getRoomEquipment(this.userSessionToken, roomId)
    .subscribe({
      next: data => {
        this.equipment = data.toString();
         
      },
      error: err => {
        this.errorMessage = err.error.message;
      }
    });
  }

  constructor(private authService: AuthService, private roomService: RoomService, private userService: UserService) {}

  ngOnInit(): void {
    this.getAllRooms();
  }

  bookRoom(roomId: string) {
    const user = this.authService.getUser();
    this.userSessionToken = user.sessionToken;

    this.userService.bookRoom(this.userSessionToken, roomId)
    .subscribe({
        next: data => {
           console.log(data);
           
        },
        error: err => {
          this.errorMessage = err.error.message;
        }
      });
  }
}
