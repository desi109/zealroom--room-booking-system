import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'front-end';

  public response: Observable<object> | undefined;

  constructor(private http: HttpClient) {}


  ngOnInit(): void {
    this.response = this.http.get('/');
  }
}
