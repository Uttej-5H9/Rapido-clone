package ride_service.repository;


import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import ride_service.entity.Ride;
import ride_service.entity.RideStatus;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {

    boolean existsByUserIdAndStatusIn(
            Long userId,
            List<RideStatus> statuses
    );

    Page<Ride> findByUserId(Long userId, Pageable pageable);

    Page<Ride> findByDriverId(Long driverId, Pageable pageable);
}
