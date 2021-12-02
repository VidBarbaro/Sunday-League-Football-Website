import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { HeaderType } from '../enum/header-type.enum';
import { Subscription } from 'rxjs';
import { AuthenticationService } from '../service/authentication.service';
import { NotificationService } from '../service/notification.service';
import { NotificationType } from '../enum/notification-type-enum';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit, OnDestroy {
  public showLoading: boolean;
  private subscriptions: Subscription[] = [];
  email = new FormControl('', [Validators.required, Validators.email]);

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

 constructor(private router: Router, private authenticationService: AuthenticationService,
             private notificationService: NotificationService) { }


 ngOnInit(): void {
   if (this.authenticationService.isUserLoggedIn()) {
     this.router.navigateByUrl('/home');    ///user/management
   } else {
     this.router.navigateByUrl('/login');
   }

 }

 public onLogin(user: User): void {
   console.log(user);
   this.showLoading = true;
   this.subscriptions.push(
     this.authenticationService.login(user).subscribe( // we get user from the back-end
       (response: HttpResponse<User>) => {
         const token = response.headers.get(HeaderType.JWT_TOKEN) || '{}'; // get the token from the headers
         this.authenticationService.saveToken(token); // save the token in the local storage
         this.authenticationService.addUserToLocalCache(response.body!); // add the user to the local storage
         this.router.navigateByUrl('/home'); // navigate the user back to the home page
         this.showLoading = false; // stop showing loading on button
       },
       (errorResponse: HttpErrorResponse) => {
         this.sendErrorNotification(NotificationType.ERROR, errorResponse.error.message);
         this.showLoading = false;
       }
     )
   );
 }

 ngOnDestroy(): void {
   this.subscriptions.forEach(sub => sub.unsubscribe());
 }
 
  private sendErrorNotification(notificationType: NotificationType, message: string): void {
   if (message) {
     this.notificationService.notify(notificationType, message);
   } else {
     this.notificationService.notify(notificationType, 'An error occurred. Please try again.');
   }
 }

}