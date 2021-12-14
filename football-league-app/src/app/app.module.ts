import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { HeaderComponent } from './header/header.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { HomeComponent } from './home/home.component';
import { FixturesComponent } from './fixtures/fixtures.component';
import { ResultsComponent } from './results/results.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { LogInComponent } from './log-in/log-in.component';
import { ProfileComponent } from './profile/profile.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { LeagueTableComponent } from './league-table/league-table.component'
import { NgxEchartsModule } from 'ngx-echarts';
import { AdminComponent } from './admin/admin.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PlayersComponent } from './admin/players/players.component';
import { ManagersComponent } from './admin/managers/managers.component';
import { MatchComponent } from './fixtures/match/match.component';
import { ChangeDetailsComponent } from './profile/change-details/change-details.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthenticationService } from './service/authentication.service';
import { UserService } from './service/user.service';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import { AuthenticationGuard } from './guard/authentication.guard';
import { NotificationModule } from './notification.module';
import { NotificationService } from './service/notification.service';
import { PopUpComponent } from './pop-ups/edit-user-pop-up/edit-user-pop-up';
import { NewUserPopUpComponent } from './pop-ups/new-user-pop-up/new-user-pop-up.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ResetUserPasswordComponent } from './admin/reset-user-password/reset-user-password.component';
import { TeamManagerComponent } from './team-manager/team-manager.component';
import { MyTeamComponent } from './team-manager/my-team/my-team.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    FixturesComponent,
    ResultsComponent,
    StatisticsComponent,
    SignUpComponent,
    LogInComponent,
    ProfileComponent,
    LeagueTableComponent,
    AdminComponent,
    PlayersComponent,
    ManagersComponent,
    MatchComponent,
    ChangeDetailsComponent,
    ForbiddenComponent,
    NewUserPopUpComponent,
    PopUpComponent,
    ResetUserPasswordComponent,
    TeamManagerComponent,
    MyTeamComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    FormsModule,
    HttpClientModule,
    MatSortModule,
    RouterModule,
    NgxEchartsModule.forRoot({ echarts: () => import('echarts'),}),
    NotificationModule,
    ReactiveFormsModule
  ],
  providers: [NotificationService, AuthenticationGuard, AuthenticationService, UserService, { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
