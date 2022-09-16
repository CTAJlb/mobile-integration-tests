package stepdefinitions.pdf.components;

import framework.utilities.swipe.directions.EntireScreenDragDirection;

public interface IPdfSteps {
    void checkPdfBookPresent(String bookInfoKey);  //+

    void checkThatBookmarkIsNotDisplayed();

    void checkPdfPageNumberIsEqualTo(int pageNumber);

    void checkThatAmountOfBookmarksIsCorrect(int expectedAmountOfBookmarks);

    void openBookmark(int bookmarkNumber);

    void checkThatBookmarkIsDisplayed();

    void closeTocBookmarksGalleryScreen();

    void addBookmarkOnReaderPdfScreen();

    void deleteBookmarkOnReaderPdfScreen();

    void goToPreviousPdfPage();

    void goToNextPdfPage();

    void checkThatRandomChapterOfPdfBookCanBeOpenedFromTocPdfScreen();

    void savePdfPageNumber(String pageNumberKey);

    void swipePdfPageForwardSeveralTimes(int minValue, int maxValue);

    void slidePdfPageSlider(EntireScreenDragDirection entireScreenDragDirection);

    void checkThatSavedPdfPageNumberIsGreaterThanCurrentPdfPageNumber(String pageNumberKey);

    void checkThatSavedPdfPageNumberIsLessThanCurrentPdfPageNumber(String pageNumberKey);

    void checkThatSavedPdfPageNumberIsEqualToCurrentPdfPageNumber(String pageNumberKey);

    void openGalleryPdfScreen();        //+

    void openBookmarksPdfScreen();

    void openTOC();         //+

    void openRandomChapterAndSavePageNumber(String pageNumberKey);

    void isChapterOpened(String pageNumberKey);

    void openRandomPdfPageAndSavePageNumberOnGalleryScreen(String pageNumberKey);

    void openSearchPdfScreen();

    void closeSearchScreen();

    void checkBookmarksPdfScreenIsOpened();

    void checkSearchPdfScreenIsOpened();

    void checkGalleryPdfScreenIsOpened();           //+

    void searchTextOnSearchPdfScreen(String text);

    void enterTextOnSearchLine(String text);

    void deleteTextFromSearchLine();

    void isSearchFieldEmpty();

    void enterData(String word, String infoKey);

    void isSearchResultEmpty();

    void isSearchResultShown();

    void checkThatPdfFoundLinesContainText(String textThatShouldBe);

    void openTheFirstFoundText();

    void savePageNumberOfTheFirstFoundText(String pageNumberKey);

    void switchToListOfContents();

    void checkTocPdfScreenIsOpened();

    void switchToGalleryPdfScreen();        //+
}
