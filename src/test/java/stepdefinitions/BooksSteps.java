package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.android.CatalogBookModel;
import org.junit.Assert;
import screens.books.BooksScreen;

public class BooksSteps {
    private final BooksScreen booksScreen;
    private ScenarioContext context;

    @Inject
    public BooksSteps(ScenarioContext context) {
        this.context = context;
        booksScreen = AqualityServices.getScreenFactory().getScreen(BooksScreen.class);
    }

    @Then("There are not books on Books Screen")
    public void checkNoBooksArePresentInBooksList() {
        Assert.assertTrue("Books are present in Books list", booksScreen.isNoBooksMessagePresent());
    }

    @When("I open {string} book with {} action button on Books Screen")
    public void openBookDetailsByClickingOnCover(String bookInfoKey, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        booksScreen.state().waitForDisplayed();
        booksScreen.openBookWithDefiniteNameAndDefiniteActionButton(bookInfo, actionButtonKey);
    }

    @Then("Book {string} with {} action button is not present on Books Screen")
    public void checkBookWithReadActionButtonIsNotPresentInBooksList(String bookInfoKey, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        Assert.assertFalse(String.format("Book '%s' with action button is present in Books List", bookInfo), booksScreen.isSpecificBookWithSpecificActionButtonPresent(bookInfo, actionButtonKey));
    }

    @Then("Book {string} with {} action button is present on Books Screen")
    public void checkBookWithReadActionButtonIsPresentInBooksList(String bookInfoKey, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        Assert.assertTrue(String.format("Book '%s' with action button is not present in Books List", bookInfo), booksScreen.isSpecificBookWithSpecificActionButtonPresent(bookInfo, actionButtonKey));
    }

    @And("Count of books is equal to {int}")
    public void checkCountOfBooksIsEqualTo(int expectedCountOfBooks) {
        Assert.assertEquals("Count of books is not correct", expectedCountOfBooks, booksScreen.getCountOfBooks());
    }

    @When("I refresh list of books")
    public void refreshListOfBooks() {
        booksScreen.refreshList();
    }
}
