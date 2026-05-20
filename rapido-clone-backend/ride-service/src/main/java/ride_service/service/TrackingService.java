package ride_service.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ride_service.dto.DriverLocationEvent;

@Service
public class TrackingService {
    private final SimpMessagingTemplate messagingTemplate;
    public TrackingService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    public void processLocation(DriverLocationEvent event) {
        messagingTemplate.convertAndSend(
                "/topic/ride/" + event.getRideId(),
                event
        );
        System.out.println("Broadcasted location for ride: " + event.getRideId());
    }
}