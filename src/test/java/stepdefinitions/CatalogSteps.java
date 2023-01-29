package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import constants.RegEx;
import constants.keysForContext.ScenarioContextKey;
import constants.localization.catalog.BookActionButtonNames;
import enums.localization.facetedsearch.FacetAvailabilityKeys;
import enums.localization.facetedsearch.FacetSortByKeys;
import enums.localization.translation.Spanish;
import framework.utilities.ScenarioContext;
import framework.utilities.ScreenshotUtils;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.catalog.form.MainCatalogToolbarForm;
import screens.catalog.screen.catalog.CatalogScreen;
import screens.facetedSearch.FacetedSearchScreen;
import screens.subcategory.SubcategoryScreen;

import java.util.*;
import java.util.stream.Collectors;

public class CatalogSteps {
    private final BottomMenuForm bottomMenuForm;
    private final CatalogScreen catalogScreen;
    private final SubcategoryScreen subcategoryScreen;
    private final MainCatalogToolbarForm mainCatalogToolbarForm;
    private final FacetedSearchScreen facetedSearchScreen;
    private final ScenarioContext context;

    @Inject
    public CatalogSteps(ScenarioContext context) {
        this.context = context;
        mainCatalogToolbarForm = AqualityServices.getScreenFactory().getScreen(MainCatalogToolbarForm.class);
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        catalogScreen = AqualityServices.getScreenFactory().getScreen(CatalogScreen.class);
        subcategoryScreen = AqualityServices.getScreenFactory().getScreen(SubcategoryScreen.class);
        facetedSearchScreen = AqualityServices.getScreenFactory().getScreen(FacetedSearchScreen.class);
    }

    @Then("Category rows are loaded")
    public void categoryRowsAreLoaded() {
        boolean isCategoryRowsPresent = catalogScreen.areCategoryRowsLoaded();
        Assert.assertTrue("Category rows are not loaded.", isCategoryRowsPresent);
    }

    @Then("Category names are correct on catalog book screen")
    public void isCategoryNamesCorrect() {
        Set<String > categoriesNames = catalogScreen.getAllCategoriesNames();
        categoriesNames.forEach(category -> Assert.assertTrue("Category name " + category + " have invalid symbols",
                category.matches(RegEx.VALID_SYMBOLS_IN_CATALOG_NAMES)));
    }

