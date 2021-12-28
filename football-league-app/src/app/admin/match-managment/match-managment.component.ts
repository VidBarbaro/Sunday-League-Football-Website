import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { NotificationType } from 'src/app/enum/notification-type-enum';
import { Match } from 'src/app/model/match';
import { NewMatchPopUpComponent } from 'src/app/pop-ups/new-match-pop-up/new-match-pop-up.component';
import { MatchService } from 'src/app/service/match.service';
import { NotificationService } from 'src/app/service/notification.service';

@Component({
  selector: 'app-match-managment',
  templateUrl: './match-managment.component.html',
  styleUrls: ['./match-managment.component.css']
})
export class MatchManagmentComponent implements OnInit, OnDestroy {

  dataSource: MatTableDataSource<Match>;
  // users1: Users[];
  public matches: Match[] = null;
  public refreshing: boolean;
  public selectedMatch: Match;
  private subscriptions: Subscription[] = [];

  constructor(private matchService: MatchService, private notificationService: NotificationService, private dialogRef: MatDialog) {
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
    this.getMatches();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  public refreshPage() {
    window.location.reload()
  }

  public getMatches(): void {
    this.refreshing = true;
    this.subscriptions.push(
      this.matchService.getMatches().subscribe(
        (response: Match[]) => {
          this.matchService.addMatchesToLocalCache(response);
          this.matches = response;
          this.refreshing = false;
          if (this.sendNotification) {
            this.sendNotification(NotificationType.SUCCESS, `${response.length} match(es) loaded successfully.`);
          }
        },
        (errorResponse: HttpErrorResponse) => {
          this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
          this.refreshing = false;
        }
      )
    );
  }

  public openCreateMatch() {
    this.dialogRef.open(NewMatchPopUpComponent,{
      width: '30%'
    });
  }

  // public searchUsers(searchTerm: string): void {
  //   const results: User[] = [];
  //   for (const user of this.userService.getUsersFromLocalCache()) {
  //     if (user.firstName.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1 ||
  //         user.lastName.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1 ||
  //         user.username.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1 ||
  //         user.userId.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1) {
  //           results.push(user);
  //     }
  //   }
  //   this.users = results;
  //   if (results.length == 0 || !searchTerm) {
  //     this.users = this.userService.getUsersFromLocalCache();
  //   }
  // }

  // public onDeleteUser(username: string): void {
  //   this.subscriptions.push(
  //     this.userService.deleteUser(username).subscribe(
  //       (response: CustomHttpResponse) => {
  //         this.sendNotification(NotificationType.SUCCESS, response.message);
  //         this.getUsers();
  //       },
  //       (errorResponse: HttpErrorResponse) => {
  //         this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
  //       }
  //     )
  //   );
  // }

  private sendNotification(notificationType: NotificationType, message: string): void {
    if (message) {
      this.notificationService.notify(notificationType, message);
    } else {
      this.notificationService.notify(notificationType, 'An error occurred. Please try again.');
    }
  }


}

