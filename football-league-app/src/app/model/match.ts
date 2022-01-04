import { Team } from "./team";
import { User } from "./user";

export class Match {
    public id: number;
    public homeTeamId: Team;
    public awayTeamId: Team;
    public refereeId: User;
    public matchDateTime: Date;
    public location: string;
    public homeTeamGoals: number;
    public awayTeamGoals: number;
    public isFinished: boolean;

    constructor(homeTeamId: Team, awayTeamId: Team, refereeId: User, matchDateTime: Date, location: string, isFinished: boolean) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.refereeId = refereeId;
        this.matchDateTime = matchDateTime;
        this.location = location;
        this.isFinished = isFinished;
    }
}