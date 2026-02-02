package hachiware;
import java.util.Scanner;

public class Ui {
    protected static Scanner sc = new Scanner(System.in);

    /*
    Prints the welcome message upon running the bot
    */
    public static void printWelcomeMessage() {
        System.out.println("----------------------------");
        System.out.println("Woooi! I'm Hachiware!");
        System.out.println("What can I help you with?");
        System.out.println("----------------------------");
    }

    /*
    Prints the input message and waits for a command
    */
    public static String promptInput() {
        System.out.print("Input > ");
        String command = sc.nextLine();
        return command;
    }

    /*
    Prints a formatting bar
    */
    public static void printBar() {
        System.out.println("----------------------------");
    }

    /*
    Prints the exit message
    */
    public static void printExitMessage() {
        System.out.println("Bye then!");
    }
}