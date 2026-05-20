package ride_service.dto;

import lombok.Builder;
import lombok.Data;
import ride_service.entity.RideStatus;

@Data
@Builder
public class RideResponseDTO {

    private Long rideId;

    private Long userId;

    private Long driverId;

    private RideStatus status;

    private Double estimatedDistance;

    private Double estimatedFare;
}
