import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import {BehaviorSubject, map, Observable} from "rxjs";
import {User} from "../models/user";
import {environment} from "../../environments/environment";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})

export class UserService {
  private userSubject: BehaviorSubject<User>;
  public user: Observable<User>;
  private loginUrl = "http://localhost:5000/user/login";
  constructor( private router: Router,
               private http: HttpClient) {
    this.userSubject = new BehaviorSubject<User>(JSON.parse(<string>localStorage.getItem('user')));
    this.user = this.userSubject.asObservable();
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('${}');
  }

  login(username: any, password: any) {
    return this.http.post<User>(`${environment.apiUrl}/user/login`, { username, password })
      .pipe(map(user => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('user', JSON.stringify(user));
        this.userSubject.next(user);
        return user;
      }));
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('user');
    this.router.navigate(['/user/logout']);
  }
}
