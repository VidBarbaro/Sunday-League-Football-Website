import { Team } from "./team";
import { User } from "./user";

export class TeamPlayer {
    public id: number;
    public teamId: Team;
    public playerId: User;

    constructor() {
    }
}