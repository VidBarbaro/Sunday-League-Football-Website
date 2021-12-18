import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Team } from "../model/team";
import { User } from "../model/user";

@Injectable({
    providedIn: 'root'
  })
export class TeamService {
    private host = environment.apiUrl;

    constructor(private http: HttpClient) { }

    public getTeamByManagerId(teamManagerId: string): Observable<Team> {
        return this.http.get<Team>(`${this.host}/team/managerId/${teamManagerId}`);
    }

    public getPlayersByTeamName(teamId: string): Observable<User[]> {
        return this.http.get<User[]>(`${this.host}/teamPlayers/${teamId}`);
    }

    
    public addTeam(formData: FormData): Observable<Team> {
        return this.http.post<Team>(`${this.host}/team/add`, formData);
    }

    public addTeamToLocalCache(team: Team): void {
        localStorage.setItem('team', JSON.stringify(team));
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
    
    public createTeamFormData(managerId: string, teamName: string, profileImage: File): FormData {
        const formData = new FormData();
        formData.append('teamManagerId', managerId);
        formData.append('teamName', teamName);
        formData.append('clubLogo', profileImage);
        return formData;
      }
}