package hachiware;
import task.TaskList;

import org.junit.jupiter.api.Test;

import exception.InvalidFormat;
import exception.UnknownCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseCommand_missingBy_invalidFormatThrown() {
        try {
            Parser.parseCommand("deadline a b c", new TaskList());
            fail("Expected InvalidFormat exception thrown");
        } catch (InvalidFormat e) {
            assertEquals(e.getMessage(), "Invalid format! Please use the format \'deadline [description] /by [DD/MM/YYYY HHMM]'!\'");
        } catch (UnknownCommand e) {
            fail("Wrong exception thrown");
        }
    }
}
