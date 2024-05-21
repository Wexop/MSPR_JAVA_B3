package fr.mspr_java_b3.security;

import io.jsonwebtoken.Claims;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/newMessage/annonce");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        String[] allowedOrigins = {"http://localhost:3000", "https://tom-chanson.github.io/MSPR1_FRONT_B3/"};
        registry.addEndpoint("/websocket").setAllowedOrigins(allowedOrigins).withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(@NonNull Message<?> message,@NonNull MessageChannel channel) {
                StompHeaderAccessor accessor =
                        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                System.out.println(accessor);
                JwtUtil jwtUtil = new JwtUtil();
                Claims claims = jwtUtil.resolveClaimsWebsocket(accessor);
                try {
                    if (claims == null || !jwtUtil.validateClaims(claims)) {
                        System.out.println("**************************************************");
                        System.out.println("Token invalid");
                        System.out.println("**************************************************");
                        return null;
                    }
                } catch (Exception e) {
                    System.out.println("**************************************************");
                    System.out.println("Token invalid");
                    System.out.println("**************************************************");
                    return null;
                }
                assert accessor != null;
                return message;
            }
        });
    }
}
