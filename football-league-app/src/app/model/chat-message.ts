import { AuthenticationService } from "../service/authentication.service";

export class ChatMessage {

    public username: string;
    public _message: string;

    constructor() {

    }

    get message(): string {
        return this._message;
    }

    set message(newMessage: string) {
        this._message = newMessage;
    }
}