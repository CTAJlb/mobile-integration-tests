package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import constants.application.EnumBookType;
import constants.keysForContext.ScenarioContextKey;
import constants.localization.application.catalog.BookActionButtonNames;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import constants.localization.application.facetedSearch.FacetAvailabilityKeys;
import constants.localization.application.facetedSearch.FacetSortByKeys;
import framework.utilities.ScenarioContext;
import framework.utilities.ScreenshotUtils;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import screens.alert.AlertScreen;
import screens.audioplayer.AudioPlayerScreen;
import screens.bookDetails.BookDetailsScreen;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.catalog.form.MainCatalogToolbarForm;
import screens.catalog.screen.books.CatalogBooksScreen;
import screens.catalog.screen.catalog.CatalogScreen;
import screens.epubreader.EpubReaderScreen;
import screens.facetedSearch.FacetedSearchScreen;
import screens.subcategory.SubcategoryScreen;

import java.util.*;
import java.util.stream.Collectors;

public class CatalogSteps extends BaseSteps {
    protected final BottomMenuForm bottomMenuForm;
    protected final CatalogScreen catalogScreen;
    protected final SubcategoryScreen subcategoryScreen;
    protected final BookDetailsScreen bookDetailsScreen;
    protected final MainCatalogToolbarForm mainCatalogToolbarForm;
    protected final CatalogBooksScreen catalogBooksScreen;
    protected final FacetedSearchScreen facetedSearchScreen;
    protected final AudioPlayerScreen audioPlayerScreen;
    protected final EpubReaderScreen epubReaderScreen;
    protected final ScenarioContext context;
    private final AlertScreen alertScreen;

