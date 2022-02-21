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
import org.assertj.core.api.SoftAssertions;
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

    @When("Click {} action button on book details screen and click {} action button on alert. Only for ios")
    public void pressActionButtonAndAlertActionButtonOnBookDetailsScreen(EnumActionButtonsForBooksAndAlertsKeys actionBookButtonKey, EnumActionButtonsForBooksAndAlertsKeys actionAlertButtonKey) {
        bookDetailsScreen.clickActionButton(actionBookButtonKey);
        alertScreen.waitAndPerformAlertActionIfDisplayed(actionAlertButtonKey);
    }

    @Then("{string} published and {string} publisher and {string} categories and {string} distributor are present on book details screen")
    public void checkThatFollowingInfoIsPresent(String expectedPublished, String expectedPublisher, String expectedCategories, String expectedDistributor) {
        String actualPublished = bookDetailsScreen.getPublishedInfo();
        String actualPublisher = bookDetailsScreen.getPublisherInfo();
        String actualCategories = bookDetailsScreen.getCategoryInfo();
        String actualDistributor = bookDetailsScreen.getDistributorInfo();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(expectedPublished.toLowerCase().equals(actualPublished.toLowerCase())).as(String.format("expectedPublished is not equal to " +
                "actualPublished. expectedPublished - %s, actualPublished - %s", expectedPublished, actualPublished)).isTrue();
        softAssertions.assertThat(expectedPublisher.toLowerCase().equals(actualPublisher.toLowerCase())).as(String.format("expectedPublisher is not equal to " +
                "actualPublisher. expectedPublisher - %s, actualPublisher - %s", expectedPublisher, actualPublisher)).isTrue();
        softAssertions.assertThat(expectedCategories.toLowerCase().equals(actualCategories.toLowerCase())).as(String.format("expectedCategories is not equal to " +
                "actualCategories. expectedCategories - %s, actualCategories - %s", expectedCategories, actualCategories)).isTrue();
        softAssertions.assertThat(expectedDistributor.toLowerCase().equals(actualDistributor.toLowerCase())).as(String.format("expectedDistributor is not equal to " +
                "actualDistributor. expectedDistributor - %s, actualDistributor - %s", expectedDistributor, actualDistributor)).isTrue();
        softAssertions.assertAll();
    }

    @Then("I check that book contains {} action button on book details screen")
    public void checkThatBookContainsActionButton(final EnumActionButtonsForBooksAndAlertsKeys key) {
        boolean isButtonPresent = bookDetailsScreen.isActionButtonDisplayed(key);
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
