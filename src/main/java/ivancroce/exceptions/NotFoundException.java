package ivancroce.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("ID '" + id + "' was not found.");
    }
}
