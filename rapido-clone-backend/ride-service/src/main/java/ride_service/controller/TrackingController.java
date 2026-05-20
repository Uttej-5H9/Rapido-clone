package ride_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ride_service.dto.DriverLocationEvent;
import ride_service.service.TrackingService;

@RestController
@RequestMapping("/tracking")
public class TrackingController {
    private final TrackingService trackingService;
    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }
    @PostMapping("/driver/location/live")
    public ResponseEntity<String> updateLiveLocation(
            @RequestBody DriverLocationEvent event) {
        trackingService.processLocation(event);
        return ResponseEntity.ok("Location Updated");
    }
}