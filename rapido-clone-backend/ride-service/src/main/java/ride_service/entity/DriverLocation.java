package ride_service.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        indexes = {
                @Index(columnList = "rideId"),
                @Index(columnList = "driverId")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long rideId;

    private Long driverId;

    private Double latitude;

    private Double longitude;
}
