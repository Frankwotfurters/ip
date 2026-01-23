public enum CommandType {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT;

    public static CommandType from(String input) throws UnknownCommand {
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommand();
        }
    }
}
