package screens.books;

import aquality.appium.mobile.screens.Screen;
import constants.application.EnumBookType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;

public abstract class BooksScreen extends Screen {
    protected BooksScreen(By locator) {
        super(locator, "Books");
    }

    public abstract boolean isNoBooksMessagePresent();

    public abstract boolean isBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonPresent(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract int getCountOfBooks();

    public abstract void refreshList();

    public abstract void openBookWithSpecificTypeAndSpecificNameAndSpecificActionButton(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);
}
