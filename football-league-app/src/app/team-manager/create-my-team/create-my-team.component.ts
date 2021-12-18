import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/model/user';
import { NotificationType } from 'src/app/enum/notification-type-enum';
import { HttpErrorResponse } from '@angular/common/http';
import { NotificationService } from 'src/app/service/notification.service';
import { TeamService } from 'src/app/service/team.service';
import { Team } from 'src/app/model/team';


@Component({
  selector: 'app-create-my-team',
  templateUrl: './create-my-team.component.html',
  styleUrls: ['./create-my-team.component.css']
})
export class CreateMyTeamComponent implements OnInit {
  public fileName: string;
  public clubLogo: File;
  public user: User;

  constructor(private teamService: TeamService, private notificationService: NotificationService, private userService: UserService) { }

  ngOnInit(): void {
    this.user = this.userService.getCurrentUserFromLocalCache();
  }

  public onProfileImageChange(fileName: string, clubLogo: File): void {
    this.fileName = fileName;
    this.clubLogo = clubLogo;
  }

  public saveNewTeam(teamForm: NgForm): void {
    const formData = this.teamService.createTeamFormData(this.user.userId, teamForm.value.teamName, this.clubLogo);
    console.log(teamForm.value.teamName);
    this.teamService.addTeam(formData).subscribe(
      (response: Team) => {
        this.fileName = null;
        this.clubLogo = null;
        teamForm.reset();
        this.sendNotification(NotificationType.SUCCESS, `Team created succesfully`);
      },
      (errorResponse: HttpErrorResponse) => {
        this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
        this.clubLogo = null;
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
