package hachiware;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidFormat;
import exception.UnknownCommand;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 * Class to handle parsing of user commands
 * and calling of respective handler methods
 */
public class Parser {
    private static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Main logic handler for all commands.
     * @param command command string to be parsed
     * @param taskList TaskList object containing current tasks
     * @throws InvalidFormat if proper syntax of command is not used
     * @throws UnknownCommand if command is not recognized
     */
    public static String parseCommand(String command, TaskList taskList) throws InvalidFormat, UnknownCommand {
        // Parse command type (first word)
        String[] tokens = command.split(" ", 2); // split only on first space
        CommandType type = CommandType.from(tokens[0].toUpperCase());
        String response = "";

        switch (type) {
        case LIST:
            response = printTasks(taskList);
            break;
        case MARK:
            response = handleMark(tokens, taskList);
            break;
        case UNMARK:
            response = handleUnmark(tokens, taskList);
            break;
        case DELETE:
            response = handleDelete(tokens, taskList);
            break;
        case TODO:
            response = handleTodo(tokens, taskList);
            break;
        case DEADLINE:
            response = handleDeadline(tokens, taskList);
            break;
        case EVENT:
            response = handleEvent(tokens, taskList);
            break;
        case FIND:
            response = handleFind(command, taskList);
            break;
        default:
            response = new UnknownCommand().getMessage();
        }

        System.out.println(response);
        return response;
    }

    // ---------------- Command Handlers Start ----------------

    /**
     * Handles marking a task as done.
     */
    private static String handleMark(String[] tokens, TaskList taskList) throws InvalidFormat {
        int index = parseIndex(tokens, taskList);

        Task tempTask = taskList.getTask(index);
        String res = tempTask.markDone();
        Storage.storeTasks(taskList);

        return res;
    }

    /**
     * Handles unmarking a task.
     */
    private static String handleUnmark(String[] tokens, TaskList taskList) throws InvalidFormat {
        int index = parseIndex(tokens, taskList);

        Task tempTask = taskList.getTask(index);
        String res = tempTask.markNotDone();
        Storage.storeTasks(taskList);

        return res;
    }

    /**
     * Handles deleting a task.
     */
    private static String handleDelete(String[] tokens, TaskList taskList) throws InvalidFormat {
        int index = parseIndex(tokens, taskList);

        String res = taskList.deleteTask(index);
        Storage.storeTasks(taskList);
    
        return res;
    }

    /**
     * Handles creating a new Todo task.
     */
    private static String handleTodo(String[] tokens, TaskList taskList) throws InvalidFormat {
        // Ensure description exists
        if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
            throw new InvalidFormat("Todo description cannot be empty!");
        }

        Task task = new Todo(tokens[1].trim());
        String res = taskList.addTask(task);
        Storage.storeTasks(taskList);

        return res;
    }

    /**
     * Handles creating a new Deadline task.
     */
    private static String handleDeadline(String[] tokens, TaskList taskList) throws InvalidFormat {
        if (tokens.length < 2) {
            throw new InvalidFormat("Invalid format! Use: deadline [desc] /by [DD/MM/YYYY HHMM]");
        }

        // Parse tokens
        String[] parts = tokens[1].split(" /by ", 2);
        if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new InvalidFormat("Invalid format! Use: deadline [desc] /by [DD/MM/YYYY HHMM]");
        }

        try {
            // Convert 'by' to LocalDateTime object
            LocalDateTime dateTime = LocalDateTime.parse(parts[1].trim(), DATE_TIME_INPUT_FORMATTER);

            // Create the Task object
            Task task = new Deadline(parts[0].trim(), dateTime);
            String res = taskList.addTask(task);
            Storage.storeTasks(taskList);

            return res;

        } catch (DateTimeParseException e) {
            throw new InvalidFormat("Invalid date/time format! Use DD/MM/YYYY HHMM");
        }
    }

    /**
     * Handles creating a new Event task.
     */
    private static String handleEvent(String[] tokens, TaskList taskList) throws InvalidFormat {
        if (tokens.length < 2) {
            throw new InvalidFormat("Invalid format! Use: event [desc] /from [DD/MM/YYYY HHMM] /to [DD/MM/YYYY HHMM]");
        }

        // Parse tokens
        String[] parts = tokens[1].split(" /from | /to ", 3);
        if (parts.length != 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new InvalidFormat("Invalid format! Use: event [desc] /from [DD/MM/YYYY HHMM] /to [DD/MM/YYYY HHMM]");
        }

        try {
            // Convert times to LocalDateTime objects
            LocalDateTime start = LocalDateTime.parse(parts[1].trim(), DATE_TIME_INPUT_FORMATTER);
            LocalDateTime end = LocalDateTime.parse(parts[2].trim(), DATE_TIME_INPUT_FORMATTER);

            // Create the Task object
            Task task = new Event(parts[0].trim(), start, end);
            String res = taskList.addTask(task);
            Storage.storeTasks(taskList);

            return res;


        } catch (DateTimeParseException e) {
            throw new InvalidFormat("Invalid date/time format! Use DD/MM/YYYY HHMM");
        }
    }

    /**
     * Parses task index from command tokens and checks bounds.
     * @throws InvalidFormat if the index is missing, non-numeric, or out of range
     */
    private static int parseIndex(String[] tokens, TaskList taskList) throws InvalidFormat {
        if (tokens.length != 2) {
            throw new InvalidFormat("Task index missing!");
        }
        try {
            int index = Integer.parseInt(tokens[1]) - 1;
            if (index < 0 || index >= taskList.getSize()) {
                throw new InvalidFormat("Task index out of range!");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new InvalidFormat("Task index must be a number!");
        }
    }

    /**
     * Prints all tasks in the TaskList
     */
    public static String printTasks(TaskList taskList) {
        return taskList.printTasks();
    }

    private static String handleFind(String command, TaskList taskList) {
        // Parse tokens
        String[] parts = command.split("find ", 4);
        String searchString = parts[1];

        return taskList.findTask(searchString);
    }

    // ---------------- Command Handlers End ----------------
}
