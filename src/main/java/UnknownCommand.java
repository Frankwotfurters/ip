public class UnknownCommand extends Exception {
    public UnknownCommand() {
        super("Unknown command!");
    }

    public UnknownCommand(String message) {
        super(message);
    }
}
