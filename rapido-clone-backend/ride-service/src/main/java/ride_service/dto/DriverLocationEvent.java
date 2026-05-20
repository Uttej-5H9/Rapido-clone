package ride_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DriverLocationEvent {

    private Long rideId;
    private Long driverId;
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;
}
