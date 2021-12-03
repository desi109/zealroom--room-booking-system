import { Component, OnInit } from '@angular/core';
import {CreateSpaceComponent} from "../create-space/create-space.component";
import {MatDialog} from "@angular/material/dialog";
import {GenerateCodeComponent} from "../generate-code/generate-code.component";
import {ViewportScroller} from "@angular/common";

@Component({
  selector: 'app-moderator',
  templateUrl: './moderator.component.html',
  styleUrls: ['./moderator.component.css']
})
export class ModeratorComponent implements OnInit {

  constructor(public dialog: MatDialog, private scroller: ViewportScroller) { }

  ngOnInit(): void {
  }

  openDialogCode(): void {
    let dialogRef = this.dialog.open(GenerateCodeComponent, {
      width: '600px',
      data: {  }
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  goDown1() {
    this.scroller.scrollToAnchor("targetRooms");
  }

  goDown2() {
    this.scroller.scrollToAnchor("targetUsers");
  }

  goDown3() {
    this.scroller.scrollToAnchor("targetReservations");
  }

}
