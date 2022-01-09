package com.individuals3.backend_football.resource;

import com.individuals3.backend_football.domain.ChatMessage;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatResource {

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage greeting(ChatMessage message) throws Exception {
        return message;
    }
}
