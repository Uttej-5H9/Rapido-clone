package user_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import user_service.dto.UserProfileDTO;
import user_service.entity.User;
import user_service.exception.ResourceNotFoundException;
import user_service.mapping.UserMapper;
import user_service.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl
        implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserProfileDTO getProfile(String email) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));

        return UserMapper.toDTO(user);
    }

    @Override
    public void updateProfile(
            String email,
            UserProfileDTO dto
    ) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));

        user.setFullName(dto.getFullName());
        user.setPhone(dto.getPhone());
        user.setProfileImage(dto.getProfileImage());

        userRepository.save(user);

        log.info(
                "Profile updated for user: {}",
                email
        );
    }
}
