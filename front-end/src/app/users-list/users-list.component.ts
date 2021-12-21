import { Component, OnInit } from '@angular/core';
import { User } from './user.module';


@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  users: User[] = [
    { "id": 1, "name": 'Maria Marinova', "email": 'm.marinova@gmail.com',
      "position": 'Front-end developer'
    },
    { "id": 2, "name": 'Petar Petrov', "email": 'p.pertov@abv.bg',
      "position": 'Software architect'
    },
    { "id": 3, "name": 'Georgi Georgiev', "email": 'g.georgiev@gmail.com',
      "position": 'Finance analyst'
    },
    { "id": 4, "name": 'Anna Petrova', "email": 'a.petrova@gmail.com',
      "position": 'Account manager'
    }
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
