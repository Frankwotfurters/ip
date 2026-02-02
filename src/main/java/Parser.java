import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /* Main logic handler for all commands.
    Receives a command string and activates its respective handler.
    Input: command as a string, current taskList
    */
    public static void parseCommand(String command, TaskList taskList) throws InvalidFormat, UnknownCommand {
        // Parse command type
        String[] tokens = command.split(" ");
        CommandType type = CommandType.from(tokens[0]);
        switch (type) {
            case LIST -> printTasks(taskList);
            case MARK -> handleMark(tokens, taskList);
            case UNMARK -> handleUnmark(tokens, taskList);
            case DELETE -> handleDelete(tokens, taskList);
            case TODO -> handleTodo(command, taskList);
            case DEADLINE -> handleDeadline(command, taskList);
            case EVENT -> handleEvent(command, taskList);
        }
    }

        // Command Handlers Start
    private static void handleMark(String[] tokens, TaskList taskList) throws InvalidFormat {
        if (tokens.length != 2) throw new InvalidFormat();
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidFormat();
        }
        if (index < 0 || index >= taskList.getSize()) {
            throw new InvalidFormat();
        }
        
        Task tempTask = taskList.getTask(index);
        tempTask.markDone();
        Storage.storeTasks(taskList);

        System.out.println("Yay! I've ticked off this task:");
        System.out.println(tempTask);
    }
    
    private static void handleUnmark(String[] tokens, TaskList taskList) throws InvalidFormat {
        if (tokens.length != 2) throw new InvalidFormat();
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidFormat();
        }
        if (index < 0 || index >= taskList.getSize()) {
            throw new InvalidFormat();
        }
        
        Task tempTask = taskList.getTask(index);
        tempTask.markNotDone();
        Storage.storeTasks(taskList);

        System.out.println("Oops! I've unmarked this task:");
        System.out.println(tempTask);
    }
    
    private static void handleDelete(String[] tokens, TaskList taskList) throws InvalidFormat {
        if (tokens.length != 2) throw new InvalidFormat();
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidFormat();
        }
        if (index < 0 || index >= taskList.getSize()) {
            throw new InvalidFormat();
        }
        
        Task tempTask = taskList.deleteTask(index);
        Storage.storeTasks(taskList);

        System.out.println("Okay! Deleting this task:");
        System.out.println(tempTask);
    }
    
    private static void handleTodo(String command, TaskList taskList) throws InvalidFormat {
        try {
            String desc = command.split("todo ", 2)[1].trim();
            if (desc.isEmpty()) throw new InvalidFormat();
            Task task = new Todo(desc);
            taskList.addTask(task);
            Storage.storeTasks(taskList);

            System.out.println("Added: " + task);
            System.out.println("Now there are " + taskList.getSize() + " tasks!");
        } catch (Exception e) {
            throw new InvalidFormat();
        }
    }
    
    private static void handleDeadline(String command, TaskList taskList) throws InvalidFormat {
        try {
            // Parse tokens
            String[] parts = command.split("deadline | /by ", 3);
            String desc = parts[1].trim();
            String by = parts[2].trim();

            // Convert 'by' to LocalDateTime object
            LocalDateTime dateTime = LocalDateTime.parse(by, DATE_TIME_INPUT_FORMATTER);

            // Create the Task object
            Task task = new Deadline(desc, dateTime);
            taskList.addTask(task);
            Storage.storeTasks(taskList);

            System.out.println("Added: " + task);
            System.out.println("Now there are " + taskList.getSize() + " tasks!");
        } catch (Exception e) {
            throw new InvalidFormat(e.toString());
        }
    }
    
    private static void handleEvent(String command, TaskList taskList) throws InvalidFormat {
        try {
            // Parse tokens
            String[] parts = command.split("event | /from | /to ", 4);
            String desc = parts[1].trim();
            String from = parts[2].trim();
            String to = parts[3].trim();

            // Convert times to LocalDateTime objects
            LocalDateTime start = LocalDateTime.parse(from, DATE_TIME_INPUT_FORMATTER);
            LocalDateTime end = LocalDateTime.parse(to, DATE_TIME_INPUT_FORMATTER);

            // Create the Task object
            Task task = new Event(desc, start, end);
            taskList.addTask(task);
            Storage.storeTasks(taskList);
            
            System.out.println("Added: " + task);
            System.out.println("Now there are " + taskList.getSize() + " tasks!");
        } catch (Exception e) {
            throw new InvalidFormat();
        }
    }
    
    public static void printTasks(TaskList taskList) {
        taskList.printTasks();
    }
    // Command Handlers End
}
