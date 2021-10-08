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

    public abstract CatalogBookModel getBookInfo(String title);

    //here
    public abstract CatalogBookModel clickSpecificActionButtonOnBookWithSpecificTypeAndSpecificNameAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    //here
    public abstract CatalogBookModel openBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    //here
    public abstract boolean isBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonPresent(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract CatalogBookModel scrollToBookByTypeAndClickActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookType);

    public abstract boolean isErrorButtonPresent();

    //here
    public abstract CatalogBookModel clickSpecificActionButtonOnTheFirstBookWithSpecificActionButtonAndGetBookInfo(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);
}
