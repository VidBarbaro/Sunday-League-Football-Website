import { HttpErrorResponse } from '@angular/common/http';
import { AfterContentInit, AfterViewInit, Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { NotificationType } from 'src/app/enum/notification-type-enum';
import { Team } from 'src/app/model/team';
import { User } from 'src/app/model/user';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { NotificationService } from 'src/app/service/notification.service';
import { TeamService } from 'src/app/service/team.service';

@Component({
  selector: 'app-my-team',
  templateUrl: './my-team.component.html',
  styleUrls: ['./my-team.component.css']
})
export class MyTeamComponent implements OnInit {

  private subscriptions: Subscription[] = [];
  public players: User[] = null;
  public user: User;
  public team: Team = null;

  constructor(private teamService: TeamService, private notificationService: NotificationService, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.user = this.authenticationService.getUserFromLocalCache();
    this.getTeamForManager(this.user.userId);    
  }

  public getPlayersForTeam(teamName: string) {
    this.subscriptions.push(
      this.teamService.getPlayersByTeamName(teamName).subscribe(
        (response: User[]) => {
          this.teamService.addPlayersToLocalCache(response);
          this.players = response;
          console.log(this.players);
          
          if (this.sendNotification) {
            this.sendNotification(NotificationType.SUCCESS, `${response.length} players loaded successfully.`);
          }
          },
          (errorResponse: HttpErrorResponse) => {
            this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
          }
        )
    )
  }

  public getTeamForManager(managerId: string) {
    this.subscriptions.push(
      this.teamService.getTeamByManagerId(managerId).subscribe(
        (response: Team) => {
          this.teamService.addTeamToLocalCache(response);
          this.team = response;  
          console.log(this.team);
          this.getPlayersForTeam(this.team.teamId);
          if (this.sendNotification) {
            this.sendNotification(NotificationType.SUCCESS, `${response.name} loaded successfully.`);
          }
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
