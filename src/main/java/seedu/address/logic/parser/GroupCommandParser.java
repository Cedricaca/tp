package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.GroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the arguments to create a groupCommand
 */
public class GroupCommandParser implements Parser<GroupCommand> {
    /**
     * parses the arguments
     * @param args the arguments
     * @return the GroupCommand
     * @throws ParseException the error thrown
     */
    public GroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);

        if (!arePrefixesPresent(argMultimap, PREFIX_CATEGORY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_CATEGORY).get().isEmpty()) {
            throw new ParseException(GroupCommand.NO_INPUT);
        }

        // Gets each category from the command
        List<String> categories = argMultimap.getAllValues(PREFIX_CATEGORY);
        if (categories.size() > 1) {
            throw new ParseException(GroupCommand.TOO_MANY_CATEGORIES);
        }
        if (categories.get(0).trim() == "") {
            throw new ParseException(GroupCommand.NO_INPUT);
        }

        return new GroupCommand(categories.get(0));
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
