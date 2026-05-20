package ride_service.service;


import org.springframework.data.domain.Pageable;
import ride_service.dto.RideRequestDTO;
import ride_service.dto.RideResponseDTO;

public interface RideService {
    RideResponseDTO requestRide(
            String username,
            RideRequestDTO dto
    );
    RideResponseDTO acceptRide(
            Long rideId,
            String username
    );
    RideResponseDTO startRide(
            Long rideId,
            String username
    );
    RideResponseDTO completeRide(
            Long rideId,
            String username
    );
    RideResponseDTO cancelRide(
            Long rideId,
            String username
    );
    Object getHistory(
            String username,
            Pageable pageable
    );
}
