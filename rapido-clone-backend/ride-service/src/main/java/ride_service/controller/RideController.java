package ride_service.controller;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ride_service.dto.RideRequestDTO;
import ride_service.service.RideService;

@RestController
@RequestMapping("/rides")
@RequiredArgsConstructor
public class RideController {
    private final RideService rideService;
    @PostMapping("/request")
    public ResponseEntity<?> requestRide(
            @Valid @RequestBody RideRequestDTO dto,
            Authentication authentication) {

        return ResponseEntity.ok(
                rideService.requestRide(
                        authentication.getName(),
                        dto
                )
        );
    }
    @PutMapping("/{rideId}/accept")
    public ResponseEntity<?> acceptRide(
            @PathVariable Long rideId,
            Authentication authentication) {

        return ResponseEntity.ok(
                rideService.acceptRide(
                        rideId,
                        authentication.getName()
                )
        );
    }
    @PutMapping("/{rideId}/start")
    public ResponseEntity<?> startRide(
            @PathVariable Long rideId,
            Authentication authentication) {

        return ResponseEntity.ok(
                rideService.startRide(
                        rideId,
                        authentication.getName()
                )
        );
    }

    @PutMapping("/{rideId}/complete")
    public ResponseEntity<?> completeRide(
            @PathVariable Long rideId,
            Authentication authentication) {

        return ResponseEntity.ok(
                rideService.completeRide(
                        rideId,
                        authentication.getName()
                )
        );
    }

    @PutMapping("/{rideId}/cancel")
    public ResponseEntity<?> cancelRide(
            @PathVariable Long rideId,
            Authentication authentication) {

        return ResponseEntity.ok(
                rideService.cancelRide(
                        rideId,
                        authentication.getName()
                )
        );
    }
    @GetMapping("/history")
    public ResponseEntity<?> history(
            Pageable pageable,
            Authentication authentication) {

        return ResponseEntity.ok(
                rideService.getHistory(
                        authentication.getName(),
                        pageable
                )
        );
    }
}
