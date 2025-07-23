package com.codemoa.project.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // WebSocket 메시지 브로커를 활성화합니다.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 WebSocket에 연결할 때 사용할 주소(endpoint)를 등록합니다.
        // "/ws-stomp"라는 주소로 클라이언트가 접속하며, SockJS를 지원하여 오래된 브라우저도 호환됩니다.
        registry.addEndpoint("/ws-stomp").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지 브로커 설정을 합니다.

        // "/sub"으로 시작하는 주소를 구독하는 클라이언트에게 메시지를 전달합니다. (Subscribe)
        registry.enableSimpleBroker("/sub");

        // "/pub"으로 시작하는 주소로 클라이언트가 메시지를 보내면, @MessageMapping이 붙은 메서드로 라우팅됩니다. (Publish)
        registry.setApplicationDestinationPrefixes("/pub");
    }
}