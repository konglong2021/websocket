package com.example.websocket.socketJS;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("our-websocket")
                .setHandshakeHandler(new UserHandshakeHandler())
                .withSockJS();
        registry.addEndpoint("our-websocket")
                .setHandshakeHandler(new UserHandshakeHandler());
        WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
    }

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/ws");
        WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
    }
}
