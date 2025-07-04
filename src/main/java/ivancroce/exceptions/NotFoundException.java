package ivancroce.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String id) {
        super("ID '" + id + "' was not found.");
    }
}
