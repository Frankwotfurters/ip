package hachiware;
import java.util.Scanner;

/**
 * Ui class to handle all UI printing operations
 */
public class Ui {
    protected static Scanner sc = new Scanner(System.in);

    /**
     * Prints the welcome message upon running the bot
     */
    public static void printWelcomeMessage() {
        System.out.println("----------------------------");
        System.out.println("Woooi! I'm Hachiware!");
        System.out.println("What can I help you with?");
        System.out.println("----------------------------");
    }

    /**
     * Prints the input message and waits for a command
     * 
     * @return Command string entered by user
     */
    public static String promptInput() {
        System.out.print("Input > ");
        String command = sc.nextLine();
        return command;
    }

    /*
        * Prints the no tasks found message
        */
    public static void printNotFoundMessage() {
        System.out.println("No tasks found!");
    }

    /*
     * Prints a formatting bar
     */
    public static void printBar() {
        System.out.println("----------------------------");
    }

    /*
     * Prints the exit message
     */
    public static void printExitMessage() {
        System.out.println("Bye then!");
    }
}
