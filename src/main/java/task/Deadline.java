package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents a Deadline, which has a /by field
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Used to create a new DeadLine with the same fields
     */
    public Deadline deepCopy() {
        return new Deadline(this.description, this.isDone, this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
            + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")";
    }
}
