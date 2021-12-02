import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { Subscription } from 'rxjs';
import { AuthenticationService } from '../service/authentication.service';
import { NotificationService } from '../service/notification.service';
import { NotificationType } from '../enum/notification-type-enum';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit, OnDestroy {
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
    this.router.navigateByUrl('/home');
  }
 }

 public onRegister(user: User): void {
   console.log(user);
   this.showLoading = true;
   this.subscriptions.push(
     this.authenticationService.register(user).subscribe(
       (response: User) => {
         this.showLoading = false;
         this.sendNotification(NotificationType.SUCCESS, `A new account was created for ${response.firstName}. Please check your email for password to login`);
       },
       (errorResponse: HttpErrorResponse) => {
         this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
         this.showLoading = false;
       }
     )
   );
 }

 ngOnDestroy(): void {
   this.subscriptions.forEach(sub => sub.unsubscribe());
 }
 
  private sendNotification(notificationType: NotificationType, message: string): void {
   if (message) {
     this.notificationService.notify(notificationType, message);
   } else {
     this.notificationService.notify(notificationType, 'An error occurred. Please try again.');
   }
 }

}