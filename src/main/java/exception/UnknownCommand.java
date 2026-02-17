package exception;

/**
 * Exception thrown when an unknown command is used
 */
public class UnknownCommand extends Exception {
    public UnknownCommand() {
        super("Unknown command!");
    }

    public UnknownCommand(String message) {
        super(message);
    }
}
