import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { ManagersComponent } from './admin/managers/managers.component';
import { PlayersComponent } from './admin/players/players.component';
import { ResetUserPasswordComponent } from './admin/reset-user-password/reset-user-password.component';
import { FixturesComponent } from './fixtures/fixtures.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { AuthenticationGuard } from './guard/authentication.guard';
import { HomeComponent } from './home/home.component';
import { LeagueTableComponent } from './league-table/league-table.component';
import { LogInComponent } from './log-in/log-in.component';
import { ChangeDetailsComponent } from './profile/change-details/change-details.component';
import { ProfileComponent } from './profile/profile.component';
import { ResultsComponent } from './results/results.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { StatisticsComponent } from './statistics/statistics.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'fixtures', component: FixturesComponent },
  { path: 'league-table', component: LeagueTableComponent },
  { path: 'results', component: ResultsComponent },
  { path: 'statistics', component: StatisticsComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'login', component: LogInComponent },
  { path: 'forbidden', component: ForbiddenComponent },
  { path: 'profile',
    canActivate: [AuthenticationGuard],
    children: [
        {
          path: '',
          component: ProfileComponent
        },
        {
            path: 'change-details',
            component: ChangeDetailsComponent
        }
    ]},
  { path: 'admin',
    canActivate: [AuthenticationGuard],
    children: [
        {
          path: '',
          component: AdminComponent
        },
        {
          path: 'reset-user-password',
          component: ResetUserPasswordComponent
        }
    ]},
  { path: '', redirectTo: '/home', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
