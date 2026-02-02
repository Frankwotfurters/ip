import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final static String STORAGE_FILE_PATH = "tasks.txt";

    /*
    Initializes the file where Tasks are saved.
    Only called when it does not exist yet.
     */
    public static void initStorage() {
        File file = new File(STORAGE_FILE_PATH);

        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully: " + file.getName());
            } else {
                System.out.println("File already exists: " + file.getName());
            }
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }

    /*
    Receives a taskList and saves it to STORAGE_FILE_PATH by serializing the list of Task objects
    */
    public static void storeTasks(List<Task> taskList) {
        try (FileOutputStream fos = new FileOutputStream(STORAGE_FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeObject(taskList);
            System.out.println("Object successfully saved to " + STORAGE_FILE_PATH);

        } catch (IOException e) {
            System.err.println("Error saving object: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /*
    Seeks the data file at STORAGE_FILE_PATH and restores it to a List<Task> via unserialization
    */
    public static List<Task> fetchSavedTasks() {
        List<Task> taskList = null;
        try (FileInputStream fis = new FileInputStream(STORAGE_FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            //TODO: handle corrupted file data
            taskList = (List<Task>) ois.readObject();
            System.out.println("Object successfully loaded from " + STORAGE_FILE_PATH);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading saved tasks: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<Task>();
        }
        return taskList;
    }
}