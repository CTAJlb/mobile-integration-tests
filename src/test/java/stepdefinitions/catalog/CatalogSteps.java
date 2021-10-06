package stepdefinitions.catalog;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import constants.application.EnumBookType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import constants.localization.application.facetedSearch.FacetAvailabilityKeys;
import constants.localization.application.facetedSearch.FacetSortByKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.android.BookDetailsScreenInformationBlockModel;
import stepdefinitions.BaseSteps;
import stepdefinitions.catalog.components.AbstractCatalogSteps;
import stepdefinitions.catalog.components.ICatalogSteps;

import java.util.List;

public class CatalogSteps extends BaseSteps implements ICatalogSteps {
    private AbstractCatalogSteps catalogSteps;
    private ScenarioContext context;

    @Inject
    public CatalogSteps(ScenarioContext context) {
        this.context = context;
        catalogSteps = stepsFactory.getSteps(AbstractCatalogSteps.class, context);
    }

    @Then("Category rows are loaded")
    public void categoryRowsAreLoaded() {
        catalogSteps.categoryRowsAreLoaded();
    }

    @Then("Subcategory rows are loaded")
    public void subcategoryRowsAreLoaded() {
        catalogSteps.subcategoryRowsAreLoaded();
    }

    @When("I get names of books on screen and save them as {string}")
    public void getNamesOfBooksAndSaveThem(String booksNamesListKey) {
        catalogSteps.getNamesOfBooksAndSaveThem(booksNamesListKey);
    }

    @When("I {} hardcode book {string} and save it as {string}")
    public void performActionOnHardcodeBookByNameAndSaveIt(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookName, String bookInfoKey) {
        catalogSteps.performActionOnHardcodeBookByNameAndSaveIt(actionButtonKey, bookName, bookInfoKey);
    }

    @Then("List of books on screen is not equal to list of books saved as {string}")
    public void checkListOfBooksIsNotEqualToSavedListOfBooks(String booksNamesListKey) {
        catalogSteps.checkListOfBooksIsNotEqualToSavedListOfBooks(booksNamesListKey);
    }

    @And("I switch to {string} from side menu")
    public void openLibraryFromSideMenu(String libraryName) {
        catalogSteps.openLibraryFromSideMenu(libraryName);
    }

    @And("I open Catalog")
    @Given("Catalog is opened")
    public void openCatalogWithAgeCheck() {
        catalogSteps.openCatalogWithAgeCheck();
    }

    @And("Library {string} is present on Catalog Screen")
    public void isLibraryPresentOnCatalogScreen(String libraryName) {
        catalogSteps.isLibraryPresentOnCatalogScreen(libraryName);
    }

    @And("I open {string} category")
    public void openCategory(String categoryName) {
        catalogSteps.openCategory(categoryName);
    }

    @And("I open {string} subcategory")
    public void openSubcategory(String subCategoryName) {
        catalogSteps.openSubcategory(subCategoryName);
    }

    @Then("Current category name is {string}")
    @And("Subcategory name is {string}")
    public void checkCurrentCategoryName(String expectedCategoryName) {
        catalogSteps.checkCurrentCategoryName(expectedCategoryName);
    }

    @Then("Subcategory screen is present")
    public void checkSubcategoryScreenIsPresent() {
        catalogSteps.checkSubcategoryScreenIsPresent();
    }

    @And("Following subcategories are present:")
    public void checkFollowingSubcategoriesArePresent(List<String> expectedValuesList) {
        catalogSteps.checkFollowingSubcategoriesArePresent(expectedValuesList);
    }

    @When("I open categories by chain and chain starts from CategoryScreen:")
    public void openCategoriesByChainAndChainStartsFromCategoryScreen(List<String> categoriesChain) {
        catalogSteps.openCategoriesByChainAndChainStartsFromCategoryScreen(categoriesChain);
    }

    @When("I {} book of {string} type and save it as {string}")
    @And("{} book of {string} type and save it as {string}")
    public void performActionOnBookOfTypeAndSaveIt(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookType, String bookInfoKey) {
        catalogSteps.performActionOnBookOfTypeAndSaveIt(actionButtonKey, bookType, bookInfoKey);
    }

    @When("I {} or {} book by name {string} and save it as {string}")
    public void performGetOrDownloadActionOnBookByNameFromAPIAndSaveIt(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey1, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey2, String bookNameInfoKey, String bookInfoKey) {
        catalogSteps.performGetOrDownloadActionOnBookByNameFromAPIAndSaveIt(actionButtonKey1, actionButtonKey2, bookNameInfoKey, bookInfoKey);
    }

    @When("I {} book from Subcategory List View with title {string} and save it as {string}")
    public void performActionOnSpecificBookFromAPIAndSaveBookInfoOnSubcategoryListView(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookNameInfoKey, String bookInfoKey) {
        catalogSteps.performActionOnSpecificBookFromAPIAndSaveBookInfoOnSubcategoryListView(actionButtonKey, bookNameInfoKey, bookInfoKey);
    }

