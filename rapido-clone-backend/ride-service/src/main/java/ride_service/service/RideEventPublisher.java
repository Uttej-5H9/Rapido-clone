package ride_service.service;


import lombok.RequiredArgsConstructor;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ride_service.dto.RideStatusEvent;
import ride_service.entity.RideStatus;

@Service
public class RideEventPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public RideEventPublisher(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void publish(Long rideId, String status, String message) {

        RideStatusEvent event = new RideStatusEvent();
        event.setRideId(rideId);
        event.setStatus(RideStatus.valueOf(status));
        event.setMessage(message);

        messagingTemplate.convertAndSend(
                "/topic/ride-status/" + rideId,
                event
        );
    }
}