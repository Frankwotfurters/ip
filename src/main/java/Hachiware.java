import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Hachiware {
    /* Main logic handler for all commands.
    Receives a command string and activates its respective handler.
    Input: command as a string, current taskList
    */
    public static void parseCommand(String command, List<Task> taskList) throws InvalidFormat, UnknownCommand {
        // Parse command type
        String[] tokens = command.split(" ");
        CommandType type = CommandType.from(tokens[0]);
        switch (type) {
            case BYE -> {
                System.out.println("Bye then!");
            }
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
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = Storage.fetchSavedTasks();

        System.out.println("----------------------------");
        System.out.println("Woooi! I'm Hachiware!");
        System.out.println("What can I help you with?");
        System.out.println("----------------------------");

        while (true) {
            // Receive command
            System.out.print("Input > ");
            String command = sc.nextLine();

            if (command.equals("bye")) {
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

            System.out.println("----------------------------");
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
            String[] parts = command.split("deadline | /by ", 3);
            String desc = parts[1].trim();
            String by = parts[2].trim();
            Task task = new Deadline(desc, by);
            taskList.add(task);
            Storage.storeTasks(taskList);

            System.out.println("Added: " + task);
            System.out.println("Now there are " + taskList.size() + " tasks!");
        } catch (Exception e) {
            throw new InvalidFormat();
        }
    }
    
    private static void handleEvent(String command, List<Task> taskList) throws InvalidFormat {
        try {
            String[] parts = command.split("event | /from | /to ", 4);
            String desc = parts[1].trim();
            String from = parts[2].trim();
            String to = parts[3].trim();
            Task task = new Event(desc, from, to);
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
