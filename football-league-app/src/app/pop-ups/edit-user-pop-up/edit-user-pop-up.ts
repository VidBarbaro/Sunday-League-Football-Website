import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-pop-up',
  templateUrl: 'edit-user-pop-up.html',
  styleUrls: ['edit-user-pop-up.css']
})
export class PopUpComponent implements OnInit {

  profileImageUrl: string;
  firstName: string;
  lastName: string;
  username: string;
  status: boolean;
  userID: string;
  email: string;
  role: string;
  isLocked: boolean;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.firstName = data.firstName;
    this.lastName = data.lastName;
    this.username = data.username;
    this.status = data.status;
    this.userID = data.userID;
    this.email = data.email;
    this.role = data.role;
    this.isLocked = data.isLocked;
  }

  ngOnInit(): void {
  }

}
