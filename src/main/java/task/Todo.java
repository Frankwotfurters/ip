package task;

/**
 * Class to represent a Todo Task
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Copies this Todo and returns a new Todo instance
     * @return a new Todo with the same fields as this
     */
    public Todo deepCopy() {
        return new Todo(this.description, this.isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
