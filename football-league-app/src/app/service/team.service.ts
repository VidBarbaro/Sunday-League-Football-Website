import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { CustomHttpResponse } from "../model/custom-http-response";
import { Team } from "../model/team";
import { TeamPlayer } from "../model/teamPlayer";
import { User } from "../model/user";

@Injectable({
    providedIn: 'root'
  })
export class TeamService {
    private host = environment.apiUrl;

    constructor(private http: HttpClient) { }

    public getTeams(): Observable<Team[]> {
        return this.http.get<Team[]>(`${this.host}/team/list`);
    }

    public getTeamByManagerId(teamManagerId: string): Observable<Team> {
        return this.http.get<Team>(`${this.host}/team/managerId/${teamManagerId}`);
    }

    public getTeamByPlayerId(playerId: number): Observable<Team> {
        return this.http.get<Team>(`${this.host}/teamPlayers/playerId/${playerId}`);
    }

    public getTeamByTeamName(teamName: string): Observable<Team> {
        return this.http.get<Team>(`${this.host}/team/name/${teamName}`);
    }

    public getPlayersByTeamId(teamId: number): Observable<User[]> {
        return this.http.get<User[]>(`${this.host}/teamPlayers/${teamId}`);
    }

    
    public addTeam(formData: FormData): Observable<Team> {
        return this.http.post<Team>(`${this.host}/team/add`, formData);
    }

    public addPlayerToTeam(teamPlayer: TeamPlayer): Observable<TeamPlayer> {
        return this.http.post<TeamPlayer>(`${this.host}/teamPlayers/addPlayerToTeam`, teamPlayer);
    } 

    public removePlayerFromTeam(playerId: number): Observable<CustomHttpResponse> {
        return this.http.delete<CustomHttpResponse>(`${this.host}/teamPlayers/removePlayerFromTeam/${playerId}`);
    } 

    public addTeamToLocalCache(team: Team): void {
        localStorage.setItem('team', JSON.stringify(team));
    }

    
    public addTeamsToLocalCache(teams: Team[]): void {
        localStorage.setItem('teams', JSON.stringify(teams));
    }

    public getTeamFromLocalCache(): Team {
        if(localStorage.getItem('team')) {
            return JSON.parse(localStorage.getItem('team'));
          }
          return null;
    }

    public addPlayersToLocalCache(players: User[]): void {
        localStorage.setItem('players', JSON.stringify(players));
    }

    public getPlayersFromLocalCache(): User[] {
        if(localStorage.getItem('players')) {
            return JSON.parse(localStorage.getItem('players'));
          }
          return null;
    }

    // public createTeamPlayerFormData(teamId: Team, userId: User): FormData {
    //     const formData = new FormData();
    //     formData.append('teamId', teamId);
    //     formData.append('userId', userId);
    //     return formData;
    // }
    
    public createTeamFormData(managerId: string, teamName: string, profileImage: File): FormData {
        const formData = new FormData();
        formData.append('teamManagerId', managerId);
        formData.append('teamName', teamName);
        formData.append('clubLogo', profileImage);
        return formData;
      }
}