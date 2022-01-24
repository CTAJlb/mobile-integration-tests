package screens.bookDetails;

import aquality.appium.mobile.screens.Screen;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;

public abstract class BookDetailsScreen extends Screen {
    protected BookDetailsScreen(By locator) {
        super(locator, "Book details");
    }

    public abstract CatalogBookModel getBookInfo();

    public abstract boolean isActionButtonDisplayed(EnumActionButtonsForBooksAndAlertsKeys key);

    public abstract void clickActionButton(EnumActionButtonsForBooksAndAlertsKeys buttonKeys);

    public abstract String getErrorDetails();

    public abstract boolean isErrorButtonPresent();

    public abstract boolean isProgressBarDisplayed();

    public abstract void openErrorDetails();

    public abstract void swipeError();

    public abstract void closeBookDetailsOnlyForIOSTabIfDisplayed();
}
