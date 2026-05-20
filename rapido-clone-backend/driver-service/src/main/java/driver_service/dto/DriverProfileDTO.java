package driver_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DriverProfileDTO {

    @NotBlank
    private String fullName;

    @Pattern(
            regexp = "^[6-9]\\d{9}$"
    )
    private String phone;

    @NotBlank
    private String vehicleNumber;

    @NotBlank
    private String vehicleModel;

    @NotBlank
    private String vehicleType;
}
