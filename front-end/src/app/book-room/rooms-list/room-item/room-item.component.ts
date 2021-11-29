import { Component, OnInit } from '@angular/core';
import { RoomsListComponent } from '../rooms-list.component';

@Component({
  selector: 'app-room-item',
  templateUrl: './room-item.component.html',
  styleUrls: ['./room-item.component.css']
})

export class RoomItemComponent implements OnInit {

  constructor(
    private roomsListComponent: RoomsListComponent
  ) { }

  ngOnInit(): void {
    //this.rooms$ = this.roomsListComponent.getRooms();
  }

}
