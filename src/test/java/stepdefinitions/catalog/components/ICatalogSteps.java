package stepdefinitions.catalog.components;

import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import constants.localization.application.facetedSearch.FacetAvailabilityKeys;
import constants.localization.application.facetedSearch.FacetSortByKeys;
import models.android.BookDetailsScreenInformationBlockModel;

import java.util.List;

public interface ICatalogSteps {

    void categoryRowsAreLoaded();

    void subcategoryRowsAreLoaded();

    void openBookWithDefiniteActionButtonAndDefiniteNameAndDefiniteBookTypeFromAPIOAndSaveBookInfo(String bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookNameKey, String bookInfoKey);

    void performActionOnSpecificBookFromAPIAndSaveBookInfoOnSubcategoryListView(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookNameInfoKey, String bookInfoKey);

    void getNamesOfBooksAndSaveThem(String booksNamesListKey);

    void checkListOfBooksIsNotEqualToSavedListOfBooks(String booksNamesListKey);

    void openLibraryFromSideMenu(String libraryName);

    void performActionOnHardcodeBookByNameAndSaveIt(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookName, String bookInfoKey);

    void openCatalogWithAgeCheck();

    void isLibraryPresentOnCatalogScreen(String libraryName);

    void openCategory(String categoryName);

    void openSubcategory(String subCategoryName);

    void checkCurrentCategoryName(String expectedCategoryName);

    void checkSubcategoryScreenIsPresent();

    void checkFollowingSubcategoriesArePresent(List<String> expectedValuesList);

    void openCategoriesByChainAndChainStartsFromCategoryScreen(List<String> categoriesChain);

    void openBookDetailsExecuteBookActionAndSaveItToContext(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey);

    void performActionOnBookAndSaveBookInfoOnSubcategoryListView(
            EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey);

    void performActionOnBookOfTypeAndSaveIt(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookType, String bookInfoKey);

    void performGetOrDownloadActionOnBookByNameFromAPIAndSaveIt(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey1, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey2, String bookNameInfoKey, String bookInfoKey);

    void performActionOnBookOnSubcategoryListView(EnumActionButtonsForBooksAndAlertsKeys bookActionButtonKeys, String bookInfoKey);

    void checkCountOfBooksInFirstLaneIsMoreThan(int countOfBooks);

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

    void checkThatSavedBookContainButtonAtCatalogBooksScreen(
            final String bookInfoKey, final EnumActionButtonsForBooksAndAlertsKeys key);

    void checkThatBookContainsButtonWithDefiniteActionOnBookDetailsView(final EnumActionButtonsForBooksAndAlertsKeys key);

    void openSpecificBookWithSpecificActionButton(String bookInfoKey, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    void pressOnBookDetailsScreenAtActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButton);

    void checkThatActionButtonTextEqualToExpected(FacetAvailabilityKeys facetAvailabilityKeys);

    void closeBookDetailsOnlyForIOSTab();
}
