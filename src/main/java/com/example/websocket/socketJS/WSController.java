package com.example.websocket.socketJS;

import com.example.websocket.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WSController {

    @Autowired
    private WSService WSService;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody Message message) {
        WSService.notifyFrontend(message.getMessageContent());
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable final String id,
            @RequestBody Message message) {
        WSService.notifyUser(id,message.getMessageContent());
    }
}
