import {Component, Inject, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-join-w-code',
  templateUrl: './join-w-code.component.html',
  styleUrls: ['./join-w-code.component.css']
})
export class JoinWCodeComponent implements OnInit {

  form: FormGroup;
  description:string;

  constructor(
    public dialogRef: MatDialogRef<JoinWCodeComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  onCancel(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
  }

}
