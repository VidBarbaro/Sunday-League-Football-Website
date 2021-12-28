import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpEvent, HttpResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { CustomHttpResponse } from '../model/custom-http-response';
import { Match } from '../model/match';
import { NgForm } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class MatchService {
  private host = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public getMatches(): Observable<Match[]> {
    return this.http.get<Match[]>(`${this.host}/match/list`);
  }

  public createNewMatch(formData: FormData): Observable<Match> {
    return this.http.post<Match>(`${this.host}/match/createMatch`, formData);
  }

  public addMatchesToLocalCache(matches: Match[]): void {
    localStorage.setItem('matches', JSON.stringify(matches));
  }

  public createMatchFormData(match: Match): FormData {
    const formData = new FormData();
    var datestr = (new Date(match.matchDateTime)).toUTCString();
    alert(match.matchDateTime);
    formData.append('homeTeamId', match.homeTeamId.name);
    formData.append('awayTeamId', match.awayTeamId.name);
    formData.append('refereeId', match.refereeId.username);
    formData.append('matchDateTime', datestr);
    formData.append('location', match.location);
    return formData;
  }
}
