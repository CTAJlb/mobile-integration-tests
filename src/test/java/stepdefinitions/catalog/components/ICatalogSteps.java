package stepdefinitions.catalog.components;

import constants.localization.application.catalog.BookActionButtonKeys;
import constants.localization.application.facetedSearch.FacetAvailabilityKeys;
import constants.localization.application.facetedSearch.FacetSortByKeys;
import models.android.BookDetailsScreenInformationBlockModel;

import java.util.List;

public interface ICatalogSteps {

    void booksFeedIsLoaded();

    void openPreviousCategoryScreen();

    void openBookWithDefiniteActionButtonAndDefiniteNameFromAPIOAndSaveBookInfo(String bookType, BookActionButtonKeys actionButtonKey, String bookNameKey, String bookInfoKey);

    void performActionOnBookFromAPIAndSaveIt(BookActionButtonKeys actionButtonKey, String bookNameInfoKey, String bookInfoKey);

    void getNamesOfBooksAndSaveThem(String booksNamesListKey);

    void checkListOfBooksIsNotEqualToSavedListOfBooks(String booksNamesListKey);

    void openLibraryFromSideMenu(String libraryName);

    void performActionOnHardcodeBookByNameAndSaveIt(BookActionButtonKeys actionButtonKey, String bookName, String bookInfoKey);

    void openCatalogWithAgeCheck();

    void openBooks();

    void checkCurrentLibraryIsCorrect(String expectedLibraryName);

    void openCategory(String categoryName);

    void checkCurrentCategoryName(String expectedCategoryName);

    void checkSubcategoryScreenIsPresent();

    void checkFollowingSubcategoriesArePresent(List<String> expectedValuesList);

    void openCategoryByChain(List<String> categoriesChain);

    void openBookDetailsExecuteBookActionAndSaveItToContext(BookActionButtonKeys actionButtonKey, String bookInfoKey);

    void executeBookActionAndSaveItToContextAndLibraryCancel(
            BookActionButtonKeys actionButtonKey, String bookInfoKey);

    void performActionOnBookOfTypeAndSaveIt(BookActionButtonKeys actionButtonKey, String bookType, String bookInfoKey);

    void performGetOrDownloadActionOnBookByNameFromAPIAndSaveIt(BookActionButtonKeys actionButtonKey1, BookActionButtonKeys actionButtonKey2, String bookNameInfoKey, String bookInfoKey);

    void clickOnBookAddButtonOnCatalogBooksScreen(String bookInfoKey, BookActionButtonKeys key);

    void checkCountOfBooksInFirstLaneIsMoreThan(int countOfBooks);

    void checkCountOfBooksInSubcategoryLaneIsUpTo(String lineName, int countOfBooks);

    void checkBookInfoIsOpened(String bookInfoKey);

    void openFirstBookInSubcategoryListAndSaveIt(String bookInfoKey);

    void switchToCatalogTab(String catalogTab);

    void checkAllPresentBooksAreAudiobooks();

    void sortBooksBy(FacetSortByKeys sortingCategory);

    void saveListOfBooks(String booksInfoKey);

    void checkAllBooksCanBeDownloaded();

    void checkAllBooksCanBeLoanedOrDownloaded();

    void checkListOfBooksOnSubcategoryScreenIsNotEqualToListOfSavedBooks(String booksNamesListKey);

    void checkBooksAreSortedByAuthorAscending();

    void booksAreSortedByTitleAscending();

    void checkFollowingValuesInInformationBlockArePresent(List<BookDetailsScreenInformationBlockModel> expectedValuesList);

    void checkDescriptionHasText(final String description);

    void openRelatedBooks();

    void checkCountOfBooksInSearchResultIsUpTo(int countOfBooks);

    void checkThatSavedBookContainButtonAtCatalogBooksScreen(
            final String bookInfoKey, final BookActionButtonKeys key);

    void checkThatBookContainsButtonWithDefiniteActionOnBookDetailsView(final BookActionButtonKeys key);

    void deleteBookFromBookDetailsScreen();

    void returnBookFromBookDetailsScreen();

    void openBookDetailsByClickingOnCover(String bookInfoKey);

    void pressOnBookDetailsScreenAtActionButton(BookActionButtonKeys actionButton);

    void checkThatActionButtonTextEqualToExpected(FacetAvailabilityKeys facetAvailabilityKeys);

    void closeBookDetailsOnlyForIOSTab();
}
