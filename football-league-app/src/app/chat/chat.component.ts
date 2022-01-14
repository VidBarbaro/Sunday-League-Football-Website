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
  styleUrls: ['./chat.component.css']
})


export class ChatComponent implements OnInit {

  private authService: AuthenticationService;

  constructor(authService: AuthenticationService) { this.authService = authService; }

  stompClient: Stomp.Client;
  msgToSend: string = "Enter your message here";
  chatMessages: any[] = []; 
  display: ChatMessage[] = []; 
  // chatDisplay: ChatMessage[] = []; 

  ngOnInit(): void {
    this.connect();
  }

  public connect(): void {
    let socket = SockJS(environment.apiUrl + "/ws");
    let stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
      // subscribe to the backend
      stompClient.subscribe('/topic/greetings', (data) => {

          console.log(JSON.parse(data.body));
          var message = JSON.parse(data.body);
          this.chatMessages.push(message);
          console.log(this.chatMessages);
          
          this.onMessageReceived(data);
          this.display = this.chatMessages;
          // console.log(this.chatDisplay);
          

      });
  });
  this.stompClient = stompClient;
  }

  public onMessageReceived(data: Stomp.Message) {
    const result = JSON.parse(data.body);
  };


  public send() {
    this.stompClient.send("/app/hello", {}, JSON.stringify({'username': this.authService.getUserFromLocalCache().username, 'name': this.msgToSend}));
    // this.chatMessages.push(this.msgToSend);
    console.log(this.chatMessages);
    
    // this.chatDisplay = this.chatMessages;
  }

  public setSendMessage(value: string) {
    this.msgToSend = value;
  }
}
