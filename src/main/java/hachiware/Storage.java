package hachiware;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import task.TaskList;


/**
 * Storage class used to handle reading and writing of Tasks to files
 */
public class Storage {
    public static final String STORAGE_FILE_PATH = "tasks.txt";

    /**
     * Receives a TaskList and saves it to STORAGE_FILE_PATH by serializing the list of Task objects
     * <p>
     * @param taskList TaskList to be stored
     */
    public static void storeTasks(TaskList taskList) {
        try (FileOutputStream fos = new FileOutputStream(STORAGE_FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeObject(taskList);
            // System.out.println("Object successfully saved to " + STORAGE_FILE_PATH);

        } catch (IOException e) {
            System.err.println("Error saving object: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Seeks the data file at STORAGE_FILE_PATH and restores it to a List<Task> via unserialization
     * <p>
     * @return TaskList object with the restored Tasks
     */
    public static TaskList fetchSavedTasks() {
        TaskList taskList = null;
        
        // Case 1, read from file
        try (FileInputStream fis = new FileInputStream(STORAGE_FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            //TODO: handle corrupted file data
            taskList = (TaskList) ois.readObject();
            System.out.println("Previous tasks successfully loaded from " + STORAGE_FILE_PATH + ":");
            taskList.printTasks();

        } catch (IOException | ClassNotFoundException e) {
            // Case 2, file not found/corrupted
            System.err.println("Error reading saved file: " + e.getMessage());
            return new TaskList();
        } catch (Exception e) {
            // Case 3, other errors
            System.err.println("Error loading saved tasks: " + e.getMessage());
            return new TaskList();
        }
        return taskList;
    }
}
