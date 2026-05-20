package ride_service.algorithm;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ride_service.entity.Driver;
import ride_service.exception.NoDriversAvailableException;
import ride_service.repository.DriverRepository;
import ride_service.util.DistanceCalculator;

import java.util.Comparator;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DriverMatchingService {

    private final DriverRepository driverRepository;

    private final DistanceCalculator distanceCalculator;

    public Driver findNearestDriver(
            double pickupLat,
            double pickupLon) {

        List<Driver> drivers =
                driverRepository
                        .findByAvailableTrueAndOnlineTrue();

        return drivers.stream()
                .min(Comparator.comparingDouble(driver ->
                        distanceCalculator.calculateDistance(
                                pickupLat,
                                pickupLon,
                                driver.getLatitude(),
                                driver.getLongitude()
                        )))
                .orElseThrow(() ->
                        new NoDriversAvailableException(
                                "No nearby drivers available"
                        ));
    }
}
