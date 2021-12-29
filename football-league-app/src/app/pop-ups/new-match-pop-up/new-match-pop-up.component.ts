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
  public homeTeam: Team;
  public awayTeam: Team;
  public referee: User;
  private subscriptions: Subscription[] = [];

  public homeTeamName: string;
  public awayTeamName: string;
  public refereeUsername: string;
  public matchDate: Date;
  public location: string;

  constructor(private matchService: MatchService, private notificationService: NotificationService, private userService: UserService, private teamService: TeamService) {
  }

  ngOnInit(): void {
    this.getAvailableReferees();
    this.getAvailableTeams();
  }

  public saveNewMatch(matchForm: NgForm): void {
    console.log(matchForm.value);

    const formData = this.matchService.createMatchFormData(matchForm.value);
    
    this.matchService.createNewMatch(formData).subscribe(
      (response: Match) => {
        matchForm.reset();
        this.sendNotification(NotificationType.SUCCESS, `Match: ${response.homeTeamId} vs ${response.awayTeamId} added succesfully`);
      },
      (errorResponse: HttpErrorResponse) => {
        this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
      }
    )
  }

  // public saveNewMatch(match: Match): void {
  //   this.matchService.createNewMatch(match).subscribe(
  //     (response: Match) => {
        
  //       this.sendNotification(NotificationType.SUCCESS, `Match: ${response.homeTeamId} vs ${response.awayTeamId} added succesfully`);
  //     },
  //     (errorResponse: HttpErrorResponse) => {
  //       this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
  //     }
  //   )
  // }

  // public createNewMatch() {
  //   if(this.homeTeamName != null || this.awayTeamName != null || this.refereeUsername != null || this.matchDate != null || this.location != null) {
  //     this.subscriptions.push(
  //       this.userService.getUserByUsername(this.refereeUsername).subscribe(
  //         (response: User) => {
  //           this.referee = response;
  //         }
  //       ),
  //       this.teamService.getTeamByTeamName(this.homeTeamName).subscribe(
  //         (response: Team) => {
  //           this.homeTeam = response;
  //         }
  //       ),
  //       this.teamService.getTeamByTeamName(this.awayTeamName).subscribe(
  //         (response: Team) => {
  //           this.awayTeam = response;
  //         }
  //       ),
  //     );
  //     console.log("Home team" + this.homeTeam);
  //     var datestr = (new Date(this.matchDate)).toISOString();
  //     this.match = new Match(this.homeTeam, this.awayTeam, this.referee, datestr, this.location);
  //     this.saveNewMatch(this.match);
  //   }
  //   this.sendNotification(NotificationType.ERROR, "Fill in all the fields");
  // }

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

