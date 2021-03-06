import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
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
  selector: 'app-input-match-result-pop-up',
  templateUrl: './input-match-result-pop-up.component.html',
  styleUrls: ['./input-match-result-pop-up.component.css']
})
export class InputMatchResultPopUpComponent implements OnInit, OnDestroy {
  public match: Match;
  public matchDTO: MatchDTO;
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
    this.match = data.editMatch;
    this.matchId = data.editMatch.id.toString();
    this.homeTeam = data.editMatch.homeTeamId.name,
    this.awayTeam = data.editMatch.awayTeamId.name,
    this.referee = data.editMatch.refereeId.username,
    this.matchDate = data.editMatch.matchDateTime,
    this.location = data.editMatch.location,
    this.homeTeamGoals = data.editMatch.homeTeamGoals.toString(),
    this.awayTeamGoals = data.editMatch.awayTeamGoals.toString(),
    this.isFinished = true;
  }

  ngOnInit(): void {
  }

  public onUpdateMatch(): void {
    console.log(Number(this.matchId));
    this.matchDTO = new MatchDTO();
    
    this.matchDTO.id = Number(this.matchId);
    this.matchDTO.homeTeamId = this.homeTeam;
    this.matchDTO.awayTeamId = this.awayTeam;
    this.matchDTO.refereeId = this.referee;
    this.matchDTO.matchDateTime = this.matchDate;
    this.matchDTO.location = this.location;
    this.matchDTO.homeTeamGoals = this.homeTeamGoals;
    this.matchDTO.awayTeamGoals = this.awayTeamGoals;
    this.matchDTO.isFinished = this.isFinished.toString();
  
    const formData = this.matchService.createFinishedMatchFormData(this.matchDTO);
    this.matchService.updateMatch(formData).subscribe(      
      (response: MatchDTO) => {
        this.sendNotification(NotificationType.SUCCESS, `Match: ${response.id} updated succesfully`);
      },
      (errorResponse: HttpErrorResponse) => {
        this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
      }
    )
  }

  public addPointsFromMatchResult(match: Match) {
    this.matchService.addPointsFromMatchResult(match.id).subscribe(      
      (response: Match) => {
      },
      (errorResponse: HttpErrorResponse) => {
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

  ngOnDestroy(): void {
      this.addPointsFromMatchResult(this.match);
  }


}

