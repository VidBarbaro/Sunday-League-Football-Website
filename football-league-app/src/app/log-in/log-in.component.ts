import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  constructor(
    private userService: UserService, 
    private userAuthService: UserAuthService,
    private router: Router
    ) {}

  ngOnInit(): void {
  }

  hide = true;

  login(loginForm: NgForm) {
    
    this.userService.login(loginForm.value).subscribe(
      (response: any)=>{
        this.userAuthService.setRoles(response.user.role);
        this.userAuthService.setToken(response.jwtToken);
        this.userAuthService.setUserName(response.user.userName);

        const role = response.user.role[0].roleName;
        if(role === "Admin") {
          this.router.navigate(['/admin']);
        }
        else {
          this.router.navigate(['/home']);
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
