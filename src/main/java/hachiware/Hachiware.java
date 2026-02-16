package hachiware;

import java.net.Inet4Address;

import exception.CannotUndo;
import exception.IndexNotFound;
import exception.InvalidFormat;
import exception.UnknownCommand;
import task.TaskList;

/**
 * Main chatbot class which can be instantiated
 * to run and receive commands
 */
public class Hachiware {
    protected TaskList prevTaskList;
    protected TaskList taskList;

    public Hachiware() {
        this.prevTaskList = new TaskList();
        this.taskList = Storage.fetchSavedTasks();
    }

    /**
     * Start running this Hachiware instance
     */
    public void run() {
        while (true) {
            // Receive command
            String command = Ui.promptInput();

            // Exit command
            if (command.equals("bye")) {
                Ui.printExitMessage();
                break;
            }

            // Parse command
            try {
                Parser.parseCommand(command, taskList, prevTaskList);
            } catch (InvalidFormat | UnknownCommand | CannotUndo e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("☹️ Something went wrong: " + e.getMessage());
            }

            Ui.printBar();
        }
    }

    /**
     * Handler to receive commands from GUI 
     * @param command
     * @return command output
     */
    public String takeSingleCommand(String command) {
        try {
            return Parser.parseCommand(command, this.taskList, this.prevTaskList);
        }
        catch (InvalidFormat | UnknownCommand | IndexNotFound | CannotUndo e) {
            return e.getMessage();
        }
    }

    /**
     * Main method to start up the bot
     * @param args
     */
    public static void main(String[] args) {
        Hachiware hachiware = new Hachiware();

        Ui.printWelcomeMessage();

        hachiware.run();
    }
}