    @When("Open {string} book with {} button from Subcategory List View with title {string} and save it as {string}")
    public void openBookWithDefiniteActionButtonAndDefiniteNameAndDefiniteBookTypeFromAPIOAndSaveBookInfo(String bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookNameKey, String bookInfoKey) {
        String bookName = context.get(bookNameKey);
        catalogSteps.openBookWithDefiniteActionButtonAndDefiniteNameAndDefiniteBookTypeFromAPIOAndSaveBookInfo(bookName, actionButtonKey, bookInfoKey, bookType);
    }

    @When("I click {} button on the {string} book on Subcategory List view")
    public void performActionOnBookOnSubcategoryListView(EnumActionButtonsForBooksAndAlertsKeys bookActionButtonKeys, String bookInfoKey) {
        catalogSteps.performActionOnBookOnSubcategoryListView(bookActionButtonKeys, bookInfoKey);
    }

    @And("Count of books in first lane is more than {int}")
    public void checkCountOfBooksInFirstLaneIsMoreThan(int countOfBooks) {
        catalogSteps.checkCountOfBooksInFirstLaneIsMoreThan(countOfBooks);
    }

    @Then("Book {string} is opened")
    public void checkBookInfoIsOpened(String bookInfoKey) {
        catalogSteps.checkBookInfoIsOpened(bookInfoKey);
    }

    @When("I open first book in Subcategory List and save it as {string}")
    public void openFirstBookInSubcategoryListAndSaveIt(String bookInfoKey) {
        catalogSteps.openFirstBookInSubcategoryListAndSaveIt(bookInfoKey);
    }

    @When("I switch to {string} catalog tab")
    public void switchToCatalogTab(String catalogTab) {
        catalogSteps.switchToCatalogTab(catalogTab);
    }

    @Then("All present books are audiobooks")
    public void checkAllPresentBooksAreAudiobooks() {
        catalogSteps.checkAllPresentBooksAreAudiobooks();
    }

    @And("I sort books by {}")
    public void sortBooksBy(FacetSortByKeys sortingCategory) {
        catalogSteps.sortBooksBy(sortingCategory);
    }

    @When("I save list of books as {string}")
    public void saveListOfBooks(String booksInfoKey) {
        catalogSteps.saveListOfBooks(booksInfoKey);
    }

    @Then("All books can be downloaded")
    public void checkAllBooksCanBeDownloaded() {
        catalogSteps.checkAllBooksCanBeDownloaded();
    }

    @Then("All books can be loaned or downloaded")
    public void checkAllBooksCanBeLoanedOrDownloaded() {
        catalogSteps.checkAllBooksCanBeLoanedOrDownloaded();
    }

    @Then("List of books on subcategory screen is not equal to list of books saved as {string}")
    public void checkListOfBooksOnSubcategoryScreenIsNotEqualToListOfSavedBooks(String booksNamesListKey) {
        catalogSteps.checkListOfBooksOnSubcategoryScreenIsNotEqualToListOfSavedBooks(booksNamesListKey);
    }

    @Then("Books are sorted by Author ascending")
    public void checkBooksAreSortedByAuthorAscending() {
        catalogSteps.checkBooksAreSortedByAuthorAscending();
    }

    @Then("Books are sorted by Title ascending")
    public void booksAreSortedByTitleAscending() {
        catalogSteps.booksAreSortedByTitleAscending();
    }

    @Then("Book saved as {string} should contain {} button on Subcategory List View")
    public void checkThatSavedBookContainButtonAtCatalogBooksScreen(
            final String bookInfoKey, final EnumActionButtonsForBooksAndAlertsKeys key) {
        catalogSteps.checkThatSavedBookContainButtonAtCatalogBooksScreen(bookInfoKey, key);
    }

    @Then("I check that book contains {} action button on book details view")
    public void checkThatBookContainsButtonWithDefiniteActionOnBookDetailsView(final EnumActionButtonsForBooksAndAlertsKeys key) {
        catalogSteps.checkThatBookContainsButtonWithDefiniteActionOnBookDetailsView(key);
    }

    @When("I open {string} book with {} action button on Subcategory Screen")
    public void openSpecificBookWithSpecificActionButton(String bookInfoKey, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        catalogSteps.openSpecificBookWithSpecificActionButton(bookInfoKey, actionButtonKey);
    }

    @When("I press on the book details view at the action button {}")
    public void pressOnBookDetailsScreenAtActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButton) {
        catalogSteps.pressOnBookDetailsScreenAtActionButton(actionButton);
    }

    @When("I change books visibility to show {}")
    @And("Change books visibility to show {}")
    public void checkThatActionButtonTextEqualToExpected(FacetAvailabilityKeys facetAvailabilityKeys) {
        catalogSteps.checkThatActionButtonTextEqualToExpected(facetAvailabilityKeys);
    }

    @And("I close Book Details for IOSTab")
    public void closeBookDetailsOnlyForIOSTab() {
        catalogSteps.closeBookDetailsOnlyForIOSTab();
    }

    @When("I start reading or listening to a book with {} type from book details view")
    public void openGivenTypeBookReader(EnumBookType bookType) {
        catalogSteps.startReadingOrListeningToBookWithSpecifyTypeOnBookDetailsView(bookType);
    }

    @When("I open first present category")
    public void openFirstPresentCategory() {
        catalogSteps.openFirstCategory();
    }
}