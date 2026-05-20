package ride_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ride_service.entity.DriverLocation;

public interface DriverLocationRepository
        extends JpaRepository<DriverLocation, Long> {
}
