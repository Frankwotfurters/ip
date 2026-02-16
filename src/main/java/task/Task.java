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

    /**
     * Get the icon representation of the task
     * @return X if task is done, " " if task not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Getter for task description
     * @return task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks this task as done
     * @return bot response message
     */
    public String markDone() {
        this.isDone = true;

        String res = "Yay! I've ticked off this task:\n" + this.toString();
        return res;
    }
    
    /**
     * Marks this task as not done
     * @return bot response message
     */
    public String markNotDone() {
        this.isDone = false;

        String res = "Oops! I've unmarked this task:\n" + this.toString();
        return res;
    }
    
    /**
     * Returns a new copy of this task
     * @return a new Task with the same fields
     */
    public Task deepCopy() {
        return new Task(this.description, this.isDone);
    }

    @Override
    public String toString() {
        return "[" + (this.isDone? "X":" ") + "] " + this.description;
    }
}
