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

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService) { }
  generateCodeForm: FormGroup;
  code: string;

  ngOnInit(): void {
  }
  toggleData() {
    this.userService
      .generateCode()
      .subscribe(
        (data)=>{
          this.code=data.toString();
        }
      );
    this.toDisplay = !this.toDisplay;
  }



}