    @Then("Elements on Bottom menu are translated correctly")
    public void checkTranslationOnBottomMenu() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(bottomMenuForm.getTextFromCatalogBtn()).as("Catalog button is not translated").isEqualTo(Spanish.CATALOG.getDefaultLocalizedValue());
        softAssertions.assertThat(bottomMenuForm.getTextFromMyBooksBtn()).as("My Books button is not translated").isEqualTo(Spanish.MY_BOOKS.getDefaultLocalizedValue());
        softAssertions.assertThat(bottomMenuForm.getTextFromReservationsBtn()).as("Reservations button is not translated").isEqualTo(Spanish.RESERVATIONS.getDefaultLocalizedValue());
        softAssertions.assertThat(bottomMenuForm.getTextFromSettingsBtn()).as("Settings button is not translated").isEqualTo(Spanish.SETTINGS.getDefaultLocalizedValue());
        softAssertions.assertAll();
    }

    @Then("Elements on subcategory screen are translated correctly")
    public void checkTranslationOnSubcategoryScreen() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(subcategoryScreen.getTextFromBackBtn()).as("Back button is not translated").isEqualTo(Spanish.BACK.getDefaultLocalizedValue());
        softAssertions.assertThat(subcategoryScreen.getTextFromSortByTab()).as("Sort by tab is not translated").isEqualTo(Spanish.SORT_BY.getDefaultLocalizedValue());
        softAssertions.assertThat(subcategoryScreen.getTextFromAvailabilityTab()).as("Availability tab is not translated").isEqualTo(Spanish.AVAILABILITY.getDefaultLocalizedValue());
        softAssertions.assertThat(subcategoryScreen.getTextFromCollectionTab()).as("Collection tab is not translated").isEqualTo(Spanish.COLLECTION.getDefaultLocalizedValue());
        softAssertions.assertAll();
    }

    @Then("More button is present on each section of books on catalog book screen")
    public void isMoreBtnPresent() {
        Assert.assertTrue("More... button is not displayed on each section", catalogScreen.isMoreBtnPresent());
    }

    @When("I click More button from random book section and save name of section as {string} on catalog book screen")
    public void isMoreBtnClickable(String sectionNameKey) {
        context.add(sectionNameKey, catalogScreen.clickToMoreBtn());
    }

    @Then("Book section {string} is opened")
    public void isSectionOpened(String sectionNameKey) {
        String sectionName = context.get(sectionNameKey);
        Assert.assertTrue("Book section " + sectionName + " is not opened", catalogScreen.isBookSectionOpened(sectionName));
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
    public void openCatalog() {
        bottomMenuForm.open(BottomMenu.CATALOG);
        catalogScreen.state().waitForDisplayed();
    }

    @Then("Elements on Catalog screen are translated correctly")
    public void checkTranslationOnCatalogScreen() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(catalogScreen.getTextFromCatalogLbl()).as("Catalog label is not translated").isEqualTo(Spanish.CATALOG.getDefaultLocalizedValue());
        softAssertions.assertThat(catalogScreen.getTextFromMoreBtn()).as("More... button is not translated").isEqualTo(Spanish.MORE.getDefaultLocalizedValue());
        softAssertions.assertThat(catalogScreen.getTextFromAllTab()).as("All tab is not translated").isEqualTo(Spanish.ALL.getDefaultLocalizedValue());
        softAssertions.assertThat(catalogScreen.getTextFromEpubTab()).as("EBooks tab is not translated").isEqualTo(Spanish.EBOOKS.getDefaultLocalizedValue());
        softAssertions.assertThat(catalogScreen.getTextFromAudiobooksTab()).as("Audiobooks tab is not translated").isEqualTo(Spanish.AUDIOBOOKS.getDefaultLocalizedValue());
        softAssertions.assertAll();
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
        categoriesChain.forEach(categoryName -> {
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

    @When("I open first book in Subcategory List and save it as {string}")
    public void openFirstBookInSubcategoryListAndSaveIt(String bookInfoKey) {
        context.add(bookInfoKey, subcategoryScreen.getFirstBookInfo());
        subcategoryScreen.openFirstBook();
    }

    @When("I switch to {string} catalog tab")
    public void switchToCatalogTab(String catalogTab) {
        catalogScreen.switchToCatalogTab(catalogTab);
        catalogScreen.state().waitForDisplayed();
    }

    @Then("All present books are audiobooks")
    public void checkAllPresentBooksAreAudiobooks() {
        Assert.assertTrue("Not all present books are audiobooks", catalogScreen.getListOfBooksNames().stream().allMatch(x -> x.toLowerCase().contains("audiobook")));
    }

    @And("I sort books by {} in {string}")
    public void sortBooksBy(FacetSortByKeys sortingCategory, String library) {
        facetedSearchScreen.sortBy(library);
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

    @When("I open Sort by on catalog screen")
    public void openSortBy(){
        facetedSearchScreen.openSortBy();
    }

    @Then("Elements on Sort by tab are translated correctly")
    public void checkTranslationOnSortByTab() {
        List<String> options = facetedSearchScreen.getOptionsInTabs();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(options.get(0)).as("Title is not translated").isEqualTo(Spanish.TITLE.getDefaultLocalizedValue());
        softAssertions.assertThat(options.get(1)).as("Recently added is not translated").isEqualTo(Spanish.RECENTLY_ADDED.getDefaultLocalizedValue());
        softAssertions.assertThat(options.get(2)).as("Author is not translated").isEqualTo(Spanish.AUTHOR.getDefaultLocalizedValue());
        softAssertions.assertAll();
    }

    @Then("Books are sorted by Author ascending")
    public void checkBooksAreSortedByAuthorAscending() {
        List<String> list = subcategoryScreen.getAuthorsInfo();
        List<String> listOfSurnames = getSurnames(list);
        Assert.assertEquals("Lists of authors is not sorted properly " + list.stream().map(Object::toString).collect(Collectors.joining(", ")), getSurnames(listOfSurnames.stream().sorted().collect(Collectors.toList())), listOfSurnames);
    }

    @Then("Books are sorted by Author by default on subcategory screen in {string}")
    public void isSortedByDefaultInPalace(String libraryName) {
        if(AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
            Assert.assertEquals("Books are not sorted by default", "Author", subcategoryScreen.getNameOfSorting(libraryName));
        }
        Assert.assertEquals("Books are not sorted by default", "Author", subcategoryScreen.getNameOfSorting(libraryName));
    }

    @Then("There are sorting by {string}, {string} and {string} in {string} on subcategory screen")
    public void checkTypeOfSorting(String type1, String type2, String type3, String library) {
        facetedSearchScreen.sortBy(library);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(facetedSearchScreen.getTypeVariantsOfBtn(type1)).as("There is no sorting type by " + type1).isEqualTo(type1);
        softAssertions.assertThat(facetedSearchScreen.getTypeVariantsOfBtn(type2)).as("There is no sorting type by " + type2).isEqualTo(type2);
        softAssertions.assertThat(facetedSearchScreen.getTypeVariantsOfBtn(type3)).as("There is no sorting type by " + type3).isEqualTo(type3);
        softAssertions.assertAll();
    }

    @When("I open Availability on catalog screen")
    public void openAvailability() {
        facetedSearchScreen.openAvailabilityMenu();
    }

    @Then("Elements on Availability tab are translated correctly")
    public void checkTranslationOnAvailabilityTab() {
        List<String> options = facetedSearchScreen.getOptionsInTabs();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(options.get(0)).as("Available now is not translated").isEqualTo(Spanish.AVAILABLE_NOW.getDefaultLocalizedValue());
        softAssertions.assertThat(options.get(1)).as("Yours to keep is not translated").isEqualTo(Spanish.YOURS_TO_KEEP.getDefaultLocalizedValue());
        softAssertions.assertThat(options.get(2)).as("All is not translated").isEqualTo(Spanish.ALL.getDefaultLocalizedValue());
        softAssertions.assertAll();
    }

    @Then("The book availability is ALL by default on subcategory screen")
    public void isAvailabilityByDefault() {
        Assert.assertEquals("Book availability is not by default", "All", subcategoryScreen.getAvailability());
    }

    @Then("There are availability by {string}, {string} and {string} on subcategory screen")
    public void checkTypeOfAvailability(String type1, String type2, String type3) {
        facetedSearchScreen.openAvailabilityMenu();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(facetedSearchScreen.getTypeVariantsOfBtn(type1)).as("There is no sorting type by " + type1).isEqualTo(type1);
        softAssertions.assertThat(facetedSearchScreen.getTypeVariantsOfBtn(type2)).as("There is no sorting type by " + type2).isEqualTo(type2);
        softAssertions.assertThat(facetedSearchScreen.getTypeVariantsOfBtn(type3)).as("There is no sorting type by " + type3).isEqualTo(type3);
        softAssertions.assertAll();
    }

    @When("I open Collection on catalog screen")
    public void openCollection(){
        facetedSearchScreen.openCollection();
    }

    @Then("Elements on Collection tab are translated correctly")
    public void checkTranslationOnCollectionTab() {
        List<String> options = facetedSearchScreen.getOptionsInTabs();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(options.get(0)).as("Popular Books is not translated").isEqualTo(Spanish.POPULAR_BOOKS.getDefaultLocalizedValue());
        softAssertions.assertThat(options.get(1)).as("Everything is not translated").isEqualTo(Spanish.EVERYTHING.getDefaultLocalizedValue());
        softAssertions.assertAll();
    }

    @When("Collections is Everything by default on subcategory screen")
    public void isCollectionsByDefault() {
        Assert.assertEquals("Collection type is not by default", "Everything", subcategoryScreen.getCollectionName());
    }

    @Then("There are collection type by {string} and {string} on subcategory screen")
    public void checkTypeOfCollection(String type1, String type2) {
        facetedSearchScreen.openCollection();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(facetedSearchScreen.getTypeVariantsOfBtn(type1)).as("There is no sorting type by " + type1).isEqualTo(type1);
        softAssertions.assertThat(facetedSearchScreen.getTypeVariantsOfBtn(type2)).as("There is no sorting type by " + type2).isEqualTo(type2);
        softAssertions.assertAll();
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

    @When("I change books visibility to show {}")
    @And("Change books visibility to show {}")
    public void checkThatActionButtonTextEqualToExpected(FacetAvailabilityKeys facetAvailabilityKeys) {
        facetedSearchScreen.openAvailabilityMenu();
        facetedSearchScreen.changeAvailabilityTo(facetAvailabilityKeys);
    }

    @When("I tap Back button on subcategory screen")
    public void tapBackBtn() {
        subcategoryScreen.tapBack();
    }

    @Then("There are types {string}, {string} and {string} of books on catalog book screen:")
    public void checkTypesOfBook(String type1, String type2, String type3) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(catalogScreen.getTheNameOfBookTypeBtn(type1)).as("There is no " + type1 + " book type section ").isEqualTo(type1);
        softAssertions.assertThat(catalogScreen.getTheNameOfBookTypeBtn(type2)).as("There is no " + type2 + " book type section ").isEqualTo(type2);
        softAssertions.assertThat(catalogScreen.getTheNameOfBookTypeBtn(type3)).as("There is no " + type3 + " book type section ").isEqualTo(type3);
    }

    @Then("Section with books of {string} type is opened on catalog book screen")
    public void isSectionIsOpened(String type) {
        Assert.assertTrue("Section with books " + type + " type are not opened", catalogScreen.isSectionWithBookTypeOpen(type));
    }
}