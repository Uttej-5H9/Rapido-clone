package driver_service.service;


import driver_service.dto.DriverProfileDTO;

public interface DriverService {

    DriverProfileDTO getProfile(String email);

    void updateAvailability(
            String email,
            Boolean available
    );

    void updateOnlineStatus(
            String email,
            Boolean online
    );

    void updateLocation(
            String email,
            Double latitude,
            Double longitude
    );
}
