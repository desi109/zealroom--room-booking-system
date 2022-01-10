import { Component, OnInit } from '@angular/core';
import { User } from './user.module';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {RoomService} from "../services/room.service";


@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  // users: User[] = [
  //   { "id": 1, "name": 'Maria Marinova', "email": 'm.marinova@gmail.com',
  //     "position": 'Front-end developer'
  //   },
  //   { "id": 2, "name": 'Petar Petrov', "email": 'p.pertov@abv.bg',
  //     "position": 'Software architect'
  //   },
  //   { "id": 3, "name": 'Georgi Georgiev', "email": 'g.georgiev@gmail.com',
  //     "position": 'Finance analyst'
  //   },
  //   { "id": 4, "name": 'Anna Petrova', "email": 'a.petrova@gmail.com',
  //     "position": 'Account manager'
  //   }
  // ];

  constructor(private roomService: RoomService, private http: HttpClient) { }
  d: any;

  ngOnInit(): void {
    this.d = [];
    let uuid = window.sessionStorage.getItem('org-uuid');

    this.http.get(`${environment.apiUrl}/user/getOrganizationUsers/${uuid}`).subscribe(
      (data)=>{
        console.log(data);
        this.d.push(data as any);
        console.log(this.d);

      }
    );
  }

  deleteUser() {
    let token = window.sessionStorage.getItem('session-token');
    let userId;
    if (token != null) {
      let arr: Array<string> = JSON.parse(token);
      for(var index in arr)
      {
        if(index=="id"){
          userId=arr[index];
        }
      }
    }
    this.http.delete(`${environment.apiUrl}/user/delete/${this.d[0].id}`).subscribe(
      (data)=>{
        console.log(data);
      }
    );
    alert("User deleted.")
    window.location.reload();
  }
}
