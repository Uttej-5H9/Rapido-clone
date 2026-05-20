package ride_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RideRequestDTO {

    @NotNull
    private Double pickupLatitude;

    @NotNull
    private Double pickupLongitude;

    @NotNull
    private Double dropLatitude;

    @NotNull
    private Double dropLongitude;
}
