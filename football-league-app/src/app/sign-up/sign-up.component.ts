import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  constructor(
    private userService: UserService, 
    private userAuthService: UserAuthService,
    private router: Router
    ) {}

  ngOnInit(): void {
  }

  hide = true;

  signup(signupForm: NgForm) {
    
    this.userService.signup(signupForm.value).subscribe(
      (response: any)=>{
        this.router.navigate(['/login']);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}