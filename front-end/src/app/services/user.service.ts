import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from "../models/user";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import { HttpHeaders } from '@angular/common/http';
import {AuthService} from "./auth.service";
import * as EventEmitter from "events";

@Injectable({ providedIn: 'root' })
export class UserService {

  constructor(private http: HttpClient, authService: AuthService) { }
  authService: AuthService;
  public moderatorEvent: EventEmitter = new EventEmitter();

  register(user: User) {
    return this.http.post(`${environment.apiUrl}/user/register`, user);
  }

  logout(user: Observable<User>) {
    return this.http.put(`${environment.apiUrl}/user/logout`, user);
  }

  registerOrganization(name: string, type: string) {
    let token = window.sessionStorage.getItem('session-token');
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
      headers: new HttpHeaders({"session-token" : `${sessionToken}`}),responseType: 'text' as 'json'

    };
    return this.http.post(`${environment.apiUrl}/organization/register`, {
      name,
      type
    }, httpOptions);
  }

  joinWithCode(code: string) {
    let token = window.sessionStorage.getItem('session-token');
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
      headers: new HttpHeaders({"session-token" : `${sessionToken}`}),responseType: 'text' as 'json'

    };
    return this.http.post(`${environment.apiUrl}/organization/join/${code}`,{},
      httpOptions);
  }

  generateCode() {
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
    //console.log("WAIT");
    //console.log(organization_uuid);

    const httpOptions = {
      headers: new HttpHeaders({"session-token" : `${sessionToken}`}),responseType: 'text' as 'json'

    };
    console.log("WAIT3");
    return this.http.put(`${environment.apiUrl}/organization/generate/inviteToken/${uuid}`,{},
       httpOptions);
  }

  generateCodeModerator() {
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
    return this.http.put(`${environment.apiUrl}/organization/generate/moderatorInviteToken/${uuid}`,{},
      httpOptions);
  }

  getUserBookings(userSessionToken: string) {
    return this.http.get(`${environment.apiUrl}/user/get/bookings`,
    { headers : {'session-token' : userSessionToken, 'Content-Type': 'application/json'}});
  }

  getOrganizations() {
    let token = window.sessionStorage.getItem('session-token');
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
    const observable = this.http.get(`${environment.apiUrl}/organization/all`,
      httpOptions);
    observable.subscribe(
      (data)=>{
        window.sessionStorage.setItem("org-uuid", data.toString());
      })
    return observable;
  }

  isModerator() {
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
    this.http.get(`${environment.apiUrl}/organization/${uuid}/isModerator`,
      httpOptions).subscribe(
      (data)=>{
        const currentModeratorStatus = window.sessionStorage.getItem("isModerator");
        if(data == true) {
          this.moderatorEvent.emit('moderatorRightsGranted', true);
        }
        if(data == false) {
          this.moderatorEvent.emit('moderatorRightsRejected', false);
        }
        if(data != currentModeratorStatus) {
          window.sessionStorage.setItem("isModerator", data.toString());
          this.moderatorEvent.emit('change', data);
        }
      }
    );
  }

  deleteUserReservation(userSessionToken: string, reservationId: string) : Observable<any> {
    return this.http.delete(`${environment.apiUrl}/booking/delete/${reservationId}`,
    { headers : {'session-token' : userSessionToken, 'Content-Type': 'application/json'}});
  }

  bookRoom(userSessionToken: string, reservationId: string) : Observable<any> {
    return this.http.post(`${environment.apiUrl}/room/book/room`,
    { headers : {'session-token' : userSessionToken, 'Content-Type': 'application/json'}});
  }

}
