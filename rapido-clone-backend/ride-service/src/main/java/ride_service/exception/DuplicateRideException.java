package ride_service.exception;

public class DuplicateRideException
        extends RuntimeException {

    public DuplicateRideException(String message) {
        super(message);
    }
}
