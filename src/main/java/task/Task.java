package task;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public String markDone() {
        this.isDone = true;

        String res = "Yay! I've ticked off this task:\n" + this.toString();
        return res;
    }
    
    public String markNotDone() {
        this.isDone = false;

        String res = "Oops! I've unmarked this task:\n" + this.toString();
        return res;
    }
    
    public Task deepCopy() {
        return new Task(this.description, this.isDone);
    }

    @Override
    public String toString() {
        return "[" + (this.isDone? "X":" ") + "] " + this.description;
    }
}
