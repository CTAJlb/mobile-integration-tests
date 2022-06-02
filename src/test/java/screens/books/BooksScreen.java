package screens.books;

import aquality.appium.mobile.screens.Screen;
import constants.application.EnumBookType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import org.openqa.selenium.By;

import java.util.List;

public abstract class BooksScreen extends Screen {
    protected BooksScreen(By locator) {
        super(locator, "Books");
    }

    public abstract boolean isNoBooksMessagePresent();

    public abstract boolean isBookDisplayed(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract int getCountOfBooks();

    public abstract void refreshList();

    public abstract void openBook(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract boolean isBooksScreenOpened();

    public abstract boolean areBooksDisplayed(List<String> listOfBooks);

    public abstract List<String> getListOfAuthors();

    public abstract List<String> getListOfTitles();
}
