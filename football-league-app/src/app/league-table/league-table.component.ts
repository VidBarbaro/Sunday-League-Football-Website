import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { NotificationType } from '../enum/notification-type-enum';
import { TeamTablePosition } from '../model/teamTablePosition';
import { MatchService } from '../service/match.service';
import { NotificationService } from '../service/notification.service';

@Component({
  selector: 'app-league-table',
  templateUrl: './league-table.component.html',
  styleUrls: ['./league-table.component.css']
})
export class LeagueTableComponent implements OnInit {
  subscriptions: Subscription[] = []
  leagueTable: TeamTablePosition[] = [];

  constructor(private matchService: MatchService, private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.getLeagueTable();
  }

  public getLeagueTable(): void {
    this.subscriptions.push(
      this.matchService.getLeagueTable().subscribe(
        (response: TeamTablePosition[]) => {
          this.leagueTable = response;
          if (this.sendNotification) {
            this.sendNotification(NotificationType.SUCCESS, `Table loaded successfully.`);
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
