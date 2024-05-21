package fr.mspr_java_b3.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    @EventListener
    public void handleWebSocketDisconnectListener(
            SessionDisconnectEvent event
    ) {
        System.out.println("**********************");
        System.out.println(event.toString());
        System.out.println("**********************");
    }

    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        System.out.println("**********************");
        System.out.println(event.toString());
        System.out.println("**********************");
    }
}
