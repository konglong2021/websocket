package com.example.websocket.socketJS;

import com.example.websocket.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendGlobalNotification() {
        ResponseMessage message = new ResponseMessage("Global notification sent!");
        messagingTemplate.convertAndSend("/topic/global-notifications", message);
    }

    public void sendPrivateNotification(final String userId) {
        ResponseMessage message = new ResponseMessage("Private notification sent!");
        messagingTemplate.convertAndSendToUser(userId,"/topic/private-notifications", message);
    }
}
