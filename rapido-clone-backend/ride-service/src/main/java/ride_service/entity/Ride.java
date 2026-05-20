package ride_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "rides",
        indexes = {
                @Index(name = "idx_user", columnList = "userId"),
                @Index(name = "idx_driver", columnList = "driverId"),
                @Index(name = "idx_status", columnList = "status")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long driverId;

    private Double pickupLatitude;

    private Double pickupLongitude;

    private Double dropLatitude;

    private Double dropLongitude;

    @Enumerated(EnumType.STRING)
    private RideStatus status;

    private Double estimatedDistance;

    private Double estimatedFare;

    private LocalDateTime requestedAt;

    private LocalDateTime completedAt;
}
