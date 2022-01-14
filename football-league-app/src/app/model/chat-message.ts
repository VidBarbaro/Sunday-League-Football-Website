import { AuthenticationService } from "../service/authentication.service";

export class ChatMessage {

    public username: string;
    public _name: string;

    constructor() {

    }

    get name(): string {
        return this._name;
    }

    set message(newMessage: string) {
        this._name = newMessage;
    }
}