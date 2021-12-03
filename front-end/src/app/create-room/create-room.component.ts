import { Component, OnInit } from '@angular/core';
import {Equipment} from "../book-room/search-room/equipment-room.module";

@Component({
  selector: 'app-create-room',
  templateUrl: './create-room.component.html',
  styleUrls: ['./create-room.component.css']
})
export class CreateRoomComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  equipments: Equipment[] = [
    new Equipment( 1, 'Computers', ''),
    new Equipment( 2, 'Board', ''),
    new Equipment( 3, 'Projector screen', ''),
    new Equipment( 4, 'Projector', ''),
    new Equipment( 5, 'HDMI', ''),
    new Equipment( 6, 'TV', '')
  ];
  selectedEquipments = [];


}
