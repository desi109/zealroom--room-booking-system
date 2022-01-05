import { Injectable } from '@angular/core';
import {BehaviorSubject, map} from "rxjs";
import {User} from "../models/user";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private userSubject: BehaviorSubject<User>;
  public user: Observable<User>;
  private email: string;
  private password: string;

  constructor(private http: HttpClient, private router: Router) {
    this.userSubject = new BehaviorSubject<User>(
      JSON.parse(<string>localStorage.getItem('currentUser'))
    );
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): User {
    return this.userSubject.value;
  }

  login(email: string, password: string): Observable<any> {
    console.log(email + " " + password);
    return this.http.put(`${environment.apiUrl}/user/login`, {
      email,
      password
    }, httpOptions);
  }

  registration(firstName: string, lastName: string, email: string, password: string) {
    console.log("email: " + email + "   password: " + password);
    return this.http
      .put<any>(`${environment.apiUrl}/user/register`, { firstName, lastName, email, password })
      .subscribe(response => console.log(response.text()));
  }



  logout() {
    localStorage.removeItem('currentUser');
    this.userSubject.next(new User());
  }

  signOut(): void {
    window.sessionStorage.clear();
    window.alert("You signed out of your account")
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }
}
