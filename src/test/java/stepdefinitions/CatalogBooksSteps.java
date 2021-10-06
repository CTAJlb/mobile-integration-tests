package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import constants.application.EnumBookType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import models.android.CatalogBookModel;
import org.junit.Assert;
import screens.alert.AlertScreen;
import screens.catalog.screen.books.CatalogBooksScreen;

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
    public void openBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonAndSaveBookInfo(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookNameKey, String bookInfoKey) {
        String bookName = context.get(bookNameKey);
        CatalogBookModel bookInfo = catalogBooksScreen.openBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonAndGetBookInfo(bookType, bookName, actionButtonKey);
        context.add(bookInfoKey, bookInfo);
    }

    @When("Click {} action button on {} book with {string} bookName on catalog books screen and save book as {string}")
    public void clickSpecificActionButtonOnBookWithSpecificTypeAndSpecificNameAndSaveBookInfo(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, EnumBookType bookType, String bookNameKey, String bookInfoKey) {
        String bookName = context.get(bookNameKey);
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CatalogBookModel bookInfo = catalogBooksScreen.clickSpecificActionButtonOnBookWithSpecificTypeAndSpecificNameAndGetBookInfo(bookType, bookName, actionButtonKey);
        context.add(bookInfoKey, bookInfo);
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && alertScreen.state().waitForDisplayed()) {
            if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.CANCEL_RESERVATION){
                alertScreen.waitAndPerformAlertActionIfDisplayed(actionButtonKey);
            }else {
                AqualityServices.getApplication().getDriver().switchTo().alert().dismiss();
                AqualityServices.getLogger().info("Alert appears and dismiss alert");
            }
        }
    }

    @And("{} book with {} action button and {string} bookInfo is present on catalog books screen")
    public void isBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonPresent(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        String bookName = bookInfo.getTitle();
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(String.format("'%s' book with specific action button is not present on catalog books screen", bookName),
                catalogBooksScreen.isBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonPresent(bookType, bookName, actionButtonKey));
    }
}
