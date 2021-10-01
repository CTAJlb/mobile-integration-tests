package screens.books;

import aquality.appium.mobile.screens.Screen;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;

public abstract class BooksScreen extends Screen {
    protected BooksScreen(By locator) {
        super(locator, "Books");
    }

    public abstract boolean isNoBooksMessagePresent();

    public abstract boolean isSpecificBookWithSpecificActionButtonPresent(CatalogBookModel bookInfo, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract int getCountOfBooks();

    public abstract void refreshList();

    public abstract void openBookWithDefiniteNameAndDefiniteActionButton(CatalogBookModel bookInfo, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);
}
