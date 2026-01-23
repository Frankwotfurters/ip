import java.util.*;

public class Hachiware {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
            else {
                System.out.println(command);
            }
        }
        
        System.out.println("Bye then!");
    }
}
