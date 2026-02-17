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
    public static String printWelcomeMessage() {
        String res = """
                ----------------------------
                Woooi! I'm Hachiware!
                What can I help you with?
                ----------------------------
                """;
        System.out.println(res);

        return res;
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

    /**
     * Prints the no tasks found message
     * @return message to be sent to GUI
     */
    public static String printNotFoundMessage() {
        String res = "No tasks found!";
        // System.out.println(res);
        return res;
    }

    /**
     * Prints a formatting bar
     * @return message to be sent to GUI
     */
    public static String printBar() {
        String res = "----------------------------";
        System.out.println(res);
        return res;
    }

    /*
     * Prints the exit message
     * @return message to be sent to GUI
     */
    public static String printExitMessage() {
        String res = "Bye then!";
        System.out.println(res);
        return res;
    }
    
    /**
     * Prints the undo message
     * @return message to be sent to GUI
     */
    public static String printUndoMessage() {
        String res = "Undid the previous action!";
        // System.out.println(res);
        return res;
    }
}
