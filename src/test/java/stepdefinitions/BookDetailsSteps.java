package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import constants.RegEx;
import constants.keysForContext.ScenarioContextKey;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import enums.localization.translation.Spanish;
import framework.utilities.ScenarioContext;
import framework.utilities.ScreenshotUtils;
import framework.utilities.swipe.SwipeElementUtils;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.android.CatalogBookModel;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import screens.alert.AlertScreen;
import screens.bookDetails.BookDetailsScreen;

public class BookDetailsSteps {
    private final ScenarioContext context;
    private final BookDetailsScreen bookDetailsScreen;
    private final AlertScreen alertScreen;

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
            addScreenshot();
            if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN_ES ||
                    actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.REMOVE) {
                alertScreen.waitAndPerformAlertActionIfDisplayed(actionButtonKey);
            } else {
                AqualityServices.getApplication().getDriver().switchTo().alert().dismiss();
                AqualityServices.getLogger().info("Alert appears and dismiss alert");
            }
        }
    }

    @When("Click {} button but cancel the action by clicking {} button on the alert")
    public void cancelBookReturningAndRemoving(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, EnumActionButtonsForBooksAndAlertsKeys alertButtonCancel) {
        bookDetailsScreen.clickActionButton(actionButtonKey);
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && alertScreen.state().waitForDisplayed()) {
            addScreenshot();
            if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE ||
                    actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.REMOVE) {
                alertScreen.waitAndPerformAlertActionIfDisplayed(alertButtonCancel);
            }
        }
    }

    @When("Click {} action button and cancel downloading by click {} button on book detail screen")
    public void cancelBookDownloading(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, EnumActionButtonsForBooksAndAlertsKeys actionButtonCancel) {
        bookDetailsScreen.clickActionButtonForCancelTheAction(actionButtonKey);
        bookDetailsScreen.clickActionButtonForCancelTheAction(actionButtonCancel);
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
        softAssertions.assertThat(expectedPublished.equalsIgnoreCase(actualPublished)).as(String.format("expectedPublished is not equal to " +
                "actualPublished. expectedPublished - %s, actualPublished - %s", expectedPublished, actualPublished)).isTrue();
        softAssertions.assertThat(expectedPublisher.equalsIgnoreCase(actualPublisher)).as(String.format("expectedPublisher is not equal to " +
                "actualPublisher. expectedPublisher - %s, actualPublisher - %s", expectedPublisher, actualPublisher)).isTrue();
        softAssertions.assertThat(expectedCategories.equalsIgnoreCase(actualCategories)).as(String.format("expectedCategories is not equal to " +
                "actualCategories. expectedCategories - %s, actualCategories - %s", expectedCategories, actualCategories)).isTrue();
        softAssertions.assertThat(expectedDistributor.equalsIgnoreCase(actualDistributor)).as(String.format("expectedDistributor is not equal to " +
                "actualDistributor. expectedDistributor - %s, actualDistributor - %s", expectedDistributor, actualDistributor)).isTrue();
        softAssertions.assertAll();
    }

    @Then("Elements on Book detail view are translated correctly")
    public void checkTranslationOfBookDetailView() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(bookDetailsScreen.getTextFromBackBtn()).as("Back button is not translated").isEqualTo(Spanish.BACK.getDefaultLocalizedValue());
        softAssertions.assertThat(bookDetailsScreen.getTextFromLabelAboutAvailability()).as("Book availability info is not translated").isEqualTo(Spanish.BOOK_AVAILABILITY.getDefaultLocalizedValue());
        softAssertions.assertThat(bookDetailsScreen.getTextFromDescriptionLbl()).as("Description label is not translated").isEqualTo(Spanish.DESCRIPTION.getDefaultLocalizedValue());
        softAssertions.assertThat(bookDetailsScreen.getTextFromInformationLbl()).as("Information label is not translated").isEqualTo(Spanish.INFORMATION.getDefaultLocalizedValue());
        softAssertions.assertThat(bookDetailsScreen.getTextFromPublishedLbl()).as("Published label is not translated").isEqualTo(Spanish.PUBLISHED.getDefaultLocalizedValue());
        softAssertions.assertThat(bookDetailsScreen.getTextFromPublisherLbl()).as("Publisher label is not translated").isEqualTo(Spanish.PUBLISHER.getDefaultLocalizedValue());
        softAssertions.assertAll();
    }

    @Then("I check that book contains {} action button on book details screen")
    public void checkThatBookContainsActionButton(final EnumActionButtonsForBooksAndAlertsKeys key) {
        boolean isButtonPresent = bookDetailsScreen.isActionButtonDisplayed(key);
        addScreenshotIfErrorPresent(isButtonPresent);
        Assert.assertTrue(String.format("Button '%1$s' is not present on book details screen. Error (if present) - %2$s", key.getDefaultLocalizedValue(), getErrorDetails()), isButtonPresent);
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
        CatalogBookModel bookModel = context.get(bookInfoKey);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(bookDetailsScreen.getBookInfo().getTitle())
                .as("Expected book is not opened. Book title is wrong")
                .isEqualTo(bookModel.getTitle().replace(". Audiobook.", ""));
        softAssertions.assertThat(bookDetailsScreen.getBookInfo().getAuthor())
                .as("Expected book is not opened. Author is wrong")
                .isEqualTo(bookModel.getAuthor());
        softAssertions.assertAll();
    }

    @Then("Book {string} has correct title and author name on book details screen")
    public void isBookInfoCorrect(String bookInfoKey) {
        CatalogBookModel bookModel = context.get(bookInfoKey);
        String bookName = bookModel.getTitle();
        String authorName = bookModel.getAuthor();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(bookName.matches(RegEx.VALID_AUTHOR_OR_TITLE)).as("Book title has invalid symbols").isTrue();
        softAssertions.assertThat(authorName.matches(RegEx.VALID_AUTHOR_OR_TITLE)).as("Author name has invalid symbols").isTrue();
        softAssertions.assertAll();
    }

    @Then("The book cover is displayed on book details screen")
    public void isBookCoverDisplayed() {
        Assert.assertTrue("Book cover is not displayed", bookDetailsScreen.isBookHasCover());
    }

    @Then("Description is not empty in book details screen")
    public void isDescriptionNotEmpty() {
        Assert.assertTrue("Description section is empty", bookDetailsScreen.isDescriptionNotEmpty());
    }

    @Then("Button More in Description is available on book details screen")
    public void moreBtnIsAvailable() {
        Assert.assertTrue("More button is not available", bookDetailsScreen.isMoreBtnInDescriptionAvailable());
    }

    @Then("Publisher and Categories in Information section are displayed on book details screen")
    public void isInformationSectionFull() {
        SoftAssertions softAssertions = new SoftAssertions();
        if (AqualityServices.getApplication().getPlatformName()==PlatformName.ANDROID) {
            SwipeElementUtils.swipeByCoordinatesOfWindow();
        }

        softAssertions.assertThat(bookDetailsScreen.isPublisherInfoExist()).as("Publisher field is not displayed").isTrue();
        softAssertions.assertThat(bookDetailsScreen.isCategoryInfoExist()).as("Categories field is not displayed").isTrue();
        softAssertions.assertAll();
    }

    @Then("Publisher and Categories in Information section are correct on book details screen")
    public void isInformationSectionIsCorrect() {
        SoftAssertions softAssertions = new SoftAssertions();
        if (AqualityServices.getApplication().getPlatformName()==PlatformName.ANDROID) {
            SwipeElementUtils.swipeByCoordinatesOfWindow();
        }

        String publisher = bookDetailsScreen.getPublisherInfo();
        softAssertions.assertThat(publisher.matches(RegEx.VALID_PUBLISHER_OR_CATEGORY_NAME)).as("Publisher field has invalid symbols").isTrue();
        String categories = bookDetailsScreen.getCategoryInfo();
        softAssertions.assertThat(categories.matches(RegEx.VALID_PUBLISHER_OR_CATEGORY_NAME)).as("Category field has invalid symbols").isTrue();
        softAssertions.assertAll();
    }

    @Then("Distributor is equal to {string} on book details screen")
    public void isDistributorCorrect(String distributor) {
        String distributorFromScreen = bookDetailsScreen.getDistributorInfo();
        Assert.assertEquals("Distributor is not correct", distributor.toLowerCase(), distributorFromScreen.toLowerCase());
    }

    @Then("Related books section is displayed on book details screen")
    public void isRelatedBooksExists() {
        String authorName = bookDetailsScreen.getBookInfo().getAuthor();
        if (AqualityServices.getApplication().getPlatformName()==PlatformName.ANDROID) {
            SwipeElementUtils.swipeByCoordinatesOfWindow();
        }
        Assert.assertTrue("Related books section is not displayed", bookDetailsScreen.isRelatedBooksExists(authorName));
    }

    @Then("There is a list of related books on book details screen")
    public void listOfBooksIsDisplayed() {
        Assert.assertTrue("List of related books is empty", bookDetailsScreen.isListOfBooksDisplayed());
    }

    @Then("More button in related books section is available on book details screen")
    public void isMoreBtnInRelatedBooksAvailable() {
        Assert.assertTrue("More button in related books section is not available", bookDetailsScreen.isMoreBtnAvailableInRelatedBooks());
    }

    @When("Tap More... button in related books on book details view")
    public void tapMoreBtnInRelatedBooks() {
        if (AqualityServices.getApplication().getPlatformName()==PlatformName.ANDROID) {
            SwipeElementUtils.swipeByCoordinatesOfWindow();
        }
        bookDetailsScreen.tapMoreBtnInRelatedBooks();
    }
}
