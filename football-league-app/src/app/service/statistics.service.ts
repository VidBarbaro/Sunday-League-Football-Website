import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Match } from "../model/match";

@Injectable({
    providedIn: 'root'
  })
export class StatisticsService {
    private host = environment.apiUrl;

    constructor(private http: HttpClient) { }

    public getTopGoalsForSingleMatch(): Observable<Match[]> {
        return this.http.get<Match[]>(`${this.host}/statistics/topGoalsForSingleMatch`);
    }

    public getTop3HomeTeams(): Observable<Match[]> {
        return this.http.get<Match[]>(`${this.host}/statistics/top3HomeTeams`);
    }

    public getTop3AwayTeams(): Observable<Match[]> {
        return this.http.get<Match[]>(`${this.host}/statistics/top3AwayTeams`);
    }
}