package user_service.dto;


import lombok.Data;

@Data
public class AuthRequest {
    private String fullName;
    private String email;
    private String password;
    private String phone;
}
