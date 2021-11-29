import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent implements OnInit {

  constructor() { }

  title = 'my-app';
  OpenState: boolean = false;

  CollapsedHeight: string = '50px';
  ExpandedHeight: string = '50px';

  ngOnInit(): void {
  }

}

