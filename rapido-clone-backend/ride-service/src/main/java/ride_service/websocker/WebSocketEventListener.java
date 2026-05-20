package ride_service.websocker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
@Slf4j
public class WebSocketEventListener {

    @EventListener
    public void handleConnect(
            SessionConnectEvent event) {

        log.info("Socket connected");
    }

    @EventListener
    public void handleDisconnect(
            SessionDisconnectEvent event) {

        log.info("Socket disconnected");
    }

    @EventListener
    public void handleSubscribe(
            SessionSubscribeEvent event) {

        log.info("Rider subscribed");
    }
}
