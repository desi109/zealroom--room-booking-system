import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {BehaviorSubject, first, map, Observable} from "rxjs";
import {AuthService} from "../services/auth.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  public userFirstName: string = '';
  public userLastName: string = '';
  public userSessionToken: string = '';
  public userEmail: string = '';

  form: any = {
    username: null,
    password: null
  };

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthService
  ) {}

  ngOnInit() {
    if (this.authenticationService.getToken()) {
      this.isLoggedIn = true;
      //this.roles = this.authenticationService.getUser().roles;
    }
  }

  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    const { email, password } = this.form;

    this.authenticationService.login(email, password)
      .subscribe({
        next: data => {
          this.authenticationService.saveToken(data.accessToken);

          this.userFirstName = data.firstName;
          this.userLastName = data.lastName;
          this.userSessionToken = data.accessToken;
          this.userEmail = data.email;

          this.authenticationService.saveUser(data);
  
          this.isLoginFailed = false;
          this.isLoggedIn = true;
          this.roles = this.authenticationService.getUser().roles;
          this.reloadPage();
        },
        error: err => {
          this.errorMessage = err.error.message;
          this.isLoginFailed = true;
        }
      });
      this.router.navigate(['/']);
  }

  reloadPage(): void {
    window.location.reload();
  }
}
