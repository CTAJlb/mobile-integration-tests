package screens.bookDetails;

import aquality.appium.mobile.screens.Screen;
import constants.localization.application.bookdetals.BookDetailsScreenInformationBlockKeys;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;

public abstract class BookDetailsScreen extends Screen {
    protected BookDetailsScreen(By locator) {
        super(locator, "Book details");
    }

    public abstract CatalogBookModel getBookInfo();

    public abstract boolean isValuePresentInInformationBlock(
            BookDetailsScreenInformationBlockKeys key, String value);

    public abstract boolean isDescriptionPresent();

    public abstract String getDescriptionText();

    public abstract void clickRelatedBooks();

    public abstract boolean isRelatedBooksVisible();

    public abstract boolean isBookAddButtonTextEqualTo(EnumActionButtonsForBooksAndAlertsKeys key);

    public abstract void clickActionButton(EnumActionButtonsForBooksAndAlertsKeys buttonKeys);

    public abstract boolean isActionButtonPresent(EnumActionButtonsForBooksAndAlertsKeys actionButton);

    public abstract String getErrorDetails();

    public abstract boolean isErrorButtonPresent();

    public abstract void openErrorDetails();

    public abstract void swipeError();

    public abstract boolean isBookReadyToRead();

    public abstract void closeBookDetailsOnlyForIOSTabIfDisplayed();
}
