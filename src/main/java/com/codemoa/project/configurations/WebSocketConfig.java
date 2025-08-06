package com.codemoa.project.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // ▼▼▼ [핵심 수정 부분] ▼▼▼
        // 메시지 브로커가 "/sub"과 "/queue"로 시작하는 주소를 모두 처리하도록 설정합니다.
        registry.enableSimpleBroker("/sub", "/queue");
        // ▲▲▲ [핵심 수정 부분] ▲▲▲

        // "/pub"으로 시작하는 주소로 클라이언트가 메시지를 보내면, @MessageMapping이 붙은 메서드로 라우팅됩니다.
        registry.setApplicationDestinationPrefixes("/pub");
    }
}