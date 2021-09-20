import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FixturesComponent } from './fixtures/fixtures.component';
import { HomeComponent } from './home/home.component';
import { LogInComponent } from './log-in/log-in.component';
import { ProfileComponent } from './profile/profile.component';
import { ResultsComponent } from './results/results.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { TableComponent } from './table/table.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'fixtures', component: FixturesComponent },
  { path: 'table', component: TableComponent },
  { path: 'results', component: ResultsComponent },
  { path: 'statistics', component: StatisticsComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'log-in', component: LogInComponent },
  { path: 'profile', component: ProfileComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
