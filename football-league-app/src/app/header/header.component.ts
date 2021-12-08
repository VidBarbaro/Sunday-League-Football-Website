import { Component, OnChanges, SimpleChange, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { NotificationType } from '../enum/notification-type-enum';
import { Role } from '../enum/role.enum';
import { AuthenticationService } from '../service/authentication.service';
import { NotificationService } from '../service/notification.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  loggedIn: boolean = false;

  constructor(
    private router: Router,
    private userService: UserService,
    private authenticationService: AuthenticationService,
    private notificationService: NotificationService
    ) {}

  showMenu: boolean = true;
  currentUserRole: string = null;

  toggle() {
    if(this.showMenu){this.showMenu = false }
    else{this.showMenu = true}
  }

  ngOnInit() {    
    console.log(this.currentUserRole);    
  }

  // public isLoggedIn(): void {
  //   if (this.authenticationService.isUserLoggedIn()) {
  //     this.isUserLoggedIn = true;
  //   }
  //   this.isUserLoggedIn = false;
  // }

  public onLogOut() {
    console.log(this.authenticationService.getToken());
    
    if (this.authenticationService.getToken()) {
      this.authenticationService.logOut();
      this.router.navigate(['/login']);
      this.sendNotification(NotificationType.SUCCESS, 'You have been logged out successfully');
    }
  }

  public get isAdmin(): boolean {
    if (this.getUserRole() != null) {
      return this.getUserRole() === Role.ADMIN;
    }
    return false;
  }

  public get isTeamManager(): boolean {
    if (this.getUserRole() != null) {
      return this.getUserRole() === Role.TEAM_MANAGER;
    }
    return false;
  }

  public get isReferee(): boolean {
    if (this.getUserRole() != null) {
      return this.getUserRole() === Role.REFEREE;
    }
    return false;
  }

  public get isPlayer(): boolean {
    if (this.getUserRole() != null) {
      return this.getUserRole() === Role.PLAYER
    }
    return false;
  }

  public get isNone(): boolean {
    if (this.getUserRole() != null) {
      return false;
    }
    return true;
  }

  public isUserLoggedIn(): boolean {
    if (this.authenticationService.isUserLoggedIn()) {
      this.loggedIn = true;
    }
    return false;
  }

  private getUserRole(): string {
    if (this.authenticationService.getUserFromLocalCache() != null) {
      return this.authenticationService.getUserFromLocalCache().role;
    }
    return null;
  }

  private sendNotification(notificationType: NotificationType, message: string): void {
    if (message) {
      this.notificationService.notify(notificationType, message);
    } else {
      this.notificationService.notify(notificationType, 'An error occurred. Please try again.');
    }
  }
}
