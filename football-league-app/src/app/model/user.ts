import { Role } from "../enum/role.enum";

export class User {
    public userId: string;
    public firstName: string;
    public lastName: string;
    public username: string;
    public email: string;
    public logInDateDisplay: Date;
    public joinDate: Date;
    public profileImageUrl: string;
    public active: boolean;
    public notLocked: boolean;
    public role: Role;
    public authorities: [];

    constructor() {
        this.firstName = '';
        this.lastName = '';
        this.username = '';
        this.email = '';
        this.logInDateDisplay = null;
        this.active = false;
        this.notLocked = false;
        this.role = Role.USER;
        this.authorities = [];
    }
}