package hachiware;
import exception.UnknownCommand;

public enum CommandType {
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    FIND;

    public static CommandType from(String input) throws UnknownCommand {
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommand();
        }
    }
}
