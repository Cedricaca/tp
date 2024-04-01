package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * creates a groupcommand
 */
public class GroupCommand extends Command {
    public static final String COMMAND_WORD = "group";
    public static final String TOO_MANY_CATEGORIES = "Please specify only 1 category";
    public static final String NO_INPUT = "Please specify a category";

    private String category;
    public GroupCommand(String category) {
        this.category = category;
    }

    /**
     * executes the model to group list
     * @param model {@code Model} which the command should operate on.
     * @return success result
     */
    public CommandResult execute(Model model) {
        model.groupPerson(category);
        return new CommandResult("All persons have been grouped!");
    }
}
