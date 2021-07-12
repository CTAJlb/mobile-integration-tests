package stepdefinitions.catalog.components;

import aquality.appium.mobile.application.AqualityServices;
import constants.application.ReaderType;
import constants.application.timeouts.AuthorizationTimeouts;
import constants.application.timeouts.CategoriesTimeouts;
import constants.context.ScenarioContextKey;
import constants.localization.application.catalog.BookActionButtonKeys;
import constants.localization.application.catalog.BookActionButtonNames;
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
import screens.agegate.AgeGateScreen;
import screens.alert.AlertScreen;
import screens.bookDetails.BookDetailsScreen;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.catalog.form.MainCatalogToolbarForm;
import screens.catalog.screen.books.CatalogBooksScreen;
import screens.catalog.screen.catalog.CatalogScreen;
import screens.facetedSearch.FacetedSearchScreen;
import screens.notifications.NotificationModal;
import screens.subcategory.SubcategoryScreen;
import stepdefinitions.BaseSteps;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractCatalogSteps extends BaseSteps implements ICatalogSteps {
    protected final BottomMenuForm bottomMenuForm;
    protected final CatalogScreen catalogScreen;
    protected final SubcategoryScreen subcategoryScreen;
    protected final AgeGateScreen ageGateScreen;
    protected final BookDetailsScreen bookDetailsScreen;
    protected final MainCatalogToolbarForm mainCatalogToolbarForm;
    protected final CatalogBooksScreen catalogBooksScreen;
    protected final FacetedSearchScreen facetedSearchScreen;
    protected final ScenarioContext context;
    protected final NotificationModal notificationModal;
    private final AlertScreen alertScreen;

    public AbstractCatalogSteps(ScenarioContext context) {
        this.context = context;
        mainCatalogToolbarForm = AqualityServices.getScreenFactory().getScreen(MainCatalogToolbarForm.class);
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        catalogScreen = AqualityServices.getScreenFactory().getScreen(CatalogScreen.class);
        ageGateScreen = AqualityServices.getScreenFactory().getScreen(AgeGateScreen.class);
        bookDetailsScreen = AqualityServices.getScreenFactory().getScreen(BookDetailsScreen.class);
        subcategoryScreen = AqualityServices.getScreenFactory().getScreen(SubcategoryScreen.class);
        catalogBooksScreen = AqualityServices.getScreenFactory().getScreen(CatalogBooksScreen.class);
        facetedSearchScreen = AqualityServices.getScreenFactory().getScreen(FacetedSearchScreen.class);
        notificationModal = AqualityServices.getScreenFactory().getScreen(NotificationModal.class);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);
    }

    @Override
    public void booksFeedIsLoaded() {
        boolean isPagePresent =
                catalogScreen.state().waitForDisplayed(Duration.ofMillis(CategoriesTimeouts.TIMEOUT_WAIT_UNTIL_CATEGORY_PAGE_LOAD.getTimeoutMillis()));
        if (!isPagePresent && catalogScreen.isErrorButtonPresent()) {
            addScreenshot();
        }
        Assert.assertTrue("Books feed is not loaded. Error message (if present) - " + catalogScreen.getErrorDetails(), isPagePresent);
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
        catalogScreen.openLibrary(libraryName);
        if (notificationModal.isModalPresent()) {
            notificationModal.closeCannotAddBookModalIfDisplayed();
            catalogScreen.openLibrary(libraryName);
        }
        catalogScreen.state().waitForDisplayed();
        AqualityServices.getConditionalWait().waitFor(() -> catalogBooksScreen.getFoundBooksCount() > 0);
    }

    @Override
    public void openCatalogWithAgeCheck() {
        if (ageGateScreen.state().isDisplayed()) {
            ageGateScreen.approveAge();
        }
        bottomMenuForm.open(BottomMenu.CATALOG);
        catalogScreen.state().waitForDisplayed();
    }

    @Override
    public void openBooks() {
        bottomMenuForm.open(BottomMenu.BOOKS);
    }

    @Override
    public void checkCurrentLibraryIsCorrect(String expectedLibraryName) {
        Assert.assertEquals("Current library name is not correct", expectedLibraryName, mainCatalogToolbarForm.getCatalogName());
    }

    @Override
    public void openCategory(String categoryName) {
        subcategoryScreen.state().waitForDisplayed();
        catalogScreen.openCategory(categoryName);
    }

    public abstract void checkCurrentCategoryName(String expectedCategoryName);

    @Override
    public void checkSubcategoryScreenIsPresent() {
        boolean isScreenPresent = subcategoryScreen.state().waitForDisplayed(Duration.ofMillis(AuthorizationTimeouts.DEBUG_MENU_IS_OPENED.getTimeoutMillis()));
        if (!isScreenPresent && subcategoryScreen.isErrorButtonPresent()) {
            Scenario scenario = context.get(ScenarioContextKey.SCENARIO_KEY);
            scenario.attach(ScreenshotUtils.getScreenshot(), "image/png", "error_screenshot.png");
        }
        Assert.assertTrue("Subcategory screen is not present. Error (if present) - " + subcategoryScreen.getErrorMessage(), isScreenPresent);
    }

    @Override
    public void checkFollowingSubcategoriesArePresent(List<String> expectedValuesList) {
        catalogScreen.state().waitForDisplayed();
        catalogScreen.swipeScreenUp();
        Assert.assertEquals("List of categories is not completely present", new HashSet<>(expectedValuesList), catalogScreen.getAllCategoriesNames());
    }

    @Override
    public void openCategoryByChain(List<String> categoriesChain) {
        IntStream.range(0, categoriesChain.size()).forEach(index -> {
            openCategory(categoriesChain.get(index));
            if (index != categoriesChain.size() - 1) {
                Assert.assertTrue("Category page is not loaded. Error (if present) - " + catalogScreen.getErrorDetails(), catalogScreen.isCategoryPageLoad());
            }
        });
    }

    @Override
    public void openBookDetailsExecuteBookActionAndSaveItToContext(
            BookActionButtonKeys actionButtonKey, String bookInfoKey) {
        catalogBooksScreen.openBookWithGivenActionButtonDetails(actionButtonKey);
        CatalogBookModel catalogBookModel = bookDetailsScreen.getBookInfo();
        pressOnBookDetailsScreenAtActionButton(actionButtonKey);
        context.add(bookInfoKey, catalogBookModel);
    }

    @Override
    public void executeBookActionAndSaveItToContextAndLibraryCancel(
            BookActionButtonKeys actionButtonKey, String bookInfoKey) {
        context.add(bookInfoKey, catalogBooksScreen.scrollToBookAndClickActionButton(actionButtonKey));
        notificationModal.handleBookActionsAndNotificationPopups(actionButtonKey);
    }

    @Override
    public void performActionOnBookOfTypeAndSaveIt(BookActionButtonKeys actionButtonKey, String bookType, String bookInfoKey) {
        context.add(bookInfoKey, catalogBooksScreen.scrollToBookByTypeAndClickActionButton(actionButtonKey, bookType));
    }

    @Override
    public void performGetOrDownloadActionOnBookByNameFromAPIAndSaveIt(BookActionButtonKeys actionButtonKey1, BookActionButtonKeys actionButtonKey2, String bookNameInfoKey, String bookInfoKey) {
        String bookName = context.get(bookNameInfoKey);
        context.add(bookInfoKey, catalogBooksScreen.scrollToBookByNameAndClickGetOrDownloadActionButton(actionButtonKey1, actionButtonKey2, bookName));
    }

    //new
    @Override
    public void performActionOnBookFromAPIAndSaveIt(BookActionButtonKeys actionButtonKey, String bookNameInfoKey, String bookInfoKey) {
        subcategoryScreen.state().waitForDisplayed();
        String bookName = context.get(bookNameInfoKey);
        context.add(bookInfoKey, catalogBooksScreen.scrollToBookByNameAndClickActionButton(actionButtonKey, bookName));
        alertScreen.closeNotNowModalIfPresent();
    }

    @Override
    public void clickOnBookAddButtonOnCatalogBooksScreen(String bookInfoKey, BookActionButtonKeys key) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        catalogBooksScreen.clickBookByTitleButtonWithKey(catalogBookModel.getTitle(), key);
        notificationModal.handleBookActionsAndNotificationPopups(key);
    }

    @Override
    public void checkCountOfBooksInFirstLaneIsMoreThan(int countOfBooks) {
        Assert.assertTrue("Count of books is smaller than " + countOfBooks, countOfBooks <= catalogScreen.getListOfAllBooksNamesInFirstLane().size());
    }

    @Override
    public void checkCountOfBooksInSubcategoryLaneIsUpTo(String lineName, int countOfBooks) {
        int foundCountOfBooks = catalogScreen
                .getListOfAllBooksNamesInSubcategoryLane(lineName)
                .size();
        Assert.assertTrue(String.format("Expected count of books bigger or equal to %1$s but found %2$s", countOfBooks,
                foundCountOfBooks), countOfBooks >= foundCountOfBooks);
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
    public void performActionOnHardcodeBookByNameAndSaveIt(BookActionButtonKeys actionButtonKey, String bookName, String bookInfoKey) {
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
    public void checkFollowingValuesInInformationBlockArePresent(
            List<BookDetailsScreenInformationBlockModel> expectedValuesList) {
        Assert.assertTrue("Not all information block values are correct (or present)", expectedValuesList.stream().allMatch(listElement ->
                        bookDetailsScreen.isValuePresentInInformationBlock(listElement.getKey(),
                                listElement.getValue())));
    }

    @Override
    public void checkDescriptionHasText(final String description) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(bookDetailsScreen.isDescriptionPresent()).as("Description does not present").isTrue();
        softAssertions.assertThat(StringUtils.trim(bookDetailsScreen.getDescriptionText()).contains(StringUtils.trim(description))).as("Description has not correct text").isTrue();
        softAssertions.assertAll();
    }

    @Override
    public void openRelatedBooks() {
        bookDetailsScreen.clickRelatedBooks();
    }

    @Override
    public void checkCountOfBooksInSearchResultIsUpTo(int countOfBooks) {
        int foundBooksCount = catalogBooksScreen.getFoundBooksCount();
        Assert.assertTrue(String.format("Found count of books (%d) is bigger than expected - %d", foundBooksCount, countOfBooks), countOfBooks >= foundBooksCount);
    }

    @Override
    public void checkThatSavedBookContainButtonAtCatalogBooksScreen(
            final String bookInfoKey, final BookActionButtonKeys key) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        String title = catalogBookModel.getTitle();
        alertScreen.closeDoNotAllowIfPresent();
        boolean isButtonPresent = catalogBooksScreen.isBookAddButtonTextEqualTo(title, key);
        if (!isButtonPresent && catalogBooksScreen.isErrorButtonPresent()) {
            Scenario scenario = context.get(ScenarioContextKey.SCENARIO_KEY);
            scenario.attach(ScreenshotUtils.getScreenshot(), "image/png", "error_screenshot.png");
        }
        Assert.assertTrue(String.format("Book's with title '%1$s' button does not contain text '%2$s'. Error message (if present) - '%3$s'", title, key.i18n(), catalogBooksScreen.getErrorMessage()), isButtonPresent);
    }

    @Override
    public void checkThatBookContainsButtonWithDefiniteActionOnBookDetailsView(final BookActionButtonKeys key) {
        boolean isButtonPresent = bookDetailsScreen.isBookAddButtonTextEqualTo(key);
        addScreenshotIfErrorPresent(isButtonPresent);
        Assert.assertTrue(String.format("Button '%1$s' is not present on book details screen. Error (if present) - %2$s", key.i18n(), getErrorDetails()), isButtonPresent);
    }

    @Override
    public void returnBookFromBookDetailsScreen() {
        bookDetailsScreen.returnBook();
        notificationModal.handleBookActionsAndNotificationPopups(BookActionButtonKeys.RETURN);
    }

    @Override
    public void deleteBookFromBookDetailsScreen() {
        bookDetailsScreen.deleteBook();
        notificationModal.handleBookActionsAndNotificationPopups(BookActionButtonKeys.DELETE);
    }

    @Override
    public void openBookDetailsByClickingOnCover(String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        subcategoryScreen.state().waitForDisplayed();
        subcategoryScreen.openBook(bookInfo);
    }

    @Override
    public void pressOnBookDetailsScreenAtActionButton(BookActionButtonKeys actionButton) {
        clickButton(actionButton);
        notificationModal.handleBookActionsAndNotificationPopups(actionButton);
        alertScreen.closeNotNowModalIfPresent();
    }

    @Override
    public void checkThatActionButtonTextEqualToExpected(FacetAvailabilityKeys facetAvailabilityKeys) {
        facetedSearchScreen.openAvailabilityMenu();
        facetedSearchScreen.changeAvailabilityTo(facetAvailabilityKeys);
    }

    @Override
    public void openBookWithDefiniteActionButtonAndDefiniteNameFromAPIOAndSaveBookInfo(String bookName, BookActionButtonKeys actionButtonKey, String bookInfoKey, String bookType) {
        context.add(bookInfoKey, subcategoryScreen.openBookWithDefiniteActionButtonAndDefiniteNameFromAPIAndGetBookInfo(bookName, actionButtonKey, bookType));
    }

    public void openTypeBookReader(ReaderType readerType) {
        switch (readerType) {
            case EBOOK:
                clickButton(BookActionButtonKeys.READ);
                break;
            case AUDIOBOOK:
                clickButton(BookActionButtonKeys.LISTEN);
                break;
        }
    }

    private void clickButton(BookActionButtonKeys actionButton) {
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

    public void checkCountOfBooksInSearchResultIsMoreThen(int countOfBooks) {
        Assert.assertTrue(String.format("Found count of books (%d) is less than expected - %d", catalogBooksScreen.getFoundBooksCount(), countOfBooks), AqualityServices.getConditionalWait().waitFor(() -> countOfBooks <= catalogBooksScreen.getFoundBooksCount()));
    }

    public void checkCountOfBooksInSubcategoryLaneIsMoreThen(String lineName, int countOfBooks) {
        int foundCountOfBooks = catalogScreen.getListOfAllBooksNamesInSubcategoryLane(lineName).size();
        Assert.assertTrue(String.format("Expected count of books bigger or equal to %1$s but found %2$s", countOfBooks, foundCountOfBooks), countOfBooks <= foundCountOfBooks);
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