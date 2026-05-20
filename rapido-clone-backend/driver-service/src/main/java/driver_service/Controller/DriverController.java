package driver_service.Controller;

import driver_service.dto.ApiResponse;
import driver_service.dto.DriverProfileDTO;
import driver_service.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<DriverProfileDTO>
    getDriverProfile(
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                driverService.getProfile(
                        authentication.getName()
                )
        );
    }

    @PutMapping("/availability")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<ApiResponse>
    updateAvailability(
            @RequestParam Boolean available,
            Authentication authentication
    ) {

        driverService.updateAvailability(
                authentication.getName(),
                available
        );

        return ResponseEntity.ok(
                new ApiResponse(
                        "Availability Updated"
                )
        );
    }

    @PutMapping("/online-status")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<ApiResponse>
    updateOnlineStatus(
            @RequestParam Boolean online,
            Authentication authentication
    ) {

        driverService.updateOnlineStatus(
                authentication.getName(),
                online
        );

        return ResponseEntity.ok(
                new ApiResponse(
                        "Online Status Updated"
                )
        );
    }

    @PutMapping("/location")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<ApiResponse>
    updateLocation(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            Authentication authentication
    ) {

        driverService.updateLocation(
                authentication.getName(),
                latitude,
                longitude
        );

        return ResponseEntity.ok(
                new ApiResponse(
                        "Location Updated"
                )
        );
    }
}
