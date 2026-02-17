package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents an Event, which has a /from and /to field
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Used to create a new DeadLine with the same fields
     */
    public Event deepCopy() {
        return new Event(this.description, this.isDone, this.from, this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + " to: " + to.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")";
    }
}
