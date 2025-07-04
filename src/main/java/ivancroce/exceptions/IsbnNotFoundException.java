package ivancroce.exceptions;

public class IsbnNotFoundException extends RuntimeException {
    public IsbnNotFoundException(String isbn) {
        super("Item with ISBN '" + isbn + "' was not found.");
    }
}
