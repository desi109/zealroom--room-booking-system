import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from "../models/user";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import { HttpHeaders } from '@angular/common/http';
import {AuthService} from "./auth.service";


@Injectable({ providedIn: 'root' })
export class UserService {

  constructor(private http: HttpClient, authService: AuthService) { }
  authService: AuthService;
  register(user: User) {
    return this.http.post(`${environment.apiUrl}/user/register`, user);
  }

  logout(user: Observable<User>) {
    return this.http.put(`${environment.apiUrl}/user/logout`, user);
  }

  registerOrganization(name: string, type: string) {
    let token = window.sessionStorage.getItem('auth-user');
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
    let token = window.sessionStorage.getItem('auth-user');
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

    let token = window.sessionStorage.getItem('auth-user');
    let uuid = window.sessionStorage.getItem('uuid');
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
     return this.http.put(`${environment.apiUrl}/organization/generate/inviteToken/${uuid}`,{},
       httpOptions);

  }

  generateCodeModerator() {

    let token = window.sessionStorage.getItem('auth-user');
    let uuid = window.sessionStorage.getItem('uuid');
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
}
