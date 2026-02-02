import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public void printTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task deleteTask(int i) {
        return taskList.remove(i);
    }

    public void markDone(int i) {
        taskList.get(i).markDone();
    }

    public void markNotDone(int i) {
        taskList.get(i).markNotDone();
    }
}
