import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-generate-code',
  templateUrl: './generate-code.component.html',
  styleUrls: ['./generate-code.component.css']
})
export class GenerateCodeComponent implements OnInit {
  toDisplay = false;
  toDisplayModerator = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService) { }
  generateCodeForm: FormGroup;
  userCode: string;
  moderatorCode: string;

  ngOnInit(): void {
    if(window.sessionStorage.getItem("org-uuid")==null) {
      this.userService.getOrganizations();
    }

  }
  toggleData() {
    this.userService
      .generateCode().subscribe(
      (data)=>{
        this.userCode=data.toString();
      }
    );

    this.toDisplay = !this.toDisplay;
  }

  toggleDataModerator() {
    this.userService
      .generateCodeModerator()
      .subscribe(
        (data)=>{
          this.moderatorCode=data.toString();
        }
      );
    this.toDisplayModerator = !this.toDisplayModerator;
  }
}
