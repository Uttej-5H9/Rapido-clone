package driver_service.entity;

import com.rapido.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "drivers",
        indexes = {
                @Index(name = "idx_driver_email", columnList = "email"),
                @Index(name = "idx_driver_available", columnList = "available"),
                @Index(name = "idx_driver_online", columnList = "online")
        }
)
public class Driver extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String phone;

    private String vehicleNumber;

    private String vehicleModel;

    private String vehicleType;

    private Boolean available = false;

    private Boolean online = false;

    private Double currentLatitude;

    private Double currentLongitude;
}