    @Inject
    public CatalogSteps(ScenarioContext context) {
        this.context = context;
        mainCatalogToolbarForm = AqualityServices.getScreenFactory().getScreen(MainCatalogToolbarForm.class);
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        catalogScreen = AqualityServices.getScreenFactory().getScreen(CatalogScreen.class);
        bookDetailsScreen = AqualityServices.getScreenFactory().getScreen(BookDetailsScreen.class);
        subcategoryScreen = AqualityServices.getScreenFactory().getScreen(SubcategoryScreen.class);
        catalogBooksScreen = AqualityServices.getScreenFactory().getScreen(CatalogBooksScreen.class);
        facetedSearchScreen = AqualityServices.getScreenFactory().getScreen(FacetedSearchScreen.class);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);
        audioPlayerScreen = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen.class);
        epubReaderScreen = AqualityServices.getScreenFactory().getScreen(EpubReaderScreen.class);
    }

    @Then("Category rows are loaded")
    public void categoryRowsAreLoaded() {
        boolean isCategoryRowsPresent = catalogScreen.areCategoryRowsLoaded();
        Assert.assertTrue("Category rows are not loaded.", isCategoryRowsPresent);
    }

    @Then("Subcategory rows are loaded")
    public void subcategoryRowsAreLoaded() {
        boolean isSubcategoryRowsPresent = subcategoryScreen.areSubcategoryRowsLoaded();
        Assert.assertTrue("Subcategory rows are not loaded.", isSubcategoryRowsPresent);
    }

    @When("I get names of books on screen and save them as {string}")
    public void getNamesOfBooksAndSaveThem(String booksNamesListKey) {
        context.add(booksNamesListKey, catalogScreen.getListOfBooksNames());
    }

    @Then("List of books on screen is not equal to list of books saved as {string}")
    public void checkListOfBooksIsNotEqualToSavedListOfBooks(String booksNamesListKey) {
        List<String> expectedList = context.get(booksNamesListKey);
        Assert.assertNotEquals("Lists of books are equal" + expectedList.stream().map(Object::toString).collect(Collectors.joining(", ")), expectedList, catalogScreen.getListOfBooksNames());
    }

    @And("I switch to {string} from side menu")
    public void openLibraryFromSideMenu(String libraryName) {
        bottomMenuForm.open(BottomMenu.CATALOG);
        mainCatalogToolbarForm.chooseAnotherLibrary();
        catalogScreen.selectLibraryFromListOfAddedLibraries(libraryName);
    }

    @And("I open Catalog")
    @Given("Catalog is opened")
    public void openCatalogWithAgeCheck() {
        bottomMenuForm.open(BottomMenu.CATALOG);
    }

    @And("Library {string} is present on Catalog Screen")
    public void isLibraryPresentOnCatalogScreen(String libraryName) {
        Assert.assertTrue(String.format("Library %s is not present on Catalog Screen", libraryName), catalogScreen.isLibraryPresent(libraryName));
    }

    @And("I open {string} category")
    public void openCategory(String categoryName) {
        catalogScreen.state().waitForDisplayed();
        catalogScreen.openCategory(categoryName);
    }

    @And("I open {string} subcategory")
    public void openSubcategory(String subCategoryName) {
        subcategoryScreen.state().waitForDisplayed();
        subcategoryScreen.openCategory(subCategoryName);
    }

    @And("Subcategory name is {string}")
    public void checkCurrentCategoryName(String expectedCategoryName) {
        Assert.assertTrue(String.format("Current category name is not correct. Expected '%1$s' but found '%2$s'", expectedCategoryName, mainCatalogToolbarForm.getCategoryName()), AqualityServices.getConditionalWait().waitFor(() -> mainCatalogToolbarForm.getCategoryName().equals(expectedCategoryName), "Wait while category become correct."));
    }

    @Then("Subcategory screen is present")
    public void checkSubcategoryScreenIsPresent() {
        boolean isScreenPresent = subcategoryScreen.state().waitForDisplayed();
        if (!isScreenPresent && subcategoryScreen.isErrorButtonPresent()) {
            Scenario scenario = context.get(ScenarioContextKey.SCENARIO_KEY);
            scenario.attach(ScreenshotUtils.getScreenshot(), "image/png", "error_screenshot.png");
        }
        Assert.assertTrue("Subcategory screen is not present. Error (if present) - " + subcategoryScreen.getErrorMessage(), isScreenPresent);
    }

    @And("Following subcategories are present:")
    public void checkFollowingSubcategoriesArePresent(List<String> expectedValuesList) {
        catalogScreen.state().waitForDisplayed();
        Assert.assertEquals("List of categories is not completely present", new HashSet<>(expectedValuesList), catalogScreen.getAllCategoriesNames());
    }

    @When("I open categories by chain and chain starts from CategoryScreen:")
    public void openCategoriesByChainAndChainStartsFromCategoryScreen(List<String> categoriesChain) {
        categoriesChain.stream().forEach(categoryName -> {
            if (catalogScreen.state().waitForDisplayed()) {
                catalogScreen.openCategory(categoryName);
            } else {
                subcategoryScreen.state().waitForDisplayed();
                subcategoryScreen.openCategory(categoryName);
            }
        });
    }

    @And("Count of books in first lane is more than {int}")
    public void checkCountOfBooksInFirstLaneIsMoreThan(int countOfBooks) {
        Assert.assertTrue("Count of books is smaller than " + countOfBooks, countOfBooks <= catalogScreen.getListOfAllBooksNamesInFirstLane().size());
    }

    @Then("Book {string} is opened")
    public void checkBookInfoIsOpened(String bookInfoKey) {
        Assert.assertEquals("Expected book is not opened", Optional.ofNullable(context.get(bookInfoKey)).orElse(bookInfoKey), bookDetailsScreen.getBookInfo());
    }

    @When("I open first book in Subcategory List and save it as {string}")
    public void openFirstBookInSubcategoryListAndSaveIt(String bookInfoKey) {
        context.add(bookInfoKey, subcategoryScreen.getFirstBookInfo());
        subcategoryScreen.openFirstBook();
    }

    @When("I switch to {string} catalog tab")
    public void switchToCatalogTab(String catalogTab) {
        catalogScreen.switchToCatalogTab(catalogTab);
    }

    @Then("All present books are audiobooks")
    public void checkAllPresentBooksAreAudiobooks() {
        Assert.assertTrue("Not all present books are audiobooks", catalogScreen.getListOfBooksNames().stream().allMatch(x -> x.toLowerCase().contains("audiobook")));
    }

    @And("I sort books by {}")
    public void sortBooksBy(FacetSortByKeys sortingCategory) {
        facetedSearchScreen.sortBy();
        facetedSearchScreen.changeSortByTo(sortingCategory);
    }

    @When("I save list of books as {string}")
    public void saveListOfBooks(String booksInfoKey) {
        context.add(booksInfoKey, subcategoryScreen.getBooksInfo());
    }

    @Then("All books can be downloaded")
    public void checkAllBooksCanBeDownloaded() {
        Assert.assertTrue("Not all present books can be downloaded", subcategoryScreen.getAllButtonsNames()
                .stream()
                .allMatch(x -> x.equals(BookActionButtonNames.DOWNLOAD_BUTTON_NAME)));
    }

    @Then("All books can be loaned or downloaded")
    public void checkAllBooksCanBeLoanedOrDownloaded() {
        Assert.assertTrue("Not all present books can be loaned or downloaded", subcategoryScreen.getAllButtonsNames()
                .stream()
                .allMatch(x -> x.equals(BookActionButtonNames.GET_BUTTON_NAME) || x.equals(BookActionButtonNames.DOWNLOAD_BUTTON_NAME)));
    }

    @Then("List of books on subcategory screen is not equal to list of books saved as {string}")
    public void checkListOfBooksOnSubcategoryScreenIsNotEqualToListOfSavedBooks(String booksNamesListKey) {
        List<String> expectedList = context.get(booksNamesListKey);
        Assert.assertNotEquals("Lists of books are equal" + expectedList.stream().map(Object::toString).collect(Collectors.joining(", ")), expectedList, subcategoryScreen.getBooksInfo());
    }

    @Then("Books are sorted by Author ascending")
    public void checkBooksAreSortedByAuthorAscending() {
        List<String> list = subcategoryScreen.getAuthorsInfo();
        List<String> listOfSurnames = getSurnames(list);
        Assert.assertEquals("Lists of authors is not sorted properly " + list.stream().map(Object::toString).collect(Collectors.joining(", ")), getSurnames(listOfSurnames.stream().sorted().collect(Collectors.toList())), listOfSurnames);
    }

    private List<String> getSurnames(List<String> list) {
        List<String> listOfSurnames = new ArrayList<>();
        for (String authorName : list) {
            String[] separatedName = authorName.split("\\s");
            List<String> sortedNames = Arrays.stream(separatedName).sorted().collect(Collectors.toList());
            listOfSurnames.add(sortedNames.get(0));
        }
        return listOfSurnames;
    }

    @Then("Books are sorted by Title ascending")
    public void booksAreSortedByTitleAscending() {
        List<String> list = subcategoryScreen.getTitlesInfo();
        Assert.assertEquals("Lists of authors is not sorted properly" + list.stream().map(Object::toString).collect(Collectors.joining(", ")), list.stream().sorted().collect(Collectors.toList()), list);
    }

    @Then("I check that book contains {} action button on book details view")
    public void checkThatBookContainsButtonWithDefiniteActionOnBookDetailsView(final EnumActionButtonsForBooksAndAlertsKeys key) {
        boolean isButtonPresent = bookDetailsScreen.isBookAddButtonTextEqualTo(key);
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

    @When("I press on the book details view at the action button {}")
    public void pressOnBookDetailsScreenAtActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        clickActionButtonOnBookDetailsView(actionButtonKey);
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && alertScreen.state().waitForDisplayed()) {
            if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.CANCEL_RESERVATION) {
                alertScreen.waitAndPerformAlertActionIfDisplayed(actionButtonKey);
            } else {
                AqualityServices.getApplication().getDriver().switchTo().alert().dismiss();
                AqualityServices.getLogger().info("Alert appears and dismiss alert");
            }
        }
    }

    private void clickActionButtonOnBookDetailsView(EnumActionButtonsForBooksAndAlertsKeys actionButton) {
        bookDetailsScreen.clickActionButton(actionButton);
    }

    @When("I change books visibility to show {}")
    @And("Change books visibility to show {}")
    public void checkThatActionButtonTextEqualToExpected(FacetAvailabilityKeys facetAvailabilityKeys) {
        facetedSearchScreen.openAvailabilityMenu();
        facetedSearchScreen.changeAvailabilityTo(facetAvailabilityKeys);
    }

    @And("I close Book Details for IOSTab")
    public void closeBookDetailsOnlyForIOSTab() {
        bookDetailsScreen.closeBookDetailsOnlyForIOSTabIfDisplayed();
    }

    @When("I start reading or listening to a book with {} type from book details view")
    public void openGivenTypeBookReader(EnumBookType bookType) {
        switch (bookType) {
            case EBOOK:
                clickActionButtonOnBookDetailsView(EnumActionButtonsForBooksAndAlertsKeys.READ);
                epubReaderScreen.state().waitForDisplayed();
                break;
            case AUDIOBOOK:
                clickActionButtonOnBookDetailsView(EnumActionButtonsForBooksAndAlertsKeys.LISTEN);
                audioPlayerScreen.state().waitForDisplayed();
                break;
        }
    }
}