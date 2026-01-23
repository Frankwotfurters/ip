import java.util.*;

public class Hachiware {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> taskList = new ArrayList<>();
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
            else {
                taskList.add(command);
                System.out.println("Added: " + command);
            }
        }
        
        System.out.println("Bye then!");
    }

    public static void printTasks(List taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + ". " + taskList.get(i));
        }
    }
}
