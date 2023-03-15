package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import enums.EnumBookType;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.android.CatalogBookModel;
import org.junit.Assert;
import screens.books.BooksScreen;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;

import java.util.List;
import java.util.stream.Collectors;

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

    @Then("There are not books on books screen")
    public void areBooksNotPresent() {
        Assert.assertTrue("Books are present on books screen", booksScreen.isNoBooksMessagePresent());
    }

    @And("Open Books")
    public void openBooks() {
        bottomMenuForm.open(BottomMenu.BOOKS);
    }

    @Then("Added books from {string} are displayed on books screen")
    public void areBooksDisplayed(String listKey) {
        Assert.assertTrue("Added books are not displayed on books screen", booksScreen.areBooksDisplayed(context.get(listKey)));
    }

    @Then("Books are sorted by Author ascending on books screen")
    public void areBooksSortedByAuthor() {
        List<String> authorsList = booksScreen.getListOfAuthors();
        Assert.assertEquals("Books are not sorted by author", authorsList.stream().sorted().collect(Collectors.toList()), authorsList);
    }

    @Then("Books are sorted by Title ascending on books screen")
    public void areBooksSortedByTitle() {
        List<String> titlesList = booksScreen.getListOfTitles();
        Assert.assertEquals("Books are not sorted by title", titlesList.stream().sorted().collect(Collectors.toList()), titlesList);
    }

    @Then("Books screen is loaded")
    public void isBooksScreenOpened() {
        Assert.assertTrue("My books screen is not opened", booksScreen.isBooksScreenOpened());
    }

    @When("Open {} book with {} action button and {string} bookInfo on books screen")
    public void openBook(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        String bookName = bookInfo.getTitle();
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && bookType == EnumBookType.AUDIOBOOK) {
            bookName = bookName + ". Audiobook.";
        }
        booksScreen.openBook(bookType, bookName, actionButtonKey);
    }

    @Then("{} book with {} action button and {string} bookInfo is not present on books screen")
    public void isBookNotPresent(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        String bookName = bookInfo.getTitle();
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && bookType == EnumBookType.AUDIOBOOK) {
            bookName = bookName + ". Audiobook.";
        }
        Assert.assertFalse(String.format("'%s' book with specific action button is present on books screen", bookName),
                booksScreen.isBookDisplayed(bookType, bookName, actionButtonKey));
    }

    @Then("{} book with {} action button and {string} bookInfo is present on books screen")
    public void isBookPresent(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        String bookName = bookInfo.getTitle();
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && bookType == EnumBookType.AUDIOBOOK) {
            bookName = bookName + ". Audiobook.";
        }
        Assert.assertTrue(String.format("'%s' book with specific action button is not present on books screen", bookName),
                booksScreen.isBookDisplayed(bookType, bookName, actionButtonKey));
    }

    @And("Amount of books is equal to {int} on books screen")
    public void isAmountOfBooksEqualTo(int expectedAmountOfBooks) {
        Assert.assertEquals("Amount of books is not correct on books screen", expectedAmountOfBooks, booksScreen.getCountOfBooks());
    }

    @When("I refresh list of books on books screen")
    public void refreshListOfBooks() {
        booksScreen.refreshList();
    }
}
