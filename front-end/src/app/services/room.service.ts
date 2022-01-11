import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import { HttpHeaders } from '@angular/common/http';
import {AuthService} from "./auth.service";


@Injectable({ providedIn: 'root' })
export class RoomService {

  constructor(private http: HttpClient, authService: AuthService) { }
  authService: AuthService;

  addRoom(roomName: string, capacity: number, equipments: string) {
    let uuid = window.sessionStorage.getItem('org-uuid');
    this.http.post(`${environment.apiUrl}/room/add/${uuid}`, {
      roomName, capacity, equipments
    }).subscribe(
      (data) => {
      },
      (err) => {
        window.alert(err.error.toString());
      }
    );
  }

  getRoomEquipment(userSessionToken: string, roomId: string) : Observable<any> {
    return this.http.get(`${environment.apiUrl}/equipment/get/${roomId}`,
    { headers : {'session-token' : userSessionToken, 'Content-Type': 'application/json'}});
  }

   getAllRooms(userSessionToken: string) : Observable<any> {
    return this.http.get(`${environment.apiUrl}/room/get/rooms`,
    { headers : {'session-token' : userSessionToken, 'Content-Type': 'application/json'}});
   }

}

