package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import enums.EnumBookType;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.ScenarioContext;
import framework.utilities.swipe.SwipeElementUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.android.CatalogBookModel;
import org.junit.Assert;
import screens.alert.AlertScreen;
import screens.catalog.screen.books.CatalogBooksScreen;

import java.util.Random;

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

    @When("Open random book on catalog book screen and save book as {string}")
    public void openRandomBookAndSaveBookInfo(String bookInfoKey) {
        Random random = new Random();
        int bookNumber = random.nextInt(catalogBooksScreen.getNumberOfBooksOnTheScreen() - 4) + 4;
        CatalogBookModel bookInfo = catalogBooksScreen.openBookAndGetBookInfo(bookNumber);
        context.add(bookInfoKey, bookInfo);
    }

    @When("I swipe catalog of books down on catalog book screen")
    public void swipeDown() {
        SwipeElementUtils.swipeByCoordinatesOfWindow();
    }

    @When("Open book with {} action button and {string} bookName on catalog books screen")
    public void openBook(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookNameInfoKey) {
        String bookName = context.get(bookNameInfoKey);
        catalogBooksScreen.openBook(actionButtonKey, bookName);
    }

    @Then("I check that book {string} contains {} action button on catalog book screen")
    public void isBookContainBtn(String bookNameKey, final EnumActionButtonsForBooksAndAlertsKeys key) {
        String bookName = context.get(bookNameKey);
        Assert.assertTrue("Button " + key + " is not displayed", catalogBooksScreen.isActionButtonDisplayed(bookName, key));
    }

    @When("Click {} action button on {} book with {string} bookName on catalog books screen and save book as {string}")
    public void clickActionButtonAndSaveBookInfo(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, EnumBookType bookType, String bookNameKey, String bookInfoKey) {
        String bookName = context.get(bookNameKey);
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

    @Then("{} book with {} action button and {string} bookName is present on catalog books screen")
    public void bookIsDisplayed(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookNameKey) {
        String bookName = context.get(bookNameKey);
        Assert.assertTrue(String.format("'%s' book with specific buttion is not present on catalog books screen", bookName),
                catalogBooksScreen.isBookDisplayed(bookType, bookName, actionButtonKey));
    }

    @Then("Books contain word {string} on catalog books screen")
    public void isBookContainWord(String info) {
        String word = context.get(info);
        Assert.assertTrue("Search result does not contain books with " + word, catalogBooksScreen.isBooksContainWord(word));
    }

    @Then("The first book has {string} bookName on catalog books screen")
    public void isFirstBookContainWord(String info) {
        String word = context.get(info);
        Assert.assertEquals("The book " + word + " is not found", word.toLowerCase(), catalogBooksScreen.getNameOfFirstBook().toLowerCase());
    }

    @Then("Search result is empty on catalog books screen")
    public void isSearchEmpty() {
        Assert.assertTrue("Results are not empty", catalogBooksScreen.isSearchResultsEmpty());
    }

    @And("Click {} action button on the first {} book on catalog books screen and save book as {string}")
    public void clickActionButtonOnTheFirstBookAndSaveBookInfo(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, EnumBookType bookType, String bookInfoKey) {
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

    @When("Click {} action button on random book from the catalog book screen and save book as {string}")
    public void clickActionBtnOnRandomBookAndSaveBookInfo(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        int bookNumber = (int) (Math.random() * catalogBooksScreen.getNumberOfBooksOnTheScreen()) + 1;
        CatalogBookModel bookInfo = catalogBooksScreen.clickActionButtonOnABookAndGetBookInfo(bookNumber, actionButtonKey);
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

    @When("Save book name of the book {string} as {string} on catalog book screen")
    public void saveTheNameOfBook(String bookInfoKey, String bookNameInfoKey) {
        CatalogBookModel bookModel = context.get(bookInfoKey);
        context.add(bookNameInfoKey, bookModel.getTitle());
    }
}
