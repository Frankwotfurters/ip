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
    public void printTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
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
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Helper method to delete a task at a specific index
     * @param i index of Task to be deleted
     * @return Task that was deleted
     */
    public Task deleteTask(int i) {
        return taskList.remove(i);
    }

    /**
     * Helper method to mark a Task at index i as done
     * @param i index of Task to be marked as done
     */
    public void markDone(int i) {
        taskList.get(i).markDone();
    }

    /**
     * Helper method to unmark a Task at index i as done
     * @param i index of Task to be unmarked as done
     */
    public void markNotDone(int i) {
        taskList.get(i).markNotDone();
    }

    /**
     * Method to find Tasks in this TaskList matching a search string
     * and prints each one out
     * @param searchString search string to be queried
     */
    public void findTask(String searchString) {
        boolean isFound = false;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(searchString)) {
                isFound = true;
                System.out.println((i + 1) + "." + taskList.get(i));
            }
        }   

        // no matching tasks found
        if (!isFound) {
            Ui.printNotFoundMessage();
        }
    }
}
