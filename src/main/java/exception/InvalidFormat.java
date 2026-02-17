package exception;

/**
 * Exception thrown when a command used does not follow the correct format
 */
public class InvalidFormat extends Exception {
    public InvalidFormat() {
        super("Wrong format!");
    }

    public InvalidFormat(String message) {
        super(message);
    }
}
