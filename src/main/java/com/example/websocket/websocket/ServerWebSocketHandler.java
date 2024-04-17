package com.example.websocket.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ServerWebSocketHandler implements WebSocketHandler {

    private final Logger LOG = LoggerFactory.getLogger(ServerWebSocketHandler.class);
    private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.sessions.put(session.getId(), session);
        LOG.info("Connection established on session: {}", session.getId());
        LOG.info("Connection established on session: {}", session.getHandshakeHeaders());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = (String) message.getPayload();
        LOG.info("Message received on session: {} with payload: {}", session.getId(), payload);
        //session.sendMessage(new TextMessage("Started processing payload: " + session + " - "+ payload));
        Thread.sleep(1000);
        session.sendMessage(new TextMessage("Message received: " + payload));

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        this.sessions.remove(session.getId());
        LOG.info("Exception occurred: {} on session id: {}", exception.getMessage(),session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        LOG.info("Connection closed on session: {} with status: {}", session.getId(), closeStatus.getCode());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessageToUser(String sessionId,String message){
        WebSocketSession session = this.sessions.get(sessionId);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                sessions.remove(sessionId);
                e.printStackTrace();
            }
        }
    }
}
