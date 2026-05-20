package driver_service.service;


import driver_service.dto.DriverProfileDTO;
import driver_service.entity.Driver;
import driver_service.exception.ResourceNotFoundException;
import driver_service.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    public DriverProfileDTO getProfile(String email) {

        Driver driver = driverRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Driver not found"));

        DriverProfileDTO dto = new DriverProfileDTO();
        dto.setFullName(driver.getFullName());
        dto.setPhone(driver.getPhone());
        dto.setVehicleNumber(driver.getVehicleNumber());
        dto.setVehicleModel(driver.getVehicleModel());
        dto.setVehicleType(driver.getVehicleType());

        return dto;
    }

    @Override
    public void updateAvailability(String email, Boolean available) {

        Driver driver = driverRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Driver not found"));

        driver.setAvailable(available);

        driverRepository.save(driver);

        log.info("Driver availability updated: {}", email);
    }

    @Override
    public void updateOnlineStatus(String email, Boolean online) {

        Driver driver = driverRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Driver not found"));

        driver.setOnline(online);

        driverRepository.save(driver);

        log.info("Driver online status updated: {}", email);
    }

    @Override
    public void updateLocation(String email, Double latitude, Double longitude) {

        if (latitude < -90 || latitude > 90 ||
                longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Invalid coordinates");
        }

        Driver driver = driverRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Driver not found"));

        driver.setCurrentLatitude(latitude);
        driver.setCurrentLongitude(longitude);

        driverRepository.save(driver);

        log.info("Driver location updated: {}", email);
    }
}
