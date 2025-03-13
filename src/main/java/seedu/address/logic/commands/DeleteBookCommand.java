package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOK;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Book;

/**
 * Deletes a book identified using it's name from the address book.
 */
public class DeleteBookCommand extends Command {

    public static final String COMMAND_WORD = "delete_book";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the book identified by the book name used in the displayed book list.\n"
            + "Parameters: " + PREFIX_BOOK + "BOOK NAME" + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_BOOK +  " Percy Jackson";

    public static final String MESSAGE_DELETE_BOOK_SUCCESS = "Deleted Book: %1$s";
    public static final String MESSAGE_DELETE_BOOK_FAIL = "Book: %1$s not found in book list.";
    private final String bookName;

    public DeleteBookCommand(String bookName) {
        this.bookName = bookName;
    }
    
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();
        
        for (Book book : lastShownList) {
            if (bookName.equals(book.getName())) {
                model.deleteBook(book);
                return new CommandResult(String.format(MESSAGE_DELETE_BOOK_SUCCESS, Messages.format(book)));
            }
        }
        throw new CommandException(String.format(MESSAGE_DELETE_BOOK_FAIL, bookName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
    
        if (!(other instanceof DeleteBookCommand)) {
            return false;
        }
    
        DeleteBookCommand otherDeleteCommand = (DeleteBookCommand) other;
        return bookName.equals(otherDeleteCommand.bookName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("bookName", bookName)
                .toString();
    }

}
