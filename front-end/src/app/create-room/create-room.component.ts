import { Component, OnInit } from '@angular/core';
import {Equipment} from "../book-room/search-room/equipment-room.module";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../services/user.service";
import {RoomService} from "../services/room.service";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-create-room',
  templateUrl: './create-room.component.html',
  styleUrls: ['./create-room.component.css']
})
export class CreateRoomComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<CreateRoomComponent>,
              private formBuilder: FormBuilder,
              private roomService: RoomService) { }

  addRoomForm: FormGroup;
  submitted = false;

  ngOnInit(): void {
    this.addRoomForm = this.formBuilder.group({
      roomName: ['', Validators.required],
      capacity: ['', Validators.required],
      equipments: ['', Validators.required]
    });
  }

  equipments: Equipment[] = [
    new Equipment( 1, 'Computers', ''),
    new Equipment( 2, 'Board', ''),
    new Equipment( 3, 'Projector screen', ''),
    new Equipment( 4, 'Projector', ''),
    new Equipment( 5, 'HDMI', ''),
    new Equipment( 6, 'TV', '')
  ];
   selectedEquipments : string[];
  onFormSubmit() {
    this.submitted = true;
    let  selectedEquipmentsToAdd:string[] = [];
    if (this.addRoomForm.valid) {

      let arr: Equipment[] = this.addRoomForm.value.equipments;
      for (let i = 0; i < arr.length; i++) {
        selectedEquipmentsToAdd.push( arr[i].name);
      }

      this.roomService
        .addRoom(this.addRoomForm.value.roomName, this.addRoomForm.value.capacity,  selectedEquipmentsToAdd.toString());
      window.alert('Room added successfully!');
      this.dialogRef.close();
    } else {
      window.alert("Please, fill all required fields!");
    }
  }
}
