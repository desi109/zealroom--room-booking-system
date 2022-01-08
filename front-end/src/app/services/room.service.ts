import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from "../models/user";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import { HttpHeaders } from '@angular/common/http';
import {AuthService} from "./auth.service";
import {Equipment} from "../book-room/search-room/equipment-room.module";


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
}
