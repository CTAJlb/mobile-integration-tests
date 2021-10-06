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

    public abstract int getFoundBooksCount();

    public abstract CatalogBookModel getBookInfo(String title);

    //here
    public abstract CatalogBookModel clickSpecificActionButtonOnBookWithSpecificTypeAndSpecificNameAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract void clickBookByTitleButtonWithKey(String title, EnumActionButtonsForBooksAndAlertsKeys key);

    //here
    public abstract CatalogBookModel openBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract boolean isBookAddButtonTextEqualTo(String bookTitle, EnumActionButtonsForBooksAndAlertsKeys key);

    public abstract CatalogBookModel scrollToBookByTypeAndClickActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookType);

    public abstract CatalogBookModel scrollToBookByNameAndClickActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookName);

    public abstract CatalogBookModel scrollToBookByNameAndClickGetOrDownloadActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey1, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey2, String bookName);

    public abstract String getErrorMessage();

    public abstract boolean isErrorButtonPresent();
}
