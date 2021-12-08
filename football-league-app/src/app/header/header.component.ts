import { Component } from '@angular/core';
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
    this.currentUserRole = this.userService.getUserRole();
    console.log(this.currentUserRole);    
  }

  public onLogOut() {
    if (this.authenticationService.getToken()) {
      this.authenticationService.logOut();
      this.router.navigate(['/login']);
      this.sendNotification(NotificationType.SUCCESS, 'You have been logged out successfully');
    }
  }

  public get isAdmin(): boolean {
    return this.getUserRole() === Role.ADMIN;
  }

  public get isTeamManager(): boolean {
    return this.getUserRole() === Role.TEAM_MANAGER;
  }

  public get isReferee(): boolean {
    return this.getUserRole() === Role.REFEREE;
  }

  public get isPlayer(): boolean {
    return this.getUserRole() === Role.PLAYER
  }

  private getUserRole(): string {
    return this.authenticationService.getUserFromLocalCache().role;
  }

  private sendNotification(notificationType: NotificationType, message: string): void {
    if (message) {
      this.notificationService.notify(notificationType, message);
    } else {
      this.notificationService.notify(notificationType, 'An error occurred. Please try again.');
    }
  }
}
