import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-generate-code',
  templateUrl: './generate-code.component.html',
  styleUrls: ['./generate-code.component.css']
})
export class GenerateCodeComponent implements OnInit {
  toDisplay = false;

  toggleData() {
    this.toDisplay = !this.toDisplay;
  }
  constructor() { }

  ngOnInit(): void {
  }



}
