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

    constructor() {
    }
}