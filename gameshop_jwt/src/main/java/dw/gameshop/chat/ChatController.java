package dw.gameshop.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

    @Controller
    public class ChatController {

        @MessageMapping("/chat.sendMessage")
        @SendTo("/topic/public") // 구독명이 SendTo
        public ChatMessage sendMessage(
                @Payload ChatMessage chatMessage
        ) {
            return chatMessage;
        }

        @MessageMapping("/chat.addUser") // 입장
        @SendTo("/topic/public")
        public ChatMessage addUser(
                @Payload ChatMessage chatMessage,
                SimpMessageHeaderAccessor headerAccessor
        ) {
            // Add username in web socket session
            // 연결종료 시 누구의 연결이 종료된 것인지 확인하기 위해 저장
            headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
            return chatMessage;
        }
    }
