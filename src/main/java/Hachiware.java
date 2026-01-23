import java.util.*;

public class Hachiware {
    public static void main(String[] args) throws UnknownCommand, InvalidFormat {
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();
        System.out.println("----------------------------");
        System.out.println("Woooi! I'm Hachiware!");
        System.out.println("What can I help you with?");
        System.out.println("----------------------------");
        
        while (true) {
            System.out.print("Input > ");
            String command = sc.nextLine();
            
            try {
                if (command.equals("bye")) {
                    break;
                }
                else if (command.equals("list")) {
                    printTasks(taskList);
                }
                else if (command.split(" ")[0].equals("mark")) {

                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task tempTask = taskList.get(index);
                    tempTask.markDone();
                    taskList.set(index, tempTask);
                    // throw (new InvalidFormat());

                    System.out.println("Yay! I've ticked off this task:");
                    System.out.println(tempTask.toString());
                }
                else if (command.split(" ")[0].equals("unmark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task tempTask = taskList.get(index);
                    tempTask.markNotDone();
                    taskList.set(index, tempTask);

                    System.out.println("Oops! I've unmarked this task:");
                    System.out.println(tempTask.toString());
                }
                else if (command.split(" ")[0].equals("delete")) {
                    // TODO: add invalid formatting error for this and marks
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task tempTask = taskList.get(index);

                    taskList.remove(index);

                    System.out.println("Okay! Deleting this task:");
                    System.out.println(tempTask.toString());
                }
                else if (command.split(" ")[0].equals("todo")) {
                    String[] parts;
                    String desc;
                    try {
                        parts = command.split("todo ");
                        desc = parts[1];
                    }
                    catch (Exception e) {
                        throw (new InvalidFormat());
                    }

                    Task task = new Todo(desc);
                    taskList.add(task);
                    System.out.println("Added: " + task.toString());
                    System.out.println("Now there are " + taskList.size() + " tasks!");
                }
                else if (command.split(" ")[0].equals("deadline")) {
                    String[] parts;
                    String desc;
                    String by;

                    try {
                        parts = command.split("deadline | /by ");
                        desc = parts[1];
                        by = parts[2];
                    }
                    catch (Exception e) {
                        throw (new InvalidFormat());
                    }
                    
                    Task task = new Deadline(desc, by);
                    taskList.add(task);
                    System.out.println("Added: " + task.toString());
                    System.out.println("Now there are " + taskList.size() + " tasks!");
                }
                else if (command.split(" ")[0].equals("event")) {
                    String[] parts;
                    String desc;
                    String from;
                    String to;
                    try {
                        parts = command.split("event | /from | /to ");
                        desc = parts[1];
                        from = parts[2];
                        to = parts[3];
                    }
                    catch (Exception e) {
                        throw (new InvalidFormat());
                    }
                    
                    Task task = new Event(desc, from, to);
                    taskList.add(task);
                    System.out.println("Added: " + task.toString());
                    System.out.println("Now there are " + taskList.size() + " tasks!");
                }
                else {
                    throw new UnknownCommand();
                }
            }
            catch (InvalidFormat e) {
                System.out.println(e.getMessage());
            }
            catch (UnknownCommand e) {
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("----------------------------");
        }
        
        System.out.println("Bye then!");
        sc.close();
    }

    public static void printTasks(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + "." + taskList.get(i).toString());
        }
    }
}
