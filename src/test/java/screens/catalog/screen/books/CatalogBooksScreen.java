package screens.catalog.screen.books;

import aquality.appium.mobile.screens.Screen;
import enums.EnumBookType;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;

public abstract class CatalogBooksScreen extends Screen {
    protected CatalogBooksScreen(By locator) {
        super(locator, "Catalog Books");
    }

    public abstract CatalogBookModel clickActionButtonAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract CatalogBookModel openBookAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract void openBook(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookName);

    public abstract CatalogBookModel openBookAndGetBookInfo(int bookNumber);
    public abstract boolean isBookDisplayed(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract boolean isProgressBarDisplayed(String bookName);

    public abstract CatalogBookModel clickActionButtonOnTheFirstBookAndGetBookInfo(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract CatalogBookModel clickActionButtonOnABookAndGetBookInfo(int bookNumber, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract void clickActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookName);

    public abstract boolean isSearchResultsEmpty();

    public abstract boolean isBooksContainWord(String word);

    public abstract String getNameOfFirstBook();

    public abstract boolean isActionButtonDisplayed(String bookName, EnumActionButtonsForBooksAndAlertsKeys key);

    public abstract int getNumberOfBooksOnTheScreen();
}
