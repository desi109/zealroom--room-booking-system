import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {UserService} from "../services/user.service";

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
    @Inject(MAT_DIALOG_DATA) public data: any,
    private formBuilder: FormBuilder,
    private userService: UserService) { }

    joinForm: FormGroup;


  onCancel(): void {
    this.dialogRef.close();
  }

  ngOnInit() {
    this.joinForm = this.formBuilder.group({
      code: ['', Validators.required]
    });
  }

  onFormSubmit(){
    if(this.joinForm.valid) {
      this.userService
        .joinWithCode(this.joinForm.value.code)
        .subscribe(
          (data) => {
            this.userService.getOrganizations().
              subscribe( ()=> {
              window.alert(data.toString());
              window.location.reload();
            })
          },
          (err) => {
            window.alert(err.error.toString());
          }
        );
      this.dialogRef.close();
    }
    else {
      window.alert("Please, enter an invite code.")
    }


  }

}
