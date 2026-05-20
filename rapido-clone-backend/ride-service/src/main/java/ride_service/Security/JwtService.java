package ride_service.Security;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String extractUsername(String token) {
        return "testUser"; // mock
    }

    public boolean isValid(String token) {
        return true; // mock
    }
}