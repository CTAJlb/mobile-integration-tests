package stepdefinitions.catalog.components;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import constants.application.EnumBookType;
import constants.keysForContext.ScenarioContextKey;
import constants.localization.application.catalog.BookActionButtonNames;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import constants.localization.application.facetedSearch.FacetAvailabilityKeys;
import constants.localization.application.facetedSearch.FacetSortByKeys;
import framework.utilities.ScenarioContext;
import framework.utilities.ScreenshotUtils;
import io.cucumber.java.Scenario;
import models.android.BookDetailsScreenInformationBlockModel;
import models.android.CatalogBookModel;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
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
import stepdefinitions.BaseSteps;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractCatalogSteps extends BaseSteps implements ICatalogSteps {
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

    public AbstractCatalogSteps(ScenarioContext context) {
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

    @Override
    public void categoryRowsAreLoaded() {
        boolean isCategoryRowsPresent = catalogScreen.areCategoryRowsLoaded();
        Assert.assertTrue("Category rows are not loaded.", isCategoryRowsPresent);
    }

    @Override
    public void subcategoryRowsAreLoaded() {
        boolean isSubcategoryRowsPresent = subcategoryScreen.areSubcategoryRowsLoaded();
        Assert.assertTrue("Subcategory rows are not loaded.", isSubcategoryRowsPresent);
    }

    @Override
    public void getNamesOfBooksAndSaveThem(String booksNamesListKey) {
        context.add(booksNamesListKey, catalogScreen.getListOfBooksNames());
    }

    @Override
    public void checkListOfBooksIsNotEqualToSavedListOfBooks(String booksNamesListKey) {
        List<String> expectedList = context.get(booksNamesListKey);
        Assert.assertNotEquals("Lists of books are equal" + expectedList.stream().map(Object::toString).collect(Collectors.joining(", ")), expectedList, catalogScreen.getListOfBooksNames());
    }

    @Override
    public void openLibraryFromSideMenu(String libraryName) {
        bottomMenuForm.open(BottomMenu.CATALOG);
        mainCatalogToolbarForm.chooseAnotherLibrary();
        catalogScreen.selectLibraryFromListOfAddedLibraries(libraryName);
    }

    @Override
    public void openCatalogWithAgeCheck() {
        bottomMenuForm.open(BottomMenu.CATALOG);
    }

    @Override
    public void isLibraryPresentOnCatalogScreen(String libraryName) {
        Assert.assertTrue(String.format("Library %s is not present on Catalog Screen", libraryName), catalogScreen.isLibraryPresent(libraryName));
    }

    @Override
    public void openCategory(String categoryName) {
        catalogScreen.state().waitForDisplayed();
        catalogScreen.openCategory(categoryName);
    }

    @Override
    public void openSubcategory(String subCategoryName) {
        subcategoryScreen.state().waitForDisplayed();
        subcategoryScreen.openCategory(subCategoryName);
    }

    public abstract void checkCurrentCategoryName(String expectedCategoryName);

    @Override
    public void checkSubcategoryScreenIsPresent() {
        boolean isScreenPresent = subcategoryScreen.state().waitForDisplayed();
        if (!isScreenPresent && subcategoryScreen.isErrorButtonPresent()) {
            Scenario scenario = context.get(ScenarioContextKey.SCENARIO_KEY);
            scenario.attach(ScreenshotUtils.getScreenshot(), "image/png", "error_screenshot.png");
        }
        Assert.assertTrue("Subcategory screen is not present. Error (if present) - " + subcategoryScreen.getErrorMessage(), isScreenPresent);
    }

    @Override
    public void checkFollowingSubcategoriesArePresent(List<String> expectedValuesList) {
        catalogScreen.state().waitForDisplayed();
        Assert.assertEquals("List of categories is not completely present", new HashSet<>(expectedValuesList), catalogScreen.getAllCategoriesNames());
    }

    @Override
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

    @Override
    public void performActionOnBookOfTypeAndSaveIt(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookType, String bookInfoKey) {
        context.add(bookInfoKey, catalogBooksScreen.scrollToBookByTypeAndClickActionButton(actionButtonKey, bookType));
    }

    @Override
    public void performGetOrDownloadActionOnBookByNameFromAPIAndSaveIt(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey1, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey2, String bookNameInfoKey, String bookInfoKey) {
        String bookName = context.get(bookNameInfoKey);
        context.add(bookInfoKey, catalogBooksScreen.scrollToBookByNameAndClickGetOrDownloadActionButton(actionButtonKey1, actionButtonKey2, bookName));
    }

    @Override
    public void performActionOnSpecificBookFromAPIAndSaveBookInfoOnSubcategoryListView(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookNameInfoKey, String bookInfoKey) {
        subcategoryScreen.state().waitForDisplayed();
        String bookName = context.get(bookNameInfoKey);
        context.add(bookInfoKey, catalogBooksScreen.scrollToBookByNameAndClickActionButton(actionButtonKey, bookName));
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && alertScreen.state().waitForDisplayed()) {
            if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.CANCEL_RESERVATION){
                alertScreen.waitAndPerformAlertActionIfDisplayed(actionButtonKey);
            }else {
                AqualityServices.getApplication().getDriver().switchTo().alert().dismiss();
                AqualityServices.getLogger().info("Alert appears and dismiss alert");
            }
        }
    }

    @Override
    public void performActionOnBookOnSubcategoryListView(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        catalogBooksScreen.clickBookByTitleButtonWithKey(catalogBookModel.getTitle(), actionButtonKey);
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
            alertScreen.waitAndPerformAlertActionIfDisplayed(actionButtonKey);
        }
    }

    @Override
    public void checkCountOfBooksInFirstLaneIsMoreThan(int countOfBooks) {
        Assert.assertTrue("Count of books is smaller than " + countOfBooks, countOfBooks <= catalogScreen.getListOfAllBooksNamesInFirstLane().size());
    }

    @Override
    public void checkBookInfoIsOpened(String bookInfoKey) {
        Assert.assertEquals("Expected book is not opened", Optional.ofNullable(context.get(bookInfoKey)).orElse(bookInfoKey), bookDetailsScreen.getBookInfo());
    }

    @Override
    public void openFirstBookInSubcategoryListAndSaveIt(String bookInfoKey) {
        context.add(bookInfoKey, subcategoryScreen.getFirstBookInfo());
        subcategoryScreen.openFirstBook();
    }

    @Override
    public void switchToCatalogTab(String catalogTab) {
        catalogScreen.switchToCatalogTab(catalogTab);
    }

    @Override
    public void checkAllPresentBooksAreAudiobooks() {
        Assert.assertTrue("Not all present books are audiobooks", catalogScreen.getListOfBooksNames().stream().allMatch(x -> x.toLowerCase().contains("audiobook")));
    }

    @Override
    public void sortBooksBy(FacetSortByKeys sortingCategory) {
        facetedSearchScreen.sortBy();
        facetedSearchScreen.changeSortByTo(sortingCategory);
    }

    @Override
    public void saveListOfBooks(String booksInfoKey) {
        context.add(booksInfoKey, subcategoryScreen.getBooksInfo());
    }

    @Override
    public void checkAllBooksCanBeDownloaded() {
        Assert.assertTrue("Not all present books can be downloaded", subcategoryScreen.getAllButtonsNames()
                .stream()
                .allMatch(x -> x.equals(BookActionButtonNames.DOWNLOAD_BUTTON_NAME)));
    }

    @Override
    public void checkAllBooksCanBeLoanedOrDownloaded() {
        Assert.assertTrue("Not all present books can be loaned or downloaded", subcategoryScreen.getAllButtonsNames()
                .stream()
                .allMatch(x -> x.equals(BookActionButtonNames.GET_BUTTON_NAME) || x.equals(BookActionButtonNames.DOWNLOAD_BUTTON_NAME)));
    }

    @Override
    public void checkListOfBooksOnSubcategoryScreenIsNotEqualToListOfSavedBooks(String booksNamesListKey) {
        List<String> expectedList = context.get(booksNamesListKey);
        Assert.assertNotEquals("Lists of books are equal" + expectedList.stream().map(Object::toString).collect(Collectors.joining(", ")), expectedList, subcategoryScreen.getBooksInfo());
    }

    @Override
    public void performActionOnHardcodeBookByNameAndSaveIt(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookName, String bookInfoKey) {
        context.add(bookInfoKey, catalogBooksScreen.scrollToBookByNameAndClickActionButton(actionButtonKey, bookName));
    }

    @Override
    public void checkBooksAreSortedByAuthorAscending() {
        List<String> list = subcategoryScreen.getAuthorsInfo();
        List<String> listOfSurnames = getSurnames(list);
        Assert.assertEquals("Lists of authors is not sorted properly " + list.stream().map(Object::toString).collect(Collectors.joining(", ")), getSurnames(listOfSurnames.stream().sorted().collect(Collectors.toList())), listOfSurnames);
    }

    @Override
    public void booksAreSortedByTitleAscending() {
        List<String> list = subcategoryScreen.getTitlesInfo();
        Assert.assertEquals("Lists of authors is not sorted properly" + list.stream().map(Object::toString).collect(Collectors.joining(", ")), list.stream().sorted().collect(Collectors.toList()), list);
    }

    protected List<String> getSurnames(List<String> list) {
        List<String> listOfSurnames = new ArrayList<>();
        for (String authorName : list) {
            String[] separatedName = authorName.split("\\s");
            List<String> sortedNames = Arrays.stream(separatedName).sorted().collect(Collectors.toList());
            listOfSurnames.add(sortedNames.get(0));
        }
        return listOfSurnames;
    }

    @Override
    public void closeBookDetailsOnlyForIOSTab() {
        bookDetailsScreen.closeBookDetailsOnlyForIOSTabIfDisplayed();
    }

    @Override
    public void checkThatSavedBookContainButtonAtCatalogBooksScreen(
            final String bookInfoKey, final EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        String title = catalogBookModel.getTitle();
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && alertScreen.state().waitForDisplayed()) {
            AqualityServices.getApplication().getDriver().switchTo().alert().dismiss();
            AqualityServices.getLogger().info("Alert appears and dismiss alert");
        }
        boolean isButtonPresent = catalogBooksScreen.isBookAddButtonTextEqualTo(title, actionButtonKey);
        if (!isButtonPresent && catalogBooksScreen.isErrorButtonPresent()) {
            Scenario scenario = context.get(ScenarioContextKey.SCENARIO_KEY);
            scenario.attach(ScreenshotUtils.getScreenshot(), "image/png", "error_screenshot.png");
        }
        Assert.assertTrue(String.format("Book's with title '%1$s' button does not contain text '%2$s'. Error message (if present) - '%3$s'", title, actionButtonKey.i18n(), catalogBooksScreen.getErrorMessage()), isButtonPresent);
    }

    @Override
    public void checkThatBookContainsButtonWithDefiniteActionOnBookDetailsView(final EnumActionButtonsForBooksAndAlertsKeys key) {
        boolean isButtonPresent = bookDetailsScreen.isBookAddButtonTextEqualTo(key);
        addScreenshotIfErrorPresent(isButtonPresent);
        Assert.assertTrue(String.format("Button '%1$s' is not present on book details screen. Error (if present) - %2$s", key.i18n(), getErrorDetails()), isButtonPresent);
    }

    @Override
    public void openSpecificBookWithSpecificActionButton(String bookInfoKey, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        subcategoryScreen.state().waitForDisplayed();
        subcategoryScreen.openBookWithDefiniteNameAndDefiniteActionButton(bookInfo, actionButtonKey);
    }

    @Override
    public void pressOnBookDetailsScreenAtActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        clickActionButtonOnBookDetailsView(actionButtonKey);
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && alertScreen.state().waitForDisplayed()) {
            if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.CANCEL_RESERVATION){
                alertScreen.waitAndPerformAlertActionIfDisplayed(actionButtonKey);
            }else {
                AqualityServices.getApplication().getDriver().switchTo().alert().dismiss();
                AqualityServices.getLogger().info("Alert appears and dismiss alert");
            }
        }
    }

    @Override
    public void checkThatActionButtonTextEqualToExpected(FacetAvailabilityKeys facetAvailabilityKeys) {
        facetedSearchScreen.openAvailabilityMenu();
        facetedSearchScreen.changeAvailabilityTo(facetAvailabilityKeys);
    }

    @Override
    public void openBookWithDefiniteActionButtonAndDefiniteNameAndDefiniteBookTypeFromAPIOAndSaveBookInfo(String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey, String bookType) {
        context.add(bookInfoKey, subcategoryScreen.openBookWithDefiniteActionButtonAndDefiniteNameAndDefiniteBookTypeFromAPIAndGetBookInfo(bookName, actionButtonKey, bookType));
    }

    public void startReadingOrListeningToBookWithSpecifyTypeOnBookDetailsView(EnumBookType bookType) {
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

    private void clickActionButtonOnBookDetailsView(EnumActionButtonsForBooksAndAlertsKeys actionButton) {
        bookDetailsScreen.clickActionButton(actionButton);
    }

    private void addScreenshotIfErrorPresent(boolean isButtonPresent) {
        if (!isButtonPresent && bookDetailsScreen.isErrorButtonPresent()) {
            addScreenshot();
        }
    }

    protected void addScreenshot() {
        Scenario scenario = context.get(ScenarioContextKey.SCENARIO_KEY);
        scenario.attach(ScreenshotUtils.getScreenshot(), "image/png", "screenshot.png");
    }

    public void openFirstCategory() {
        catalogScreen.state().waitForDisplayed();
        catalogScreen.openFirstCategory();
        if (!subcategoryScreen.state().waitForDisplayed()) {
            //todo added waiting
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            catalogScreen.openFirstCategory();
            catalogScreen.state().waitForDisplayed();
        }
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
}