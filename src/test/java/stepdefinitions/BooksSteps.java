package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.application.EnumBookType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.android.CatalogBookModel;
import org.junit.Assert;
import screens.books.BooksScreen;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;

public class BooksSteps {
    private final BooksScreen booksScreen;
    protected final BottomMenuForm bottomMenuForm;
    private ScenarioContext context;

    @Inject
    public BooksSteps(ScenarioContext context) {
        this.context = context;
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        booksScreen = AqualityServices.getScreenFactory().getScreen(BooksScreen.class);
    }

    @Then("There are not books on Books Screen")
    public void areBooksNotPresent() {
        Assert.assertTrue("Books are present on books screen", booksScreen.isNoBooksMessagePresent());
    }

    @And("I open Books")
    public void openBooks() {
        bottomMenuForm.open(BottomMenu.BOOKS);
    }

    @When("Open {} book with {} action button and {string} bookInfo on books screen")
    public void openBookDetailsByClickingOnCover(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        String bookName = bookInfo.getTitle();
        booksScreen.openBookWithSpecificTypeAndSpecificNameAndSpecificActionButton(bookType, bookName, actionButtonKey);
    }

    @Then("{} book with {} action button and {string} bookInfo is not present on books screen")
    public void isBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonNotPresent(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        String bookName = bookInfo.getTitle();
        Assert.assertFalse(String.format("'%s' book with specific action button is present on books screen", bookName),
                booksScreen.isBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonPresent(bookType, bookName, actionButtonKey));
    }

    @Then("{} book with {} action button and {string} bookInfo is present on books screen")
    public void isBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonPresent(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        String bookName = bookInfo.getTitle();
        Assert.assertTrue(String.format("'%s' book with specific action button is not present on books screen", bookName),
                booksScreen.isBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonPresent(bookType, bookName, actionButtonKey));
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
