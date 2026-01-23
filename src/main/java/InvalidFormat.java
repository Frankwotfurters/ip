public class InvalidFormat extends Exception {
    public InvalidFormat() {
        super("Wrong format!");
    }

    public InvalidFormat(String message) {
        super(message);
    }
}
