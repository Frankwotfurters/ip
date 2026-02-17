package hachiware;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import task.TaskList;
import task.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @TempDir
    Path tempDir;

    private String originalUserDir;

    @BeforeEach
    void setUp() throws IOException {
        // Make Storage read/write tasks.txt inside a temp folder instead of your project folder
        originalUserDir = System.getProperty("user.dir");
        System.setProperty("user.dir", tempDir.toString());

        // Ensure there's no leftover tasks file in the temp folder
        Files.deleteIfExists(tempDir.resolve(Storage.STORAGE_FILE_PATH));
    }

    @AfterEach
    void tearDown() {
        // Restore original working directory
        System.setProperty("user.dir", originalUserDir);
    }

    @Test
    void fetchSavedTasks_whenFileDoesNotExist_returnsEmptyTaskList() {
        TaskList loaded = Storage.fetchSavedTasks();

        assertNotNull(loaded);
        assertEquals(0, loaded.getSize(), "Expected an empty TaskList when no save file exists");
    }

    @Test
    void storeTasks_thenFetchSavedTasks_restoresSameNumberOfTasks() {
        TaskList toSave = new TaskList();
        toSave.addTask(new Todo("read book"));
        toSave.addTask(new Todo("wash dishes"));

        Storage.storeTasks(toSave);

        TaskList loaded = Storage.fetchSavedTasks();

        assertNotNull(loaded);
        assertEquals(2, loaded.getSize(), "Expected the same number of tasks after reload");

        // Optional: verify task content (depends on your Task/TaskList implementation)
        assertTrue(loaded.getTask(0).toString().contains("read book"));
        assertTrue(loaded.getTask(1).toString().contains("wash dishes"));
    }

    @Test
    void fetchSavedTasks_whenFileIsCorrupted_returnsEmptyTaskList() throws IOException {
        // Write junk bytes into tasks.txt so ObjectInputStream can't deserialize it
        Path file = tempDir.resolve(Storage.STORAGE_FILE_PATH);
        Files.write(file, "not a serialized object".getBytes());

        TaskList loaded = Storage.fetchSavedTasks();

        assertNotNull(loaded);
        assertEquals(0, loaded.getSize(), "Expected an empty TaskList when save file is corrupted");
    }

    @Test
    void storeTasks_withNull_doesNotThrow_andFetchReturnsEmptyList() {
        assertDoesNotThrow(() -> Storage.storeTasks(null));

        TaskList loaded = Storage.fetchSavedTasks();
        assertNotNull(loaded);
        assertEquals(0, loaded.getSize(), "Expected empty TaskList if stored object is null / unreadable");
    }
}
