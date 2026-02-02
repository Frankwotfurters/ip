package hachiware;
import task.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class StorageTest {
    @Test
    public void fetchSavedTasks_fileNotExist_emptyTaskList() {
        try {
            Path filePath = Paths.get(Storage.STORAGE_FILE_PATH);
            Path fileDestination = Paths.get(Storage.STORAGE_FILE_PATH + ".bk");
            Files.move(filePath, fileDestination, REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(Storage.fetchSavedTasks().getSize(), 0);
    }
}
