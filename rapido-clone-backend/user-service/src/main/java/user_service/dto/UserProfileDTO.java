package user_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserProfileDTO {

    @NotBlank
    private String fullName;

    private String email;

    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid phone number"
    )
    private String phone;

    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "Invalid image URL"
    )
    private String profileImage;
}