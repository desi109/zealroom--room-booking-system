import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {create} from "domain";
import {identifierModuleUrl} from "@angular/compiler";
import {UserService} from "../services/user.service";
import {AuthService} from "../services/auth.service";


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
  authService: AuthService;

  constructor(
    public dialogRef: MatDialogRef<CreateSpaceComponent>,
    private formBuilder: FormBuilder,
    private userService: UserService,
  @Inject(MAT_DIALOG_DATA) public data: any) {
  }
  organizationForm: FormGroup;

  ngOnInit() {
    this.organizationForm = this.formBuilder.group({
      name: ['', Validators.required],
      type: ['', Validators.required]
    });
  }

  onFormSubmit() {
    this.userService
      .registerOrganization(this.organizationForm.value.name, this.organizationForm.value.type)
      .subscribe(
        (data)=>{
         // window.sessionStorage.setItem("uuid", data.toString());
        }
      );
    window.alert('Organization Registered successfully!');
    this.dialogRef.close();
  }

  onCancel(): void {
    this.dialogRef.close();
  }

}


