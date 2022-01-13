import { Component, OnInit } from '@angular/core';
import { EChartsOption } from 'echarts';
import { Match } from '../model/match';
import { Team } from '../model/team';
import { TeamTablePosition } from '../model/teamTablePosition';
import { MatchService } from '../service/match.service';
import { StatisticsService } from '../service/statistics.service';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {

  constructor(private statisticsService: StatisticsService, private matchService: MatchService) { }

  matchesMostGoals: EChartsOption 
  matchesWithMostGoals: Match[] = [];
  match1: Match = null;
  match2: Match = null;
  match3: Match = null;

  top3HomeTeamsGoals: EChartsOption 
  top3HomeTeams: Match[] = [];
  team1: Team = null;
  team2: Team = null;
  team3: Team = null;

  top3AwayTeamsGoals: EChartsOption 
  top3AwayTeams: Match[] = [];
  team1Away: Team = null;
  team2Away: Team = null;
  team3Away: Team = null;

  clubGoals: EChartsOption
  teams: TeamTablePosition[] = [];


  ngOnInit(): void {
    this.getMatchesWithMostGoals();
    this.getTop3HomeTeams();
    this.getTop3AwayTeams();
    this.getTeamsAndGoals();
  }

  public getMatchesWithMostGoals() {
      this.statisticsService.getTopGoalsForSingleMatch().subscribe(
        (response: Match[]) => {
          this.matchesWithMostGoals = response;
          this.match1 = this.matchesWithMostGoals[0];
          this.match2 = this.matchesWithMostGoals[1];
          this.match3 = this.matchesWithMostGoals[2];
          this.matchesMostGoals = {
            color: ['#673ab7'],
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                type: 'shadow'
              }
            },
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },
            xAxis: [
              {
                type: 'category',
                data: [this.match1.homeTeamId.name, this.match2.homeTeamId.name, this.match3.homeTeamId.name],
                axisTick: {
                  alignWithLabel: true
                }
              }
            ],
            yAxis: [{
              type: 'value'
            }],
            series: [{
              name: 'Goals',
              type: 'bar',
              barWidth: '60%',
              data: [this.match1.homeTeamGoals, this.match2.homeTeamGoals, this.match3.homeTeamGoals]
            }]
          };
          }
        )
  }

  public getTop3HomeTeams() {
    this.statisticsService.getTop3HomeTeams().subscribe(
      (response: Match[]) => {
        this.top3HomeTeams = response;
        this.team1 = this.top3HomeTeams[0].homeTeamId;
        this.team2 = this.top3HomeTeams[1].homeTeamId;
        this.team3 = this.top3HomeTeams[2].homeTeamId;
        this.top3HomeTeamsGoals = {
          color: ['#673ab7'],
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: [
            {
              type: 'category',
              data: [this.team1.name, this.team2.name, this.team3.name],
              axisTick: {
                alignWithLabel: true
              }
            }
          ],
          yAxis: [{
            type: 'value'
          }],
          series: [{
            name: 'Goals',
            type: 'bar',
            barWidth: '60%',
            data: [this.top3HomeTeams[0].homeTeamGoals, this.top3HomeTeams[1].homeTeamGoals, this.top3HomeTeams[2].homeTeamGoals]
          }]
        };
        }
      )
}

public getTop3AwayTeams() {
  this.statisticsService.getTop3AwayTeams().subscribe(
    (response: Match[]) => {
      this.top3AwayTeams = response;
      this.team1Away = this.top3AwayTeams[0].awayTeamId;
      this.team2Away = this.top3AwayTeams[1].awayTeamId;
      this.team3Away = this.top3AwayTeams[2].awayTeamId;
      this.top3AwayTeamsGoals = {
        color: ['#673ab7'],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: [this.team1Away.name, this.team2Away.name, this.team3Away.name],
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [{
          type: 'value'
        }],
        series: [{
          name: 'Goals',
          type: 'bar',
          barWidth: '60%',
          data: [this.top3AwayTeams[0].awayTeamGoals, this.top3AwayTeams[1].awayTeamGoals, this.top3AwayTeams[2].awayTeamGoals]
        }]
      };
      }
    )
}

public getTeamsAndGoals() {
    this.matchService.getLeagueTable().subscribe(
      (response: TeamTablePosition[]) => {
        this.teams = response;
        console.log(this.teams);
        
        console.log(this.teams[0].goalsFor);
        console.log(this.teams[0].teamId.name);
        this.clubGoals = {
          // color: ['#673ab7'],
          tooltip: {
            trigger: 'item',
            axisPointer: {
              type: 'shadow'
            }
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          series: [{
            name: 'Goals',
            type: 'pie',
            data: [
              {"value": this.teams[0].goalsFor, "name": this.teams[0].teamId.name},
              {"value": this.teams[1].goalsFor, "name": this.teams[1].teamId.name},
              {"value": this.teams[2].goalsFor, "name": this.teams[2].teamId.name},
              {"value": this.teams[3].goalsFor, "name": this.teams[3].teamId.name},
              {"value": this.teams[4].goalsFor, "name": this.teams[4].teamId.name},
              {"value": this.teams[5].goalsFor, "name": this.teams[5].teamId.name}
            ]
          }]
        };
        }
      )
}
}
