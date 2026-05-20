package ride_service.exception;

public class NoDriversAvailableException
        extends RuntimeException {

    public NoDriversAvailableException(String message) {
        super(message);
    }
}
