package driver_service.repository;

import driver_service.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByEmail(String email);

    List<Driver> findByAvailableTrueAndOnlineTrue();
}
