package ride_service.service;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ride_service.algorithm.DriverMatchingService;
import ride_service.dto.RideRequestDTO;
import ride_service.dto.RideResponseDTO;
import ride_service.entity.Driver;
import ride_service.entity.Ride;
import ride_service.entity.RideStatus;
import ride_service.entity.User;
import ride_service.exception.DuplicateRideException;
import ride_service.exception.InvalidRideStateException;
import ride_service.exception.NoDriversAvailableException;
import ride_service.repository.DriverRepository;
import ride_service.repository.RideRepository;
import ride_service.repository.UserRepository;
import ride_service.util.DistanceCalculator;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final DriverMatchingService driverMatchingService;
    private final DistanceCalculator distanceCalculator;
    private final FareService fareService;

    @Override
    @Transactional
    public RideResponseDTO requestRide(
            String username,
            RideRequestDTO dto) {

        User user = userRepository
                .findByEmail(username)
                .orElseThrow();

        boolean activeRideExists =
                rideRepository.existsByUserIdAndStatusIn(
                        user.getId(),
                        List.of(
                                RideStatus.REQUESTED,
                                RideStatus.ACCEPTED,
                                RideStatus.STARTED
                        )
                );

        if (activeRideExists) {
            throw new DuplicateRideException(
                    "User already has active ride"
            );
        }

        Driver nearestDriver =
                driverMatchingService.findNearestDriver(
                        dto.getPickupLatitude(),
                        dto.getPickupLongitude()
                );

        Driver lockedDriver =
                driverRepository.lockDriver(
                        nearestDriver.getId()
                ).orElseThrow();

        if (!lockedDriver.isAvailable()) {
            throw new NoDriversAvailableException(
                    "Driver unavailable"
            );
        }

        lockedDriver.setAvailable(false);

        double distance =
                distanceCalculator.calculateDistance(
                        dto.getPickupLatitude(),
                        dto.getPickupLongitude(),
                        dto.getDropLatitude(),
                        dto.getDropLongitude()
                );

        double fare =
                fareService.calculateFare(distance);

        Ride ride = Ride.builder()
                .userId(user.getId())
                .driverId(lockedDriver.getId())
                .pickupLatitude(dto.getPickupLatitude())
                .pickupLongitude(dto.getPickupLongitude())
                .dropLatitude(dto.getDropLatitude())
                .dropLongitude(dto.getDropLongitude())
                .estimatedDistance(distance)
                .estimatedFare(fare)
                .status(RideStatus.REQUESTED)
                .requestedAt(LocalDateTime.now())
                .build();

        rideRepository.save(ride);

        log.info("Ride {} created", ride.getId());

        return mapToDTO(ride);
    }

    @Override
    @Transactional
    public RideResponseDTO acceptRide(
            Long rideId,
            String username) {

        Ride ride =
                rideRepository.findById(rideId)
                        .orElseThrow();

        validateTransition(
                ride.getStatus(),
                RideStatus.ACCEPTED
        );

        ride.setStatus(RideStatus.ACCEPTED);

        return mapToDTO(ride);
    }

    @Override
    @Transactional
    public RideResponseDTO startRide(
            Long rideId,
            String username) {

        Ride ride =
                rideRepository.findById(rideId)
                        .orElseThrow();

        validateTransition(
                ride.getStatus(),
                RideStatus.STARTED
        );

        ride.setStatus(RideStatus.STARTED);

        return mapToDTO(ride);
    }

    @Override
    @Transactional
    public RideResponseDTO completeRide(
            Long rideId,
            String username) {

        Ride ride =
                rideRepository.findById(rideId)
                        .orElseThrow();

        validateTransition(
                ride.getStatus(),
                RideStatus.COMPLETED
        );

        ride.setStatus(RideStatus.COMPLETED);

        ride.setCompletedAt(LocalDateTime.now());

        Driver driver =
                driverRepository.findById(
                        ride.getDriverId()
                ).orElseThrow();

        driver.setAvailable(true);

        return mapToDTO(ride);
    }

    @Override
    @Transactional
    public RideResponseDTO cancelRide(
            Long rideId,
            String username) {

        Ride ride =
                rideRepository.findById(rideId)
                        .orElseThrow();

        ride.setStatus(RideStatus.CANCELLED);

        Driver driver =
                driverRepository.findById(
                        ride.getDriverId()
                ).orElseThrow();

        driver.setAvailable(true);

        return mapToDTO(ride);
    }

    @Override
    public Object getHistory(
            String username,
            Pageable pageable) {

        User user = userRepository
                .findByEmail(username)
                .orElseThrow();

        return rideRepository.findByUserId(
                user.getId(),
                pageable
        );
    }

    private void validateTransition(
            RideStatus current,
            RideStatus next) {

        switch (current) {

            case REQUESTED -> {
                if (next != RideStatus.ACCEPTED &&
                        next != RideStatus.CANCELLED) {

                    throw new InvalidRideStateException(
                            "Invalid transition"
                    );
                }
            }

            case ACCEPTED -> {
                if (next != RideStatus.STARTED &&
                        next != RideStatus.CANCELLED) {

                    throw new InvalidRideStateException(
                            "Invalid transition"
                    );
                }
            }

            case STARTED -> {
                if (next != RideStatus.COMPLETED) {

                    throw new InvalidRideStateException(
                            "Invalid transition"
                    );
                }
            }

            default -> throw new InvalidRideStateException(
                    "Invalid transition"
            );
        }
    }

    private RideResponseDTO mapToDTO(Ride ride) {

        return RideResponseDTO.builder()
                .rideId(ride.getId())
                .userId(ride.getUserId())
                .driverId(ride.getDriverId())
                .status(ride.getStatus())
                .estimatedDistance(ride.getEstimatedDistance())
                .estimatedFare(ride.getEstimatedFare())
                .build();
    }
}
