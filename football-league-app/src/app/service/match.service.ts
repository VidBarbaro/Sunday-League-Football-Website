import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpEvent, HttpResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { CustomHttpResponse } from '../model/custom-http-response';
import { Match } from '../model/match';
import { MatchDTO } from '../model/matchDTO';
import { TeamTablePosition } from '../model/teamTablePosition';

@Injectable({
  providedIn: 'root'
})
export class MatchService {
  private host = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public getMatches(): Observable<Match[]> {
    return this.http.get<Match[]>(`${this.host}/match/list`);
  }

  public getMatchesForTeam(teamId: number): Observable<Match[]> {
    return this.http.get<Match[]>(`${this.host}/match/list/${teamId}`);
  }

  public getMatchesForReferee(refereeId: number): Observable<Match[]> {
    return this.http.get<Match[]>(`${this.host}/match/list/referee/${refereeId}`);
  }

  public getLeagueTable(): Observable<TeamTablePosition[]> {
    return this.http.get<TeamTablePosition[]>(`${this.host}/teamTablePosition/list`);
  }

  public createNewMatch(formData: FormData): Observable<Match> {
    return this.http.post<Match>(`${this.host}/match/createMatch`, formData);
  }

  public addPointsFromMatchResult(matchId: number): Observable<Match> {
    return this.http.post<Match>(`${this.host}/teamTablePosition/addPointsFromMatchResult`, matchId);
  }

  public addMatchesToLocalCache(matches: Match[]): void {
    localStorage.setItem('matches', JSON.stringify(matches));
  }

  public getMatchesFromLocalCache(): Match[] {
    if(localStorage.getItem('matches')) {
      return JSON.parse(localStorage.getItem('matches'));
    }
    return null;
  }

  public deleteMatch(matchId: number): Observable<CustomHttpResponse> {
    return this.http.delete<CustomHttpResponse>(`${this.host}/match/delete/${matchId}`);
  }

  public updateMatch(formData: FormData): Observable<MatchDTO> {
    return this.http.post<MatchDTO>(`${this.host}/match/update`, formData);
  }

  public createMatchFormData(match: MatchDTO): FormData {
    const formData = new FormData();
    var datestr = (new Date(match.matchDateTime)).toISOString();
    formData.append('homeTeamId', match.homeTeamId);
    formData.append('awayTeamId', match.awayTeamId);
    formData.append('refereeId', match.refereeId);
    formData.append('matchDateTime', datestr);
    formData.append('location', match.location);
    return formData;
  }

  public createFinishedMatchFormData(match: MatchDTO): FormData {
    const formData = new FormData();
    var datestr = (new Date(match.matchDateTime)).toISOString();
    formData.append('matchId', match.id.toString());
    formData.append('homeTeamId', match.homeTeamId);
    formData.append('awayTeamId', match.awayTeamId);
    formData.append('refereeId', match.refereeId);
    formData.append('matchDateTime', datestr);
    formData.append('location', match.location);
    formData.append('homeTeamGoals', match.homeTeamGoals);
    formData.append('awayTeamGoals', match.awayTeamGoals);
    formData.append('isFinished', match.isFinished);
    return formData;
  }
}
