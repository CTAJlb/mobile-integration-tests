package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
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

    @Then("No books are present in Books list")
    public void checkNoBooksArePresentInBooksList() {
        Assert.assertTrue("Books are present in Books list", booksScreen.isNoBooksMessagePresent());
    }

    @Then("Book {string} is present in Books List")
    public void checkBookInfoIsPresentInBooksList(String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        booksScreen.state().waitForDisplayed();
        AqualityServices.getConditionalWait().waitFor(() -> booksScreen.isNoBooksMessagePresent() || booksScreen.getCountOfBooks() > 0);
        Assert.assertTrue(String.format("Book '%s' is not present in Books List", bookInfo), booksScreen.isBookPresent(bookInfo));
    }

    @Then("Book {string} is not present in Books List")
    public void checkBookInfoIsNotPresentInBooksList(String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        Assert.assertFalse(String.format("Book '%s' is present in Books List", bookInfo), booksScreen.isBookPresent(bookInfo));
    }

    @And("Count of books is equal to {int}")
    public void checkCountOfBooksIsEqualTo(int expectedCountOfBooks) {
        Assert.assertEquals("Count of books is not correct", expectedCountOfBooks, booksScreen.getCountOfBooks());
    }

    @When("I refresh list of books")
    public void refreshListOfBooks() {
        booksScreen.refreshList();
    }

    @And("I Read book {string}")
    public void readBook(String bookInfoKey) {
        booksScreen.readBook(context.get(bookInfoKey));
    }
}
