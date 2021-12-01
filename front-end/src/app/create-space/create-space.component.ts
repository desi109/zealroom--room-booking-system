import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";


interface OrganizationTypes {
  value: string;
  viewValue: string;
}


@Component({
  selector: 'app-create-space',
  templateUrl: './create-space.component.html',
  styleUrls: ['./create-space.component.css']
})
export class CreateSpaceComponent implements OnInit {

  selectedValue: string;

  types: OrganizationTypes[] = [
    {value: 'education', viewValue: 'Educational institution'},
    {value: 'business', viewValue: 'Business company'},
    {value: 'medicine', viewValue: 'Medicine'},
    {value: 'personal', viewValue: 'Personal use'},
    {value: 'other', viewValue: 'Other'}
  ];

  form: FormGroup;
  description:string;

  constructor(
    public dialogRef: MatDialogRef<CreateSpaceComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  onCancel(): void {
    this.dialogRef.close();
  }

  ngOnInit() {
  }
}


