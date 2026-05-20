package ride_service.dto;


import lombok.Data;
import ride_service.entity.RideStatus;

@Data
public class RideStatusEvent {
    private Long rideId;
    private RideStatus status;
    private String message;
}
