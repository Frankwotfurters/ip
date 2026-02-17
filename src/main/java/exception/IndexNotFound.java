package exception;

/**
 * Exception thrown when trying to access an index of a TaskList that does not exist
 */
public class IndexNotFound extends Exception {
    public IndexNotFound() {
        super("No task found at the specified index!");
    }
    
    public IndexNotFound(int index) {
        super("No task found at index " + index + "!");
    }

    public IndexNotFound(String message) {
        super(message);
    }
}
