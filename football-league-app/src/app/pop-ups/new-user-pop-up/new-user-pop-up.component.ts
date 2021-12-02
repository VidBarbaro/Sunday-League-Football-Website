import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NotificationType } from 'src/app/enum/notification-type-enum';
import { User } from 'src/app/model/user';
import { NotificationService } from 'src/app/service/notification.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-new-user-pop-up',
  templateUrl: './new-user-pop-up.component.html',
  styleUrls: ['./new-user-pop-up.component.css']
})
export class NewUserPopUpComponent implements OnInit {
  public fileName: string;
  public profileImage: File;

  constructor(private userService: UserService, private notificationService: NotificationService) {
  }

  ngOnInit(): void {
  }

  public onProfileImageChange(fileName: string, profileImage: File): void {
    this.fileName = fileName;
    this.profileImage = profileImage;
  }

  public saveNewUser(userForm: NgForm): void {
    const formData = this.userService.createUserFormData(null, userForm.value, this.profileImage);
    this.userService.addUser(formData).subscribe(
      (response: User) => {
        this.fileName = null;
        this.profileImage = null;
        userForm.reset();
        this.sendNotification(NotificationType.SUCCESS, `${response.firstName} ${response.firstName} added succesfully`);
      },
      (errorResponse: HttpErrorResponse) => {
        this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
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
