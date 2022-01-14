package com.individuals3.backend_football.resource;

import com.individuals3.backend_football.domain.Greeting;
import com.individuals3.backend_football.domain.HelloMessage;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatResource {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public HelloMessage greeting(HelloMessage message) throws Exception {
        return message;
    }

}
