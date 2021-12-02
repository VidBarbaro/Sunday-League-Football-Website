import { Component } from '@angular/core';
import { Router } from '@angular/router';
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
}
