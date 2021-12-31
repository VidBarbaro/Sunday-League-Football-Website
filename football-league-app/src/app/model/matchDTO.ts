import { Team } from "./team";
import { User } from "./user";

export class MatchDTO {
    public id: number;
    public homeTeamId: string;
    public awayTeamId: string;
    public refereeId: string;
    public matchDateTime: Date;
    public location: string;
    public homeTeamGoals: string;
    public awayTeamGoals: string;
    public isFinished: string;

    // constructor(homeTeamId: string, awayTeamId: string, refereeId: string, matchDateTime: Date, location: string) {
    //     this.homeTeamId = homeTeamId;
    //     this.awayTeamId = awayTeamId;
    //     this.refereeId = refereeId;
    //     this.matchDateTime = matchDateTime;
    //     this.location = location;
    // }
    constructor() {}
}