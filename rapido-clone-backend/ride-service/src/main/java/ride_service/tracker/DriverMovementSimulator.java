package ride_service.tracker;


import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ride_service.dto.DriverLocationEvent;
import ride_service.service.TrackingService;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DriverMovementSimulator {
    private final TrackingService trackingService;
    private double latitude = 17.3850;
    private double longitude = 78.4867;
    @Scheduled(fixedRate = 3000)
    public void simulateMovement() {
        latitude += randomOffset();
        longitude += randomOffset();
        DriverLocationEvent event = new DriverLocationEvent();
        event.setRideId(1L);
        event.setDriverId(101L);
        event.setLatitude(latitude);
        event.setLongitude(longitude);
        event.setTimestamp(LocalDateTime.now());
        trackingService.processLocation(event);
    }
    private double randomOffset() {
        return (Math.random() - 0.5) / 1000;
    }
}
