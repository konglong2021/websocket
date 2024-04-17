package com.example.websocket.websocket;

import com.example.websocket.websocket.dto.RequestWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/websocket")
public class WebSocketController {
    @Autowired
    private ServerWebSocketHandler webSocketHandler;

    @PostMapping("/send-message")
    public void sendMessageToUser(@RequestBody RequestWebSocket message){
        webSocketHandler.sendMessageToUser(message.getSessionId(), message.getMessage());
    }
}
