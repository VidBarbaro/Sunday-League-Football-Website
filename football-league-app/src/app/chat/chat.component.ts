import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Input, OnInit, ViewChild } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { environment } from 'src/environments/environment';
import * as Stomp from 'stompjs';
import { ChatMessage } from '../model/chat-message';
import { AuthenticationService } from '../service/authentication.service';
import { ChatService } from '../service/chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
  changeDetection: ChangeDetectionStrategy.Default
})


export class ChatComponent implements OnInit {

  constructor(
    private changeDetection: ChangeDetectorRef
  ) { }

  @Input() message1: string;
  stompClient: Stomp.Client;
  chatMessage: ChatMessage = null;
  chatMessages: Array<any> = [];
  chatDisplay: Array<ChatMessage> = [];

  ngOnInit(): void {
    this.connect();
    this.chatMessage = new ChatMessage()
  }

  public connect(): void {
    let socket = SockJS(environment.apiUrl + "/wsc");
    let stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
      // subscribe to the backend
      stompClient.subscribe('/topic/public', (data) => {
          this.onMessageReceived(data);

          this.chatMessages.push(JSON.parse(data.body));
          this.chatDisplay = this.chatMessages;
      });
  });
  this.stompClient = stompClient;
  }

  public onMessageReceived(data: Stomp.Message) {
    const result = JSON.parse(data.body);
  };


  public send() {
    this.stompClient.send("/app/chat.send", {}, JSON.stringify({ 'content': this.chatMessage.message, 'username': this.chatMessage.username }));
    this.chatMessages.push(this.chatMessage.message); //this is to add it to the list of messages to be displayed
    this.chatDisplay = this.chatMessages;
  }

  public setChatMessage() {
    this.chatMessage.message = this.message1;
  }

}
