package auth_service.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @GetMapping("/user/profile")
    public String profile(Authentication auth) {
        return "Logged in user: " + auth.getName();
    }
}