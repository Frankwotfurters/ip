package task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hachiware.Ui;

/**
 * Class to handle lists of Task objects.
 * Provides helper methods to interact with this list.
 */
public class TaskList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Get the number of tasks in this TaskList
     * @return number of tasks
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Prints each task's string representation
     */
    public String printTasks() {
        if (taskList.size() == 0) {
            return Ui.printNoTasksAddedMessage();
        }
        String res = "";
        for (int i = 0; i < taskList.size(); i++) {
            res += (i + 1) + "." + taskList.get(i) + "\n";
        }

        return res;
    }

    /**
     * Helper method to get a specific task out of the TaskList
     * @param i integer index in TaskList
     * @return Task at index i
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Helper method to add a task to the end of the TaskList
     * @param task to be added
     */
    public String addTask(Task task) {
        this.taskList.add(task);

        String res = "Added: " + task + "\nNow there are " + this.taskList.size() + " tasks!";
        return res;
    }

    /**
     * Helper method to delete a task at a specific index
     * @param i index of Task to be deleted
     * @return Task that was deleted
     */
    public String deleteTask(int i) {
        Task removedTask = taskList.remove(i);

        String res = "Okay! Deleting this task:\n" + removedTask;
        return res;
    }

    /**
     * Helper method to mark a Task at index i as done
     * @param i index of Task to be marked as done
     */
    public String markDone(int i) {
        return taskList.get(i).markDone();
    }

    /**
     * Helper method to unmark a Task at index i as done
     * @param i index of Task to be unmarked as done
     */
    public String markNotDone(int i) {
        return taskList.get(i).markNotDone();
    }

    /**
     * Method to find Tasks in this TaskList matching a search string
     * and prints each one out
     * @param searchString search string to be queried
     */
    public String findTask(String searchString) {
        boolean isFound = false;
        String res = "";

        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(searchString)) {
                isFound = true;
                res += (i + 1) + "." + taskList.get(i) + "\n";
            }
        }   

        // no matching tasks found
        if (!isFound) {
            return Ui.printNotFoundMessage();
        }
        return res;
    }

    /**
     * Duplicate the TaskList for snapshotting
     * @return
     */
    public TaskList deepCopy() {
        List<Task> copiedList = new ArrayList<>();

        for (Task t : this.taskList) {
            copiedList.add(t.deepCopy());
        }

        return new TaskList(copiedList);
    }

    /**
     * Replaces all fields of this taskList with the other input taskList
     * @param other taskList to replace this taskList
     */
    public void replaceWith(TaskList other) {
        this.taskList.clear();
        for (Task t : other.taskList) {
            this.taskList.add(t.deepCopy());
        }
    }
}
