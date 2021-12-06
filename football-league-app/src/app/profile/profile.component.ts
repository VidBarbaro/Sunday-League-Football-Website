import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NotificationType } from '../enum/notification-type-enum';
import { User } from '../model/user';
import { AuthenticationService } from '../service/authentication.service';
import { NotificationService } from '../service/notification.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  public user: User;
  public currentUsername: string;
  public fileName: string;
  public profileImage: File;

  constructor(private authenticationService: AuthenticationService, private userService: UserService, private notificationService: NotificationService) { }

  ngOnInit(): void {
    this.user = this.authenticationService.getUserFromLocalCache();
  }

  public onProfileImageChange(fileName: string, profileImage: File): void {
    this.fileName = fileName;
    this.profileImage = profileImage;
  }

  public onUpdateCurrentUser(user: User): void {
    this.currentUsername = this.authenticationService.getUserFromLocalCache().username;

    const formData = this.userService.createUserFormData(this.currentUsername, user, this.profileImage);
    this.userService.updateUser(formData).subscribe(      
      (response: User) => {
        this.authenticationService.addUserToLocalCache(response);
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
