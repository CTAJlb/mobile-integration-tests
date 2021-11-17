package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import constants.keysForContext.ScenarioContextKey;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.ScenarioContext;
import framework.utilities.ScreenshotUtils;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import screens.alert.AlertScreen;
import screens.bookDetails.BookDetailsScreen;

import java.util.Optional;

public class BookDetailsSteps {
    private ScenarioContext context;
    private BookDetailsScreen bookDetailsScreen;
    private AlertScreen alertScreen;

    @Inject
    public BookDetailsSteps(ScenarioContext context) {
        this.context = context;
        bookDetailsScreen = AqualityServices.getScreenFactory().getScreen(BookDetailsScreen.class);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);
    }

    @And("I close Book Details for IOSTab")
    public void closeBookDetailsOnlyForIOSTab() {
        bookDetailsScreen.closeBookDetailsOnlyForIOSTabIfDisplayed();
    }

    @When("Click {} action button on book details screen")
    public void pressOnBookDetailsScreenAtActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        bookDetailsScreen.clickActionButton(actionButtonKey);
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && alertScreen.state().waitForDisplayed()) {
            if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.REMOVE) {
                alertScreen.waitAndPerformAlertActionIfDisplayed(actionButtonKey);
            } else {
                AqualityServices.getApplication().getDriver().switchTo().alert().dismiss();
                AqualityServices.getLogger().info("Alert appears and dismiss alert");
            }
        }
    }

    @Then("I check that book contains {} action button on book details screen")
    public void checkThatBookContainsActionButton(final EnumActionButtonsForBooksAndAlertsKeys key) {
        boolean isButtonPresent = bookDetailsScreen.isActionButtonPresent(key);
        addScreenshotIfErrorPresent(isButtonPresent);
        Assert.assertTrue(String.format("Button '%1$s' is not present on book details screen. Error (if present) - %2$s", key.i18n(), getErrorDetails()), isButtonPresent);
    }

    private String getErrorDetails() {
        if (bookDetailsScreen.isErrorButtonPresent()) {
            bookDetailsScreen.openErrorDetails();
            String errorDetails = bookDetailsScreen.getErrorDetails();
            addScreenshot();
            bookDetailsScreen.swipeError();
            return errorDetails;
        } else {
            return "";
        }
    }

    private void addScreenshotIfErrorPresent(boolean isButtonPresent) {
        if (!isButtonPresent && bookDetailsScreen.isErrorButtonPresent()) {
            addScreenshot();
        }
    }

    private void addScreenshot() {
        Scenario scenario = context.get(ScenarioContextKey.SCENARIO_KEY);
        scenario.attach(ScreenshotUtils.getScreenshot(), "image/png", "screenshot.png");
    }

    @Then("Book {string} is opened on book details screen")
    public void isBookOpened(String bookInfoKey) {
        Assert.assertEquals("Expected book is not opened", Optional.ofNullable(context.get(bookInfoKey)).orElse(bookInfoKey), bookDetailsScreen.getBookInfo());
    }
}
