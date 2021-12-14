import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { NotificationType } from 'src/app/enum/notification-type-enum';
import { Team } from 'src/app/model/team';
import { User } from 'src/app/model/user';
import { NotificationService } from 'src/app/service/notification.service';
import { TeamService } from 'src/app/service/team.service';

@Component({
  selector: 'app-my-team',
  templateUrl: './my-team.component.html',
  styleUrls: ['./my-team.component.css']
})
export class MyTeamComponent implements OnInit {

  private subscriptions: Subscription[] = [];
  public team: Team = null;

  constructor(private teamService: TeamService, private notificationService: NotificationService) { }

  ngOnInit(): void {
  }

  public getTeamForManager(managerId: string) {
    this.subscriptions.push(
      this.teamService.getTeamByManagerId(managerId).subscribe(
        (response: Team) => {
          this.teamService.addTeamToLocalCache(response);
          this.team = response;
          if (this.sendNotification) {
            this.sendNotification(NotificationType.SUCCESS, `${response.name} loaded successfully.`);
          }
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
