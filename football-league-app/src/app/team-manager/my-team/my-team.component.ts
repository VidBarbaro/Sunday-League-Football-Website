import { HttpErrorResponse } from '@angular/common/http';
import { AfterContentInit, AfterViewInit, Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { NotificationType } from 'src/app/enum/notification-type-enum';
import { CustomHttpResponse } from 'src/app/model/custom-http-response';
import { Team } from 'src/app/model/team';
import { User } from 'src/app/model/user';
import { AddPlayerPopUpComponent } from 'src/app/pop-ups/add-player-pop-up/add-player-pop-up.component';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { NotificationService } from 'src/app/service/notification.service';
import { TeamService } from 'src/app/service/team.service';
import { UserService } from 'src/app/service/user.service';

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

  constructor(private teamService: TeamService, private notificationService: NotificationService, private authenticationService: AuthenticationService, private userService: UserService, private dialogRef: MatDialog) { }

  ngOnInit(): void {
    this.user = this.authenticationService.getUserFromLocalCache();
    this.getTeamForManager(this.user.userId);    
  }

  public getPlayersForTeam(teamId: number) {
    this.subscriptions.push(
      this.teamService.getPlayersByTeamId(teamId).subscribe(
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
          this.getPlayersForTeam(this.team.id);
          if (this.sendNotification) {
            this.sendNotification(NotificationType.SUCCESS, `${response.name} loaded successfully.`);
          }
        }
      )
    );
  }

  public searchUsers(searchTerm: string): void {
    const results: User[] = [];
    for (const user of this.userService.getPlayersFromLocalCache()) {
      if (user.firstName.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1 ||
          user.lastName.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1 ||
          user.username.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1 ||
          user.userId.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1) {
            results.push(user);
      }
    }
    console.log(results);
    this.players = results;
    if (results.length == 0 || !searchTerm) {
      this.players = this.userService.getPlayersFromLocalCache();
    }
  }

  public onRemovePlayer(playerId: number): void {
    this.subscriptions.push(
      this.teamService.removePlayerFromTeam(playerId).subscribe(
        (response: CustomHttpResponse) => {
          this.sendNotification(NotificationType.SUCCESS, response.message);
          this.getTeamForManager(this.user.userId); 
        },
        (errorResponse: HttpErrorResponse) => {
          this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
        }
      )
    );
  }

  public openAddPlayer() {
    this.dialogRef.open(AddPlayerPopUpComponent,{
      width: '30%'
    });
  }

  private sendNotification(notificationType: NotificationType, message: string): void {
    if (message) {
      this.notificationService.notify(notificationType, message);
    } else {
      this.notificationService.notify(notificationType, 'An error occurred. Please try again.');
    }
  }

}
