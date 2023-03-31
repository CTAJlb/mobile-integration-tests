package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import constants.localization.italian.ItalianIos;
import constants.localization.spanish.SpanishIos;
import enums.EnumBookType;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.ScenarioContext;
import framework.utilities.feedXMLUtil.GettingBookUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import screens.catalog.form.MainCatalogToolbarForm;
import screens.catalog.screen.books.CatalogBooksScreen;
import screens.catalog.screen.catalog.CatalogScreen;
import screens.search.modal.SearchModal;
import screens.subcategory.SubcategoryScreen;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchSteps {
    private final MainCatalogToolbarForm mainCatalogToolbarForm;
    private final SearchModal searchModal;
    private final SubcategoryScreen subcategoryScreen;
    private final CatalogScreen catalogScreen;
    private final CatalogBooksScreen catalogBooksScreen;
    private ScenarioContext context;

    @Inject
    public SearchSteps(ScenarioContext context) {
        this.context = context;
        mainCatalogToolbarForm = AqualityServices.getScreenFactory().getScreen(MainCatalogToolbarForm.class);
        searchModal = AqualityServices.getScreenFactory().getScreen(SearchModal.class);
        subcategoryScreen = AqualityServices.getScreenFactory().getScreen(SubcategoryScreen.class);
        catalogScreen = AqualityServices.getScreenFactory().getScreen(CatalogScreen.class);
        catalogBooksScreen = AqualityServices.getScreenFactory().getScreen(CatalogBooksScreen.class);
    }

    @When("Open search modal")
    public void openSearchModal() {
        catalogScreen.state().waitForDisplayed();
        mainCatalogToolbarForm.openSearchModal();
        searchModal.state().waitForDisplayed();
    }

    @Then("Placeholder contains {string} text in search field")
    public void checkTextInSearchField(String text){
        String textFromField = searchModal.getTextFromSearchField();
        Assert.assertTrue(String.format("Search field does not contain %s", text), textFromField.contains(text));
    }

    @When("I input only spaces in search field")
    public void inputSpaces() {
        searchModal.inputSpace();
        searchModal.inputSpace();
        searchModal.applySearch();
    }

    @When("Edit data by adding {string} in search field and save it as {string}")
    public void editDataInSearchField(String textForEdit, String wordKey) {
        context.add(wordKey, textForEdit);
        searchModal.deleteSomeData();
        searchModal.deleteSomeData();
        searchModal.setSearchedText(textForEdit);
    }

    @Then("There is no possibility to search with empty search field")
    public void checkSearchingWithEmptyField() {
        if(AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
            Assert.assertFalse("Search button is clickable", searchModal.isSearchButtonClickable());
        }
        else {
            searchModal.applySearch();
            Assert.assertTrue("Search modal is present", catalogScreen.areCategoryRowsLoaded());
        }
    }

    @When("Return back from search modal")
    public void returnBack() {
        searchModal.closeSearchScreen();
    }

    @Then("Elements on Search screen are translated correctly")
    public void checkTranslationOnSearchScreen(){
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(searchModal.getTextFromBackButton()).as("Back button is not translated").isEqualTo(SpanishIos.BACK);
        softAssertions.assertThat(searchModal.getTextFromSearchField()).as("Search field is not translated").isEqualTo(SpanishIos.SEARCH);
        softAssertions.assertAll();
    }

    @Then("Elements on Search screen are translated correctly in Italian")
    public void checkTranslationOnSearchScreenIT(){
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(searchModal.getTextFromBackButton()).as("Back button is not translated").isEqualTo(ItalianIos.BACK);
        softAssertions.assertThat(searchModal.getTextFromSearchField()).as("Search field is not translated").isEqualTo(ItalianIos.SEARCH);
        softAssertions.assertAll();
    }

    @Then("Search modal is opened")
    public void checkSearchModalIsOpened() {
        Assert.assertTrue("Search modal is not loaded", searchModal.state().waitForDisplayed());
    }

    @When("Search for {string} and save bookName as {string}")
    public void searchFor(String searchedText, String bookNameInfoKey) {
        context.add(bookNameInfoKey, searchedText);
        Assert.assertTrue("Search modal is not present. Error (if present) - " + subcategoryScreen.getErrorMessage(), searchModal.state().isDisplayed());
        searchModal.setSearchedText(searchedText);
        searchModal.applySearch();
        Assert.assertTrue(String.format("Search results page for value '%s' is not present. Error (if present) - %s", searchedText, subcategoryScreen.getErrorMessage()), subcategoryScreen.state().waitForDisplayed());
    }

    @When("Search for a {} book from {string} and save as {string}")
    public void searchFromTheList(EnumBookType bookType, String listOfBooksKey, String bookKey) {
        List<String> books = context.get(listOfBooksKey);
        Assert.assertTrue("Search modal is not present. Error (if present) - " + subcategoryScreen.getErrorMessage(), searchModal.state().isDisplayed());

        Random random = new Random();
        int bookIndex = random.nextInt(books.size());
        String bookName = books.get(bookIndex);

        if(bookType == EnumBookType.AUDIOBOOK) {
            bookName = StringUtils.substringBefore(bookName, ". Audiobook.");
        }

        searchModal.setSearchedText(bookName);
        searchModal.applySearch();
        Assert.assertTrue(String.format("Search results page for value '%s' is not present. Error (if present) - %s", bookName, subcategoryScreen.getErrorMessage()), subcategoryScreen.state().waitForDisplayed());
        context.add(bookKey, bookName);
    }

    @Then("The search field is displayed and contains {string} book")
    public void checkTheDisplayingOfSearchField(String bookNameKey){
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(searchModal.isSearchLineDisplayed()).as("Search field is not displayed").isTrue();
        softAssertions.assertThat(searchModal.getTextFromSearchField()).as("Book name is not displayed").isEqualTo(context.get(bookNameKey));
        softAssertions.assertAll();
    }

    @When("Search several books and save them in list as {string}:")
    public void searchSeveralBooks(String listKey, List<String> listOfBooks) {
        List<String> savedBooks = new ArrayList<>();
        listOfBooks.forEach(book -> {
            savedBooks.add(book);
            searchModal.setSearchedText(book);
            searchModal.applySearch();
            Assert.assertTrue(String.format("Search results page for value '%s' is not present. Error (if present) - %s", book, subcategoryScreen.getErrorMessage()), subcategoryScreen.state().waitForDisplayed());
            catalogBooksScreen.clickActionButton(EnumActionButtonsForBooksAndAlertsKeys.GET, book);
            searchModal.clearSearchField();
        });
        context.add(listKey, savedBooks);
    }

    @When("Enter book {string} and save it as {string}")
    public void enterTheBook(String bookName, String bookNameKey) {
        context.add(bookNameKey, bookName);
        Assert.assertTrue("Search modal is not present. Error (if present) - " + subcategoryScreen.getErrorMessage(), searchModal.state().isDisplayed());
        searchModal.setSearchedText(bookName);
    }

    @When("I search random pdf and save as {string}")
    public void searchForPdf(String bookNameInfoKey) {
        String pdfForSearching = getRandomPdfWithoutBadSymbols();
        AqualityServices.getLogger().info("randomPdf: " + pdfForSearching);
        context.add(bookNameInfoKey, pdfForSearching);

        searchModal.setSearchedText(pdfForSearching);
        searchModal.applySearch();
        Assert.assertTrue(String.format("Search results page for value '%s' is not present. Error (if present) - %s", pdfForSearching, subcategoryScreen.getErrorMessage()), subcategoryScreen.state().waitForDisplayed());
    }

    @When("Clear search field on catalog books screen")
    public void clearSearchField() {
        searchModal.clearSearchField();
    }

    @Then("Search field is empty on catalog books screen")
    public void searchFieldIsEmpty() {
        searchModal.isSearchFieldEmpty();
    }

    @When("Enter word {} and save as {string} on catalog books screen")
    public void enterInfo(String word, String wordKey) {
        context.add(wordKey, word);
        Assert.assertTrue("Search modal is not present. Error (if present) - " + subcategoryScreen.getErrorMessage(), searchModal.state().isDisplayed());
        searchModal.setSearchedText(word);
        searchModal.applySearch();
    }

    private String getRandomPdfWithoutBadSymbols() {
        int amount = 0;
        String pdfName = null;

        while (amount < 15) {
            pdfName = GettingBookUtil.getRandomPdf();
            Pattern pattern = Pattern.compile("[^\\w :]");
            Matcher matcher = pattern.matcher(pdfName);
            amount++;
            if (!matcher.find()) {
                break;
            }
        }
        AqualityServices.getLogger().info("Count of attempts to get random pdf name without bad symbols-" + amount);

        return pdfName;
    }
}
