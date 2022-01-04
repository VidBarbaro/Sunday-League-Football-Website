import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { NotificationType } from 'src/app/enum/notification-type-enum';
import { Match } from 'src/app/model/match';
import { MatchService } from 'src/app/service/match.service';
import { NotificationService } from 'src/app/service/notification.service';

@Component({
  selector: 'app-finished-matches',
  templateUrl: './finished-matches.component.html',
  styleUrls: ['./finished-matches.component.css']
})
export class FinishedMatchesComponent implements OnInit {
  public subscriptions: Subscription[] = [];
  public matches: Match[] = null;

  constructor(private matchService: MatchService, private notificationService: NotificationService) { }

  ngOnInit(): void {
    this.getRefereeMatches();
  }

  public getRefereeMatches(): void {
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

  private sendNotification(notificationType: NotificationType, message: string): void {
    if (message) {
      this.notificationService.notify(notificationType, message);
    } else {
      this.notificationService.notify(notificationType, 'An error occurred. Please try again.');
    }
  }

}

