package screens.catalog.screen.books;

import aquality.appium.mobile.screens.Screen;
import constants.application.EnumBookType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;

public abstract class CatalogBooksScreen extends Screen {
    protected CatalogBooksScreen(By locator) {
        super(locator, "Catalog Books");
    }

    public abstract CatalogBookModel clickActionButtonAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract CatalogBookModel openBookAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract boolean isBookDisplayed(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract boolean isProgressBarDisplayed(String bookName);

    public abstract CatalogBookModel clickActionButtonOnTheFirstBookAndGetBookInfo(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract boolean isSearchResultsEmpty();

    public abstract boolean isBooksContainWord(String word);

    public abstract String getNameOfFirstBook();

    public abstract boolean isActionButtonDisplayed(String bookName, EnumActionButtonsForBooksAndAlertsKeys key);
}
