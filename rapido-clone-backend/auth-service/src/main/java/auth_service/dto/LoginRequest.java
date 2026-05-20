package auth_service.dto;


import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @Email
    private String email;

    @NotBlank
    private String password;
}
