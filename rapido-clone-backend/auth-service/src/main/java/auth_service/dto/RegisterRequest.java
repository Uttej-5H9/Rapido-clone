package auth_service.dto;

import auth_service.entity.Role;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Size(min = 8)
    private String password;

    @NotBlank
    private String phone;

    @NotBlank
    private Role role;
}
