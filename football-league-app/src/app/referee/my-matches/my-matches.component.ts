import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { NotificationType } from 'src/app/enum/notification-type-enum';
import { Match } from 'src/app/model/match';
import { EditMatchPopUpComponent } from 'src/app/pop-ups/edit-match-pop-up/edit-match-pop-up.component';
import { InputMatchResultPopUpComponent } from 'src/app/pop-ups/input-match-result-pop-up/input-match-result-pop-up.component';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { MatchService } from 'src/app/service/match.service';
import { NotificationService } from 'src/app/service/notification.service';

@Component({
  selector: 'app-my-matches',
  templateUrl: './my-matches.component.html',
  styleUrls: ['./my-matches.component.css']
})
export class MyMatchesComponent implements OnInit {
  public subscriptions: Subscription[] = [];
  public matches: Match[] = null;

  constructor(private matchService: MatchService, private notificationService: NotificationService, private dialogRef: MatDialog, private authService: AuthenticationService) { }

  ngOnInit(): void {
    this.getRefereeMatches();
  }

  public getRefereeMatches(): void {
    this.subscriptions.push(
      this.matchService.getMatchesForReferee(this.authService.getUserFromLocalCache().id).subscribe(
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

  public openInputMatchResult(match: Match) {
    this.dialogRef.open(InputMatchResultPopUpComponent,{
      //data
      width: '30%',
      data: {
        editMatch: match
      }
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
