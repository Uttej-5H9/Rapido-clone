package user_service.service;


import user_service.dto.UserProfileDTO;

public interface UserService {

    UserProfileDTO getProfile(String email);

    void updateProfile(
            String email,
            UserProfileDTO dto
    );
}
