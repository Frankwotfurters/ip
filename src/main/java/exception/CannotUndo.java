package exception;

/**
 * Exception thrown when there is no valid action to undo
 */
public class CannotUndo extends Exception {
    public CannotUndo() {
        super("No appropriate command to undo!");
    }

    public CannotUndo(String message) {
        super(message);
    }
}
