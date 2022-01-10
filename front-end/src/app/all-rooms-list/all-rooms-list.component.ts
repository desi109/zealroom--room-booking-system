import { Component, OnInit } from '@angular/core';
import {Equipment} from "../book-room/search-room/equipment-room.module";
import { Room } from '../models/room';
import {RoomService} from "../services/room.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-all-rooms-list',
  templateUrl: './all-rooms-list.component.html',
  styleUrls: ['./all-rooms-list.component.css']
})
export class AllRoomsListComponent implements OnInit {
  rooms: Room[];
  constructor(private roomService: RoomService, private http: HttpClient) { }
  d: any;

  ngOnInit() {
    this.d = [];
    let token = window.sessionStorage.getItem('session-token');
    let uuid = window.sessionStorage.getItem('org-uuid');
    let sessionToken;
    if (token != null) {
      let arr: Array<string> = JSON.parse(token);
      for(var index in arr)
      {
        if(index=="sessionToken"){
          sessionToken=arr[index];
        }
      }
    }
    const httpOptions = {
      headers: new HttpHeaders({"session-token" : `${sessionToken}`}), responseType: 'text' as 'json'

    };
    this.http.get(`${environment.apiUrl}/room/get/organizationRooms/${uuid}`,
      httpOptions).subscribe(
      (data)=>{
        this.d.push(JSON.parse(data as any));
        console.log(this.d);
      }
    );

    let a = this.d.roomName;
  }
}
