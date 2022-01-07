import { Team } from "./team";

export class TeamTablePosition {
    public id: number;
    public teamId: Team;
    public points: number;
    public wins: number;
    public loses: number;
    public draws: number;
    public goalsFor: number;
    public goalsAgainst: number;

    constructor() {
    }
}