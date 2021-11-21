import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class HomeCompanyService {

  constructor(private http:HttpClient) { }

  getHomePage(): Observable<string> {
    return this.http.get('/home', { responseType: 'text' });
  }
}
