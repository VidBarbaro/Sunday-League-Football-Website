import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { NotificationType } from 'src/app/enum/notification-type-enum';
import { Match } from 'src/app/model/match';
import { MatchService } from 'src/app/service/match.service';
import { NotificationService } from 'src/app/service/notification.service';
import { Role } from '../enum/role.enum';
import { Team } from '../model/team';
import { User } from '../model/user';
import { AuthenticationService } from '../service/authentication.service';
import { TeamService } from '../service/team.service';

@Component({
  selector: 'app-fixtures',
  templateUrl: './fixtures.component.html',
  styleUrls: ['./fixtures.component.css']
})
export class FixturesComponent implements OnInit {
  public subscriptions: Subscription[] = [];
  public matches: Match[] = null;
  public allMatchesDisplay: boolean = true;
  public userPlayer: boolean = false;
  public user: User;
  public team: Team;

  constructor(private matchService: MatchService, private notificationService: NotificationService, private authenticationService: AuthenticationService, private teamService: TeamService) { }

  ngOnInit(): void {
    this.getMatches();
    this.user = this.authenticationService.getUserFromLocalCache();
    if(this.user.role == Role.PLAYER) {
      console.log(this.user.role);
      
      this.userPlayer = true;
      this.getTeamForPlayer(this.user);
    }
  }

  public getMatches(): void {
    this.allMatchesDisplay = false;
    this.subscriptions.push(
      this.matchService.getMatches().subscribe(
        (response: Match[]) => {
          this.matchService.addMatchesToLocalCache(response);
          this.matches = response;
          if (this.sendNotification) {
            this.sendNotification(NotificationType.SUCCESS, `${response.length} match(es) loaded successfully.`);
          }
        },
        (errorResponse: HttpErrorResponse) => {
          this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
        }
      )
    );
  }

  public getMatchesForTeam(teamId: number): void {
    this.allMatchesDisplay = true;
    this.subscriptions.push(
      this.matchService.getMatchesForTeam(teamId).subscribe(
        (response: Match[]) => {
          this.matchService.addMatchesToLocalCache(response);
          this.matches = response;
          if (this.sendNotification) {
            this.sendNotification(NotificationType.SUCCESS, `Your ${response.length} match(es) loaded successfully.`);
          }
        },
        (errorResponse: HttpErrorResponse) => {
          this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
        }
      )
    );
  }


  public getTeamForPlayer(user: User): void {
    this.subscriptions.push(
      this.teamService.getTeamByPlayerId(user.id).subscribe(
        (response: Team) => {
          this.teamService.addTeamToLocalCache(response);
          this.team = response;
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
