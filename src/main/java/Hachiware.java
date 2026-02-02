public class Hachiware {
    protected TaskList taskList;

    public Hachiware() {
        this.taskList = Storage.fetchSavedTasks();
    }

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
                Parser.parseCommand(command, taskList);
            } catch (InvalidFormat e) {
                System.err.println(e.getMessage());
            } catch (UnknownCommand e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("☹️ Something went wrong: " + e.getMessage());
            }

            Ui.printBar();
        }
    }

    public static void main(String[] args) {
        Hachiware hachiware = new Hachiware();

        Ui.printWelcomeMessage();

        hachiware.run();
    }
}


