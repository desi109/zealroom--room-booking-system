import { Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PickerInteractionMode } from 'igniteui-angular';
import { Equipment } from './equipment-room.module';

@Component({
  selector: 'app-search-room',
  templateUrl: './search-room.component.html',
  styleUrls: ['./search-room.component.css'],
})
export class SearchRoomComponent implements OnInit {

  minDate = new Date(2021, 0, 1);
  maxDate = new Date(2025, 0, 1);

  public mode: PickerInteractionMode = PickerInteractionMode.DropDown;
  public format = 'hh:mm tt';
  public date: Date = new Date();
  
  minCapacity = 0;
  maxCapacity = 100000;

  public capacity: number = 0;

  constructor () {}

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

  formatLabel(capacity: number) {
    this.capacity = capacity;
    if (this.capacity >= 1000) {
      return Math.round(this.capacity / 1000);
    }

    return this.capacity;
  }
}

