package hachiware;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import exception.CannotUndo;
import exception.InvalidFormat;
import exception.UnknownCommand;
import exception.IndexNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskList;
import task.Todo;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private TaskList taskList;
    private TaskList prevTaskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        prevTaskList = new TaskList();
    }

    @Test
    void parseCommand_list_returnsPrintTasks() throws Exception {
        taskList.addTask(new Todo("read book"));
        String res = Parser.parseCommand("list", taskList, prevTaskList);

        // Parser delegates to taskList.printTasks()
        assertEquals(taskList.printTasks(), res);
    }

    @Test
    void parseCommand_todo_emptyDescription_throwsInvalidFormat() {
        assertThrows(InvalidFormat.class, () ->
                Parser.parseCommand("todo   ", taskList, prevTaskList)
        );
    }

    @Test
    void parseCommand_todo_addsTask_andSnapshotsPrev() throws Exception {
        taskList.addTask(new Todo("existing"));

        int beforeSize = taskList.getSize();
        String res = Parser.parseCommand("todo new task", taskList, prevTaskList);

        assertEquals(beforeSize + 1, taskList.getSize(), "Todo should be added to taskList");
        assertEquals(beforeSize, prevTaskList.getSize(), "prevTaskList should snapshot before mutation");

        // Optional: sanity check that response isn't empty
        assertNotNull(res);
        assertFalse(res.isBlank());
    }

    @Test
    void parseCommand_mark_invalidIndex_throwsInvalidFormat() throws Exception {
        taskList.addTask(new Todo("t1"));

        // Index 2 is out of range when only 1 item exists
        assertThrows(InvalidFormat.class, () ->
                Parser.parseCommand("mark 2", taskList, prevTaskList)
        );
    }

    @Test
    void parseCommand_mark_marksTaskDone() throws Exception {
        taskList.addTask(new Todo("t1"));
        Task t = taskList.getTask(0);

        Parser.parseCommand("mark 1", taskList, prevTaskList);

        // Use equals() (not ==) for strings
        assertEquals("X", t.getStatusIcon());
        assertEquals(1, prevTaskList.getSize(), "prevTaskList should snapshot before mutation");
    }

    @Test
    void parseCommand_unmark_unmarksTask() throws Exception {
        taskList.addTask(new Todo("t1"));
        Task t = taskList.getTask(0);

        Parser.parseCommand("mark 1", taskList, prevTaskList);   // mark first
        Parser.parseCommand("unmark 1", taskList, prevTaskList); // then unmark

        assertEquals(" ", t.getStatusIcon());
    }

    @Test
    void parseCommand_delete_removesTask() throws Exception {
        taskList.addTask(new Todo("t1"));
        taskList.addTask(new Todo("t2"));

        int beforeSize = taskList.getSize();
        Parser.parseCommand("delete 1", taskList, prevTaskList);

        assertEquals(beforeSize - 1, taskList.getSize());
        assertEquals(beforeSize, prevTaskList.getSize(), "prevTaskList should snapshot before mutation");
    }

    @Test
    void parseCommand_deadline_missingBy_throwsInvalidFormat() {
        assertThrows(InvalidFormat.class, () ->
                Parser.parseCommand("deadline do thing", taskList, prevTaskList)
        );
    }

    @Test
    void parseCommand_deadline_badDate_throwsInvalidFormat() {
        assertThrows(InvalidFormat.class, () ->
                Parser.parseCommand("deadline do thing /by not-a-date", taskList, prevTaskList)
        );
    }

    @Test
    void parseCommand_event_badDate_throwsInvalidFormat() {
        assertThrows(InvalidFormat.class, () ->
                Parser.parseCommand("event party /from 01/01/2026 1200 /to bad", taskList, prevTaskList)
        );
    }

    @Test
    void parseCommand_find_invalidFormat_throwsInvalidFormat() {
        // Parser expects exactly "find <string>" in a very specific split
        assertThrows(InvalidFormat.class, () ->
                Parser.parseCommand("find", taskList, prevTaskList)
        );
    }

    @Test
    void parseCommand_undo_whenPrevEmpty_throwsCannotUndo() {
        assertThrows(CannotUndo.class, () ->
                Parser.parseCommand("undo", taskList, prevTaskList)
        );
    }

    @Test
    void parseCommand_undo_restoresPrevAndClearsPrev() throws Exception {
        // Current list has A; prev list has B (simulate a snapshot)
        taskList.addTask(new Todo("A"));
        prevTaskList.addTask(new Todo("B"));

        String res = Parser.parseCommand("undo", taskList, prevTaskList);

        assertNotNull(res);
        assertEquals(1, taskList.getSize(), "taskList should be replaced by prevTaskList");
        assertEquals(0, prevTaskList.getSize(), "prevTaskList should be cleared after undo");
    }

    @Test
    void parseCommand_unknownCommand_throwsUnknownCommand() {
        assertThrows(UnknownCommand.class, () ->
                Parser.parseCommand("nonsense", taskList, prevTaskList)
        );
    }
}
