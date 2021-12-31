import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subscription } from 'rxjs';
import { NotificationType } from 'src/app/enum/notification-type-enum';
import { Match } from 'src/app/model/match';
import { Team } from 'src/app/model/team';
import { User } from 'src/app/model/user';
import { NotificationService } from 'src/app/service/notification.service';
import { UserService } from 'src/app/service/user.service'
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatchService } from 'src/app/service/match.service';
import { TeamService } from 'src/app/service/team.service';
import { MatchDTO } from 'src/app/model/matchDTO';


@Component({
  selector: 'app-edit-match-pop-up',
  templateUrl: './edit-match-pop-up.component.html',
  styleUrls: ['./edit-match-pop-up.component.css']
})
export class EditMatchPopUpComponent implements OnInit {
  public match: MatchDTO;
  public teams: Team[];
  public referees: User[];
  public matchId: string;
  public homeTeam: string;
  public awayTeam: string;
  public referee: string;
  private subscriptions: Subscription[] = [];

  public matchDate: Date;
  public location: string;
  public homeTeamGoals: string;
  public awayTeamGoals: string;
  public isFinished: boolean;

  constructor(@Inject(MAT_DIALOG_DATA) public data: {editMatch: Match}, private matchService: MatchService, private userService: UserService, private teamService: TeamService, private notificationService: NotificationService) {
    // this.match = <MatchDTO> <unknown> data.editMatch,
    this.matchId = data.editMatch.id.toString();
    this.homeTeam = data.editMatch.homeTeamId.name,
    this.awayTeam = data.editMatch.awayTeamId.name,
    this.referee = data.editMatch.refereeId.username,
    this.matchDate = data.editMatch.matchDateTime,
    this.location = data.editMatch.location,
    this.homeTeamGoals = data.editMatch.homeTeamGoals.toString(),
    this.awayTeamGoals = data.editMatch.awayTeamGoals.toString(),
    this.isFinished = data.editMatch.isFinished
  }

  ngOnInit(): void {
    this.getAvailableReferees();
    this.getAvailableTeams();
  }

  public onUpdateMatch(): void {
    console.log(Number(this.matchId));
    this.match = new MatchDTO();
    
    this.match.id = Number(this.matchId);
    this.match.homeTeamId = this.homeTeam;
    this.match.awayTeamId = this.awayTeam;
    this.match.refereeId = this.referee;
    this.match.matchDateTime = this.matchDate;
    this.match.location = this.location;
    this.match.homeTeamGoals = this.homeTeamGoals;
    this.match.awayTeamGoals = this.awayTeamGoals;
    this.match.isFinished = this.isFinished.toString();
  
    const formData = this.matchService.createFinishedMatchFormData(this.match);
    this.matchService.updateMatch(formData).subscribe(      
      (response: MatchDTO) => {
        this.sendNotification(NotificationType.SUCCESS, `Match: ${response.id} updated succesfully`);
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


