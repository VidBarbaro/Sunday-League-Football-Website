import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { ManagersComponent } from './admin/managers/managers.component';
import { PlayersComponent } from './admin/players/players.component';
import { FixturesComponent } from './fixtures/fixtures.component';
import { HomeComponent } from './home/home.component';
import { LeagueTableComponent } from './league-table/league-table.component';
import { LogInComponent } from './log-in/log-in.component';
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
  { path: 'log-in', component: LogInComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'admin',
    children: [
        {
          path: '',
          component: AdminComponent
        },
        {
            path: 'players',
            component: PlayersComponent
        },
        {
            path: 'teamManagers',
            component: ManagersComponent
        }
    ]},
  { path: '', redirectTo: '/home', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
