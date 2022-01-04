import { Component, OnInit } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { AuthService } from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit {

  isLoggedIn = false;
  email?: string;
  firstName?: string;
  lastName?: string;
  loginComponent: LoginComponent;

  userEmail: string = "Email";

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {

    this.isLoggedIn = !!this.authService.getToken();

    if (this.isLoggedIn) {
      const user = this.authService.getUser();

      //this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      //this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');
      this.firstName = user.firstName;
      this.lastName = user.lastName;
      this.email = user.email;

    }
  }

}
