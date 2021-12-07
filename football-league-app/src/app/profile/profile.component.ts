import { HttpErrorResponse, HttpEvent, HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NotificationType } from '../enum/notification-type-enum';
import { FileUploadStatus } from '../model/file-upload-status';
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
  public fileStatus: FileUploadStatus;

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

  public updateProfileImage(): void {
    this.clickButton('profile-image-input');
  }

  private sendNotification(notificationType: NotificationType, message: string): void {
    if (message) {
      this.notificationService.notify(notificationType, message);
    } else {
      this.notificationService.notify(notificationType, 'An error occurred. Please try again.');
    }
  }

  public onUpdateProfileImage(): void {
    const formData = new FormData();
    formData.append('username', this.user.username);
    formData.append('profileImage', this.profileImage);
    this.userService.updateProfileImage(formData).subscribe(      
      (event: HttpEvent<any>) => {
        this.reportUploadProgress(event);
        window.location.reload()
      },
      (errorResponse: HttpErrorResponse) => {
        this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
        this.fileStatus.status = 'done';
      }
    );
  }

  private clickButton(buttonId: string): void {
    document.getElementById(buttonId).click();
    
  }

  private reportUploadProgress(event: HttpEvent<any>): void {
    switch (event.type) {
      case HttpEventType.UploadProgress:
        this.fileStatus.percentage = Math.round(100 * event.loaded / event.total);
        this.fileStatus.status = 'progress';
        break;
      case HttpEventType.Response:
        if (event.status === 200 ) {
          this.user.profileImageUrl = `${event.body.profileImageUrl}?time=${new Date().getTime()}`;
          this.sendNotification(NotificationType.SUCCESS, `${event.body.firstName}\'s profile image updated succesfully`);
          this.fileStatus.status = 'done';
          break;
        }
        else {
          this.sendNotification(NotificationType.ERROR, `Unable to upload image. Pleasetry again`);
          break;
        }
      default:
        `Finished all proccesses`;
    }
  }
}
