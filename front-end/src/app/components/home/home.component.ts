import { Component, OnInit } from '@angular/core';
import {HomeCompanyService} from "../../services/home.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  homePageData = { };

  constructor(public homeCompanyService: HomeCompanyService) { }

  ngOnInit(): void {
    this.getHomePage();
  }

  getHomePage() {
    this.homeCompanyService.getHomePage().subscribe(
      data => {
        this.homePageData = data;
      },
      err => console.error(err),
      () => console.log('Home Page Data loaded!')
    );
  }
}
