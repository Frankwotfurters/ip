import java.util.*;

public class Hachiware {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();
        System.out.println("----------------------------");
        System.out.println("Woooi! I'm Hachiware!");
        System.out.println("What can I help you with?");
        System.out.println("----------------------------");
        
        while (true) {
            System.out.print("Input > ");
            String command = sc.nextLine();
            
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
            else {
                taskList.add(new Task(command));
                System.out.println("Added: " + command);
            }
            System.out.println("----------------------------");
        }
        
        System.out.println("Bye then!");
    }

    public static void printTasks(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + "." + taskList.get(i).toString());
        }
    }
}
