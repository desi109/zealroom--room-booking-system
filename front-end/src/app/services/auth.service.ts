import { Injectable } from '@angular/core';
import {BehaviorSubject, map, Observable} from "rxjs";
import {User} from "../models/user";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";

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

  login(username: string, password: string) {
    return this.http
      .post<any>(`${environment.apiUrl}/user/login`, { username, password })
      .pipe(
        map(({token}) => {
          let user: { password: string; email: string } = {
            email: this.email,
            password: this.password,
          };
          localStorage.setItem('currentUser', JSON.stringify(user));
          if (user instanceof User) {
            this.userSubject.next(user);
          }
          return user;
        })
      );
  }

  // logout() {
  //   localStorage.removeItem('currentUser');
  //   this.userSubject.next(null);
  // }
}
