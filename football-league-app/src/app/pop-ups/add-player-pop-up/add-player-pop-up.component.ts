import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subscription } from 'rxjs';
import { NotificationType } from 'src/app/enum/notification-type-enum';
import { Team } from 'src/app/model/team';
import { TeamPlayer } from 'src/app/model/teamPlayer';
import { User } from 'src/app/model/user';
import { NotificationService } from 'src/app/service/notification.service';
import { TeamService } from 'src/app/service/team.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-add-player-pop-up',
  templateUrl: './add-player-pop-up.component.html',
  styleUrls: ['./add-player-pop-up.component.css']
})
export class AddPlayerPopUpComponent implements OnInit {
  public fileName: string;
  public profileImage: File;
  public players: User[];
  public team: Team;
  public user: User;
  public teamPlayer: TeamPlayer = new TeamPlayer();
  private subscriptions: Subscription[] = [];


  constructor(private userService: UserService, private notificationService: NotificationService, private teamService: TeamService) {
  }

  ngOnInit(): void {
    this.team = this.teamService.getTeamFromLocalCache();
    this.getAvailablePlayers();
  }

  public getAvailablePlayers(): void {
    this.subscriptions.push(
      this.userService.getAvailablePlayers().subscribe(
        (response: User[]) => {
          this.userService.addUsersToLocalCache(response);
          this.players = response;
          if (this.sendNotification) {
            this.sendNotification(NotificationType.SUCCESS, `${response.length} user(s) loaded successfully.`);
          }
        },
        (errorResponse: HttpErrorResponse) => {
          this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
        }
      )
    );
  }

  public getUserByUsername(playerUsername: string): void {
    this.subscriptions.push(
      this.userService.getUserByUsername(playerUsername).subscribe(
        (response: User) => {
          this.user = response;
          console.log(this.user);
          this.teamPlayer.playerId = response;
          this.addPlayerToTeam(response);
        },
      )      
    );
  }

  public addPlayerToTeam(player: User): void {    
    this.teamPlayer.teamId = this.team;
    this.teamPlayer.playerId = player;
    
    this.subscriptions.push(
      this.teamService.addPlayerToTeam(this.teamPlayer).subscribe(        
        (response: TeamPlayer) => {
          
          this.sendNotification(NotificationType.SUCCESS, `Player was added to your team successfully`);
        },
        (errorResponse: HttpErrorResponse) => {
          this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
        }
      )
    );
  }


  public saveNewUser(userForm: NgForm): void {
    const formData = this.userService.createUserFormData(null, userForm.value, this.profileImage);
    this.userService.addUser(formData).subscribe(
      (response: User) => {
        this.fileName = null;
        this.profileImage = null;
        userForm.reset();
        this.sendNotification(NotificationType.SUCCESS, `${response.firstName} ${response.firstName} added succesfully`);
      },
      (errorResponse: HttpErrorResponse) => {
        this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
        this.profileImage = null;
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


}
