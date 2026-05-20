package ride_service.service;

import org.springframework.stereotype.Service;

@Service
public class FareService {
    private static final double BASE_FARE = 40;
    private static final double PER_KM_RATE = 12;
    public double calculateFare(double distance) {
        return BASE_FARE + (distance * PER_KM_RATE);
    }
}
