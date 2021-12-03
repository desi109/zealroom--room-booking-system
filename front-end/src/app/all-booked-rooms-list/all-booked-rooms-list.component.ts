import { Component, OnInit } from '@angular/core';
import {Reservation} from "../reservations/reservation.module";
import {Room} from "../book-room/room.module";
import {Equipment} from "../book-room/search-room/equipment-room.module";

@Component({
  selector: 'app-all-booked-rooms-list',
  templateUrl: './all-booked-rooms-list.component.html',
  styleUrls: ['./all-booked-rooms-list.component.css']
})
export class AllBookedRoomsListComponent implements OnInit {

  reservations: Reservation[] = [
    new Reservation(
      1,
      new Room(1, 'Room 1', 100, [
        new Equipment(3, 'Projector screen', ''),
        new Equipment(4, 'Projector', ''),
        new Equipment(5, 'HDMI', ''),
      ]),
      new Date(2021, 12, 21),
      '10:20 AM',
      '11:00 AM'
    ),
    new Reservation(
      2,
      new Room(2, 'Room 2', 20, [
        new Equipment(1, 'Computers', ''),
        new Equipment(2, 'Board', ''),
      ]),
      new Date(2021, 12, 23,),
      '8:00 AM',
      '8:40 AM'
    ),
    new Reservation(
      3,
      new Room(3, 'Room 3', 30, [
        new Equipment(1, 'Board', ''),
        new Equipment(6, 'TV', ''),
        new Equipment(5, 'HDMI', ''),
      ]),
      new Date(2021, 12, 22),
      '9:20 AM',
      '12:00 AM'
    ),
  ];

  constructor() { }

  ngOnInit(): void {
  }

  getDateOnly(date: Date): string{
    return date.toDateString();
  }

}
