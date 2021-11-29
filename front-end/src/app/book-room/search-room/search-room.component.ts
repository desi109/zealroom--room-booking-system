import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from "@angular/forms";


@Component({
  selector: 'app-search-room',
  templateUrl: './search-room.component.html',
  styleUrls: ['./search-room.component.css']
})
export class SearchRoomComponent implements OnInit {

  constructor() { }

  minDate = new Date(2021,0,1);
  maxDate = new Date(2025,0,1);

  ngOnInit(): void {
  
  }

}
