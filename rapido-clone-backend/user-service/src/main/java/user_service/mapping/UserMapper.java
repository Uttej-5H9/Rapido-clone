package user_service.mapping;


import user_service.dto.UserProfileDTO;
import user_service.entity.User;

public class UserMapper {

    public static UserProfileDTO toDTO(User user) {

        UserProfileDTO dto = new UserProfileDTO();

        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setProfileImage(user.getProfileImage());

        return dto;
    }
}