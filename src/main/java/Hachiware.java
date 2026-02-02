import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hachiware {
    private static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /* Main logic handler for all commands.
    Receives a command string and activates its respective handler.
    Input: command as a string, current taskList
    */
    public static void parseCommand(String command, List<Task> taskList) throws InvalidFormat, UnknownCommand {
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
    public static void main(String[] args) {
        List<Task> taskList = Storage.fetchSavedTasks();

        Ui.printWelcomeMessage();

        while (true) {
            // Receive command
            String command = Ui.promptInput();

            if (command.equals("bye")) {
                Ui.printExitMessage();
                break;
            }

            try {
                parseCommand(command, taskList);
            } catch (InvalidFormat e) {
                System.out.println(e.getMessage());
            } catch (UnknownCommand e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("☹️ Something went wrong: " + e.getMessage());
            }

            Ui.printBar();
        }
    }

    // Command Handlers Start
    private static void handleMark(String[] tokens, List<Task> taskList) throws InvalidFormat {
        if (tokens.length != 2) throw new InvalidFormat();
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidFormat();
        }
        if (index < 0 || index >= taskList.size()) throw new InvalidFormat();
        
        Task tempTask = taskList.get(index);
        tempTask.markDone();
        Storage.storeTasks(taskList);

        System.out.println("Yay! I've ticked off this task:");
        System.out.println(tempTask);
    }
    
    private static void handleUnmark(String[] tokens, List<Task> taskList) throws InvalidFormat {
        if (tokens.length != 2) throw new InvalidFormat();
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidFormat();
        }
        if (index < 0 || index >= taskList.size()) throw new InvalidFormat();
        
        Task tempTask = taskList.get(index);
        tempTask.markNotDone();
        Storage.storeTasks(taskList);

        System.out.println("Oops! I've unmarked this task:");
        System.out.println(tempTask);
    }
    
    private static void handleDelete(String[] tokens, List<Task> taskList) throws InvalidFormat {
        if (tokens.length != 2) throw new InvalidFormat();
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidFormat();
        }
        if (index < 0 || index >= taskList.size()) throw new InvalidFormat();
        
        Task tempTask = taskList.remove(index);
        Storage.storeTasks(taskList);

        System.out.println("Okay! Deleting this task:");
        System.out.println(tempTask);
    }
    
    private static void handleTodo(String command, List<Task> taskList) throws InvalidFormat {
        try {
            String desc = command.split("todo ", 2)[1].trim();
            if (desc.isEmpty()) throw new InvalidFormat();
            Task task = new Todo(desc);
            taskList.add(task);
            Storage.storeTasks(taskList);

            System.out.println("Added: " + task);
            System.out.println("Now there are " + taskList.size() + " tasks!");
        } catch (Exception e) {
            throw new InvalidFormat();
        }
    }
    
    private static void handleDeadline(String command, List<Task> taskList) throws InvalidFormat {
        try {
            // Parse tokens
            String[] parts = command.split("deadline | /by ", 3);
            String desc = parts[1].trim();
            String by = parts[2].trim();
            System.out.println(by);

            // Convert 'by' to LocalDateTime object

            LocalDateTime dateTime = LocalDateTime.parse(by, DATE_TIME_INPUT_FORMATTER);

            // Create the Task object
            Task task = new Deadline(desc, dateTime);
            taskList.add(task);
            Storage.storeTasks(taskList);

            System.out.println("Added: " + task);
            System.out.println("Now there are " + taskList.size() + " tasks!");
        } catch (Exception e) {
            throw new InvalidFormat(e.toString());
        }
    }
    
    private static void handleEvent(String command, List<Task> taskList) throws InvalidFormat {
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
            taskList.add(task);
            Storage.storeTasks(taskList);
            
            System.out.println("Added: " + task);
            System.out.println("Now there are " + taskList.size() + " tasks!");
        } catch (Exception e) {
            throw new InvalidFormat();
        }
    }
    
    public static void printTasks(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
    }
    // Command Handlers End
}
