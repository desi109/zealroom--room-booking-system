import { Component, OnInit } from '@angular/core';
import { Room } from '../room.module';
import { Equipment } from '../search-room/equipment-room.module';

@Component({
  selector: 'app-rooms-list',
  templateUrl: './rooms-list.component.html',
  styleUrls: ['./rooms-list.component.css'],
})
export class RoomsListComponent implements OnInit {
  rooms: Room[] = [
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
  ];

  constructor() {}

  ngOnInit(): void {}
}
