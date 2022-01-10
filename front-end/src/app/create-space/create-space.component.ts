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
  submitted = false;

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

  get createOrganizationFormControl() {
    return this.organizationForm.controls;
  }

  onFormSubmit() {
    this.submitted = true;
    if(this.organizationForm.valid) {
      this.userService
        .registerOrganization(this.organizationForm.value.name, this.organizationForm.value.type)
        .subscribe(
          (data) => {
            window.alert('Organization Registered successfully!');
            window.sessionStorage.setItem("org-uuid", data.toString());
            window.location.reload();
          },
          (err) => {
            window.alert(err.error.toString());
          }
        );
      this.dialogRef.close();
    }
    else {
      window.alert("Please, fill all required fields!");
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }

}


