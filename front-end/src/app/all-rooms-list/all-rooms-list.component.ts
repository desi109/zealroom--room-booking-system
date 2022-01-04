import { Component, OnInit } from '@angular/core';
import {Equipment} from "../book-room/search-room/equipment-room.module";
import { Room } from '../models/room';

@Component({
  selector: 'app-all-rooms-list',
  templateUrl: './all-rooms-list.component.html',
  styleUrls: ['./all-rooms-list.component.css']
})
export class AllRoomsListComponent implements OnInit {
  rooms: Room[];
  /*rooms: Room[] = [
    new Room( 1, 'Room 1', 100,
      [
        new Equipment( 3, 'Projector screen', ''),
        new Equipment( 4, 'Projector', ''),
        new Equipment( 5, 'HDMI', '')
      ]
    ),
    new Room( 2, 'Room 2', 20,
      [
        new Equipment( 1, 'Computers', ''),
        new Equipment( 2, 'Board', '')
      ]
    ),
    new Room( 3, 'Room 3', 30,
      [
        new Equipment( 1, 'Board', ''),
        new Equipment( 6, 'TV', ''),
        new Equipment( 5, 'HDMI', '')
      ]
    ),
    new Room( 4, 'Room 4', 30,
      [
        new Equipment( 1, 'Board', ''),
        new Equipment( 6, 'TV', ''),
        new Equipment( 5, 'HDMI', '')
      ]
    ),
    new Room( 5, 'Room 5', 30,
      [
        new Equipment( 1, 'Board', ''),
        new Equipment( 6, 'TV', ''),
        new Equipment( 5, 'HDMI', '')
      ]
    ),
    new Room( 6, 'Room 6', 30,
      [
        new Equipment( 1, 'Board', ''),
        new Equipment( 6, 'TV', ''),
        new Equipment( 5, 'HDMI', '')
      ]
    )
  ];*/
  constructor() { }

  ngOnInit(): void {
  }

}
