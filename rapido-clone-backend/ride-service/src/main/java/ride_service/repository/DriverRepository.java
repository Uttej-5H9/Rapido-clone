package ride_service.repository;


import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import ride_service.entity.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverRepository
        extends JpaRepository<Driver, Long> {

    List<Driver> findByAvailableTrueAndOnlineTrue();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        SELECT d
                FROM Driver d
                WHERE d.id = :driverId
    """)
    Optional<Driver> lockDriver(
            @Param("driverId") Long driverId
    );
}