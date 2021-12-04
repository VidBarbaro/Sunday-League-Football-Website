import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NotificationService } from 'src/app/service/notification.service';
import { UserService } from 'src/app/service/user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { NotificationType } from 'src/app/enum/notification-type-enum';
import { User } from 'src/app/model/user';
import { Role } from 'src/app/enum/role.enum';

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
  role: Role;
  isLocked: boolean;
  public currentUsername: string;
  public editUser: User;


  constructor(@Inject(MAT_DIALOG_DATA) public data: {editUser: User}, private userService: UserService, private notificationService: NotificationService) {
    this.editUser = data.editUser,
    this.firstName = data.editUser.firstName,
    this.lastName = data.editUser.lastName,
    this.username = data.editUser.username,
    this.status = data.editUser.active,
    this.email = data.editUser.email,
    this.role = data.editUser.role,
    this.isLocked = data.editUser.notLocked,
    this.profileImageUrl = data.editUser.profileImageUrl,
    this.currentUsername = data.editUser.username
  }

  public fileName: string;
  public profileImage: File;
  // public editUser = new User();

  ngOnInit(): void {
    console.log(this.editUser);
    
  }

  ngAfterInit() {
    
  }

  public onProfileImageChange(fileName: string, profileImage: File): void {
    this.fileName = fileName;
    this.profileImage = profileImage;
  }

  public onUpdateUser(): void {
    this.editUser.firstName = this.firstName;
    this.editUser.lastName = this.lastName;
    this.editUser.username = this.username;
    this.editUser.email = this.email;
    this.editUser.role = this.role;
    this.editUser.profileImageUrl = this.profileImageUrl;
    this.editUser.active = this.status;
    this.editUser.notLocked = this.isLocked;
  
    const formData = this.userService.createUserFormData(this.currentUsername, this.editUser, this.profileImage);
    this.userService.updateUser(formData).subscribe(      
      (response: User) => {
        this.fileName = null;
        this.profileImage = null;
        this.sendNotification(NotificationType.SUCCESS, `${response.firstName} ${response.lastName} updated succesfully`);
      },
      (errorResponse: HttpErrorResponse) => {
        this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
        this.profileImage = null;
      }
    )
  }

  private sendNotification(notificationType: NotificationType, message: string): void {
    if (message) {
      this.notificationService.notify(notificationType, message);
    } else {
      this.notificationService.notify(notificationType, 'An error occurred. Please try again.');
    }
  }


}
