import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { NotificationType } from '../enum/notification-type-enum';
import { User } from '../model/user';
import { NotificationService } from '../service/notification.service';
import { UserService } from '../service/user.service';
import { MatDialog } from '@angular/material/dialog';
import { PopUpComponent } from '../pop-ups/edit-user-pop-up/edit-user-pop-up';
import { NewUserPopUpComponent } from '../pop-ups/new-user-pop-up/new-user-pop-up.component';
import { CustomHttpResponse } from '../model/custom-http-response';

interface Users {
  profileImageUrl: string;
  userId: string;
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  status: string;
}

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})

export class AdminComponent implements OnInit, OnDestroy {

  dataSource: MatTableDataSource<User>;
  // users1: Users[];
  public users: User[] = null;
  public refreshing: boolean;
  public selectedUser: User;
  private subscriptions: Subscription[] = [];

  constructor(private userService: UserService, private notificationService: NotificationService, private dialogRef: MatDialog) {
    // this.users1 = [{
    //   profileImageUrl: 'link',
    //   userId: '123',
    //   firstName: "Vid",
    //   lastName: "Barbaro",
    //   username: "vbarbaro",
    //   email: "vid@gmail.com",
    //   status: "active"
    // },
    // {
    //   profileImageUrl: 'link',
    //   userId: '321',
    //   firstName: "Ana",
    //   lastName: "Nitu",
    //   username: "anitu",
    //   email: "ana@gmail.com",
    //   status: "inactive"
    // }];
  }

  ngOnInit() {
    // console.log(this.userService.getUsers());
    this.getUsers();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  public refreshPage() {
    window.location.reload()
  }

  public getUsers(): void {
    this.refreshing = true;
    this.subscriptions.push(
      this.userService.getUsers().subscribe(
        (response: User[]) => {
          this.userService.addUsersToLocalCache(response);
          this.users = response;
          this.refreshing = false;
          if (this.sendNotification) {
            this.sendNotification(NotificationType.SUCCESS, `${response.length} user(s) loaded successfully.`);
          }
        },
        (errorResponse: HttpErrorResponse) => {
          this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
          this.refreshing = false;
        }
      )
    );
  }

  public openEditUser(user: User) {
    this.dialogRef.open(PopUpComponent,{
      //data
      width: '30%',
      data: {
        editUser: user
      }
    });
  }

  public openNewUser() {
    this.dialogRef.open(NewUserPopUpComponent,{
      width: '30%'
    });
  }

  // public onSelectUser(selectedUser: User): void {
  //   this.selectedUser = selectedUser;
  //   document.getElementById('openUserInfo').click();
  //   this.selectedUser = null;
  // }

  public searchUsers(searchTerm: string): void {
    const results: User[] = [];
    for (const user of this.userService.getUsersFromLocalCache()) {
      if (user.firstName.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1 ||
          user.lastName.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1 ||
          user.username.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1 ||
          user.userId.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1) {
            results.push(user);
      }
    }
    this.users = results;
    if (results.length == 0 || !searchTerm) {
      this.users = this.userService.getUsersFromLocalCache();
    }
  }

  public onDeleteUser(username: string): void {
    this.subscriptions.push(
      this.userService.deleteUser(username).subscribe(
        (response: CustomHttpResponse) => {
          this.sendNotification(NotificationType.SUCCESS, response.message);
          this.getUsers();
        },
        (errorResponse: HttpErrorResponse) => {
          this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
        }
      )
    );
  }

  private sendNotification(notificationType: NotificationType, message: string): void {
    if (message) {
      this.notificationService.notify(notificationType, message);
    } else {
      this.notificationService.notify(notificationType, 'An error occurred. Please try again.');
    }
  }


}
