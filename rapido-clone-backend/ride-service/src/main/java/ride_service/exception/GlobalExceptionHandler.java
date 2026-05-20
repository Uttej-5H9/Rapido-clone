package ride_service.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoDriversAvailableException.class)
    public ResponseEntity<?> handleNoDrivers(
            NoDriversAvailableException ex) {

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidRideStateException.class)
    public ResponseEntity<?> handleInvalidState(
            InvalidRideStateException ex) {

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateRideException.class)
    public ResponseEntity<?> handleDuplicateRide(
            DuplicateRideException ex) {

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
}
