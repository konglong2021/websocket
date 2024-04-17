package com.example.websocket.socketJS;

import com.example.websocket.dto.ResponseMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WSService {
    private SimpMessagingTemplate messagingTemplate;
    private NotificationService notificationService;

    public WSService(SimpMessagingTemplate messagingTemplate, NotificationService notificationService) {
        this.messagingTemplate = messagingTemplate;
        this.notificationService = notificationService;
    }

    public void notifyFrontend(final String message) {
        ResponseMessage responseMessage = new ResponseMessage(message);
        notificationService.sendGlobalNotification();
        messagingTemplate.convertAndSend("/topic/messages", responseMessage);
    }

    public void notifyUser(final String id,final String message) {
        ResponseMessage responseMessage = new ResponseMessage(message);
        notificationService.sendPrivateNotification(id);
        messagingTemplate.convertAndSendToUser(id,"/topic/private-messages", responseMessage);
    }
}
