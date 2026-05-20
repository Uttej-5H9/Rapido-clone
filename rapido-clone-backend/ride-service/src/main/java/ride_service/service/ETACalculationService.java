package ride_service.service;

import org.springframework.stereotype.Service;

@Service
public class ETACalculationService {
    private static final double AVG_SPEED_KMH = 30.0;
    public double calculateETA(
            double driverLat,
            double driverLon) {
        double riderLat = 17.4000;
        double riderLon = 78.4900;
        double distance =
                calculateDistance(
                        driverLat,
                        driverLon,
                        riderLat,
                        riderLon);

        return (distance / AVG_SPEED_KMH) * 60;
    }
    private double calculateDistance(
            double lat1,
            double lon1,
            double lat2,
            double lon2) {
        double earthRadius = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                        + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2)
                        * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(
                        Math.sqrt(a),
                        Math.sqrt(1 - a));
        return earthRadius * c;
    }
}
