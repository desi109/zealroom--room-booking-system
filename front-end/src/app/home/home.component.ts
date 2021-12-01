import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {CreateSpaceComponent} from "../create-space/create-space.component";
import {JoinWCodeComponent} from "../join-w-code/join-w-code.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  animal: string;
  name: string;

  constructor(public dialog: MatDialog) {}

  ngOnInit(): void {
    }

  openDialogCreate(): void {
    let dialogRef = this.dialog.open(CreateSpaceComponent, {
      width: '600px',
      data: {  }
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  openDialogJoin(): void {
    let dialogRef = this.dialog.open(JoinWCodeComponent, {
      width: '600px',
      data: {  }
    });
  }
}
