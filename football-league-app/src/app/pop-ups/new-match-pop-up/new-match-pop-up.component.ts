import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subscription } from 'rxjs';
import { NotificationType } from 'src/app/enum/notification-type-enum';
import { Match } from 'src/app/model/match';
import { Team } from 'src/app/model/team';
import { User } from 'src/app/model/user';
import { MatchService } from 'src/app/service/match.service';
import { NotificationService } from 'src/app/service/notification.service';
import { TeamService } from 'src/app/service/team.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-new-match-pop-up',
  templateUrl: './new-match-pop-up.component.html',
  styleUrls: ['./new-match-pop-up.component.css']
})
export class NewMatchPopUpComponent implements OnInit {
  public match: Match;
  public teams: Team[];
  public referees: User[];
  example = {
    value: new Date(2022, 11, 28, 14, 57)}
  private subscriptions: Subscription[] = [];

  constructor(private matchService: MatchService, private notificationService: NotificationService, private userService: UserService, private teamService: TeamService) {
  }

  ngOnInit(): void {
    this.getAvailableReferees();
    this.getAvailableTeams();
  }

  public saveNewMatch(matchForm: NgForm): void {
    const formData = this.matchService.createMatchFormData(matchForm.value);
    console.log(this.match.matchDateTime);
    
    this.matchService.createNewMatch(formData).subscribe(
      (response: Match) => {
        matchForm.reset();
        this.sendNotification(NotificationType.SUCCESS, `Match: ${response.homeTeamId.name} vs ${response.awayTeamId.name} added succesfully`);
      },
      (errorResponse: HttpErrorResponse) => {
        this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
      }
    )
  }

  public getAvailableReferees(): void {
    this.subscriptions.push(
      this.userService.getReferees().subscribe(
        (response: User[]) => {
          this.userService.addRefereesToLocalCache(response);
          this.referees = response;
          if (this.sendNotification) {
            this.sendNotification(NotificationType.SUCCESS, `${response.length} referee(s) loaded successfully.`);
          }
        },
        (errorResponse: HttpErrorResponse) => {
          this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
        }
      )
    );
  }

  public getAvailableTeams(): void {
    this.subscriptions.push(
      this.teamService.getTeams().subscribe(
        (response: Team[]) => {
          this.teamService.addTeamsToLocalCache(response);
          this.teams = response;
          if (this.sendNotification) {
            this.sendNotification(NotificationType.SUCCESS, `${response.length} teams(s) loaded successfully.`);
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

