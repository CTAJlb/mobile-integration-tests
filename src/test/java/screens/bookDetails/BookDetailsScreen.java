package screens.bookDetails;

import aquality.appium.mobile.screens.Screen;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;

public abstract class BookDetailsScreen extends Screen {
    protected BookDetailsScreen(By locator) {
        super(locator, "Book details");
    }

    public abstract CatalogBookModel getBookInfo();

    public abstract boolean isActionButtonDisplayed(EnumActionButtonsForBooksAndAlertsKeys key);

    public abstract void clickActionButton(EnumActionButtonsForBooksAndAlertsKeys buttonKeys);

    public abstract void clickActionButtonForCancelTheAction(EnumActionButtonsForBooksAndAlertsKeys buttonKeys);

    public abstract String getErrorDetails();

    public abstract boolean isErrorButtonPresent();

    public abstract boolean isProgressBarDisplayed();

    public abstract void openErrorDetails();

    public abstract void swipeError();

    public abstract String getPublishedInfo();

    public abstract boolean isBookFormatInfoExist();

    public abstract String getBookFormatInfo();

    public abstract String getPublisherInfo();

    public abstract boolean isPublisherInfoExist();

    public abstract String getCategoryInfo();

    public abstract boolean isCategoryInfoExist();

    public abstract String getDistributorInfo();

    public abstract void closeBookDetailsOnlyForIOSTabIfDisplayed();

    public abstract boolean isBookHasCover();

    public abstract boolean isDescriptionNotEmpty();

    public abstract boolean isMoreBtnInDescriptionAvailable();

    public abstract boolean isRelatedBooksExists(String authorName);

    public abstract boolean isListOfBooksDisplayed();

    public abstract boolean isMoreBtnAvailableInRelatedBooks();

    public abstract void tapMoreBtnInRelatedBooks();

    public abstract String getTextFromBackBtn();

    public abstract String getTextFromLabelAboutAvailability();

    public abstract String getTextFromDescriptionLbl();

    public abstract String getTextFromInformationLbl();

    public abstract String getTextFromPublishedLbl();

    public abstract String getTextFromPublisherLbl();
}
