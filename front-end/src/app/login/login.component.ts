import { Component, OnInit } from '@angular/core';

import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  // constructor(private router: Router) { }
  //
  // username: string | undefined;
  // password: string | undefined;

  ngOnInit() {
  }

  // login() : void {
  //   if(this.username == 'admin' && this.password == 'admin'){
  //     this.router.navigate(["user"]);
  //   }else {
  //     alert("Invalid credentials");
  //   }
  // }
}
