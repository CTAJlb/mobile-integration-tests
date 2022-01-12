package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import constants.application.EnumBookType;
import constants.application.timeouts.BooksTimeouts;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import models.android.CatalogBookModel;
import org.junit.Assert;
import screens.alert.AlertScreen;
import screens.catalog.screen.books.CatalogBooksScreen;

import java.time.Duration;

public class CatalogBooksSteps {
    private final CatalogBooksScreen catalogBooksScreen;
    private final AlertScreen alertScreen;
    private ScenarioContext context;

    @Inject
    public CatalogBooksSteps(ScenarioContext context) {
        this.context = context;
        catalogBooksScreen = AqualityServices.getScreenFactory().getScreen(CatalogBooksScreen.class);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);
    }

    @When("Open {} book with {} action button and {string} bookName on catalog books screen and save book as {string}")
    public void openBookAndSaveBookInfo(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookNameKey, String bookInfoKey) {
        String bookName = context.get(bookNameKey);
        CatalogBookModel bookInfo = catalogBooksScreen.openBookAndGetBookInfo(bookType, bookName, actionButtonKey);
        context.add(bookInfoKey, bookInfo);
    }

    @When("Click {} action button on {} book with {string} bookName on catalog books screen and save book as {string}")
    public void clickActionButtonAndSaveBookInfo(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, EnumBookType bookType, String bookNameKey, String bookInfoKey) {
        String bookName = context.get(bookNameKey);
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.ANDROID) {
            actionButtonKey = EnumActionButtonsForBooksAndAlertsKeys.DOWNLOAD;
        }
        CatalogBookModel bookInfo = catalogBooksScreen.clickActionButtonAndGetBookInfo(bookType, bookName, actionButtonKey);
        context.add(bookInfoKey, bookInfo);
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && alertScreen.state().waitForDisplayed()) {
            if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.REMOVE) {
                alertScreen.waitAndPerformAlertActionIfDisplayed(actionButtonKey);
            } else {
                AqualityServices.getApplication().getDriver().switchTo().alert().dismiss();
                AqualityServices.getLogger().info("Alert appears and dismiss alert");
            }
        }
    }

    @And("{} book with {} action button and {string} bookInfo is present on catalog books screen")
    public void isBookPresent(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        String bookName = bookInfo.getTitle();
        Assert.assertTrue(String.format("'%s' book with specific action button is not present on catalog books screen", bookName),
                catalogBooksScreen.isBookDisplayed(bookType, bookName, actionButtonKey));
    }

    @And("Click {} action button on the first {} book on catalog books screen and save book as {string}")
    public void clickActionButtonOnTheFirstBookAndSaveBookInfo(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, EnumBookType bookType, String bookInfoKey) {
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.ANDROID) {
            actionButtonKey = EnumActionButtonsForBooksAndAlertsKeys.DOWNLOAD;
        }
        CatalogBookModel bookInfo = catalogBooksScreen.clickActionButtonOnTheFirstBookAndGetBookInfo(bookType, actionButtonKey);
        context.add(bookInfoKey, bookInfo);
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && alertScreen.state().waitForDisplayed()) {
            if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.REMOVE) {
                alertScreen.waitAndPerformAlertActionIfDisplayed(actionButtonKey);
            } else {
                AqualityServices.getApplication().getDriver().switchTo().alert().dismiss();
                AqualityServices.getLogger().info("Alert appears and dismiss alert");
            }
        }
    }
}
