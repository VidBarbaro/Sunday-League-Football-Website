export class Team {
    public id: number;
    public teamId: string;
    public name: string;
    public teamManagerId: string;
    public logoUrl: string;

    constructor() {
        this.name = '';
        this.logoUrl = '';
    }
}