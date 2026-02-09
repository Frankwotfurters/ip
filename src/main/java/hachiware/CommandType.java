package hachiware;
import exception.UnknownCommand;

/**
 * Class to handle different command enumerations
 */
public enum CommandType {
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    FIND;

    /**
     * Method to parse a command input, and return its respective CommandType
     * @param input
     * @return its respective CommandType
     * @throws UnknownCommand if CommandType not found
     */
    public static CommandType from(String input) throws UnknownCommand {
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommand();
        }
    }
}
