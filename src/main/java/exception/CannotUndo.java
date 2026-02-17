package exception;

public class CannotUndo extends Exception {
    public CannotUndo() {
        super("No appropriate command to undo!");
    }

    public CannotUndo(String message) {
        super(message);
    }
}
