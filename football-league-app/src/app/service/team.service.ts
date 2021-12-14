import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Team } from "../model/team";

@Injectable({
    providedIn: 'root'
  })
export class TeamService {
    private host = environment.apiUrl;

    constructor(private http: HttpClient) { }

    public getTeamByManagerId(teamManagerId: string): Observable<Team> {
        return this.http.get<Team>(`${this.host}/team/managerId/${teamManagerId}`);
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
    
    public createTeamForm(loggedInId: string, team: Team, profileImage: File): FormData {
        const formData = new FormData();
        formData.append('teamManagerId', loggedInId);
        formData.append('name', team.name);
        formData.append('clubLogoUrl', team.logoUrl);
        return formData;
      }
}