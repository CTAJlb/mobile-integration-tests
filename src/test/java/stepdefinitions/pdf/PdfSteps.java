package stepdefinitions.pdf;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import framework.utilities.ScenarioContext;
import framework.utilities.swipe.SwipeElementUtils;
import framework.utilities.swipe.directions.EntireScreenDragDirection;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import screens.pdf.readerPdf.ReaderPdfScreen;
import screens.pdf.settingspdf.SettingsPdfScreen;
import screens.pdf.tocbookmarkspdf.TocBookmarksPdfScreen;
import screens.pdf.tocPdf.TocPdfScreen;

public class PdfSteps {
    private final ReaderPdfScreen readerPdfScreen;
    private final TocPdfScreen tocPdfScreen;
    private final SettingsPdfScreen settingsPdfScreen;
    private final TocBookmarksPdfScreen tocBookmarksPdfScreen;
    private final ScenarioContext context;

    @Inject
    public PdfSteps(ScenarioContext context) {
        readerPdfScreen = AqualityServices.getScreenFactory().getScreen(ReaderPdfScreen.class);
        tocPdfScreen = AqualityServices.getScreenFactory().getScreen(TocPdfScreen.class);
        settingsPdfScreen = AqualityServices.getScreenFactory().getScreen(SettingsPdfScreen.class);
        tocBookmarksPdfScreen = AqualityServices.getScreenFactory().getScreen(TocBookmarksPdfScreen.class);
        this.context = context;
    }

    @Then("Reader pdf screen is opened")
    public void isPdfReaderOpened() {
        Assert.assertTrue("PDF reader is not opened", readerPdfScreen.isReaderOpened());
    }

    @When("I close pdf reader by back button")
    public void closePdfReader() {
        readerPdfScreen.getNavigationBarScreen().tapBackButton();
    }

    @Then("The book name is {string} on pdf reader screen")
    public void checkBookTitle(String bookNameInfoKey) {
        String expectedBookName = context.get(bookNameInfoKey);
        String actualBookName = readerPdfScreen.getBookName();
        Assert.assertEquals("The book title is incorrect", expectedBookName, actualBookName);
    }

    @When("I open table of contents on pdf reader screen")
    public void openTOC() {
        readerPdfScreen.getNavigationBarScreen().tapTocBookmarksBarButton();
    }

    @Then("There are content list with thumbnails and chapter content on pdf toc screen")
    public void checkThePresenceOfTwoContentLists() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(tocBookmarksPdfScreen.isThumbnailsButtonDisplayed())
                .as("There is no content with thumbnails").isTrue();
        softAssertions.assertThat(tocBookmarksPdfScreen.isChaptersButtonDisplayed())
                .as("There is no content with text chapters").isTrue();
        softAssertions.assertAll();
    }

    @When("I open content with thumbnails on pdf toc screen")
    public void openThumbnails() {
        tocBookmarksPdfScreen.tapThumbnailsButton();
    }

    @When("I open text chapter content on pdf toc screen")
    public void openTextContent() {
        tocBookmarksPdfScreen.tapTocButton();
    }

    @Then("Thumbnails of the book pages are displayed")
    public void areThumbnailsDisplayed() {
        Assert.assertTrue("Thumbnails of the book are not displayed", tocBookmarksPdfScreen.getThumbnailsPdfScreen().areThumbnailsDisplayed());
    }

    @Then("Text chapter content is opened on pdf toc screen")
    public void isTextContentOpened(){
        Assert.assertTrue("Text chapter content is not opened", tocBookmarksPdfScreen.isTextContentOpened());
    }

    @When("I open random thumbnail and save the number as {string} on pdf toc screen")
    public void openRandomThumbnail(String pageInfoKey) {
        context.add(pageInfoKey, tocBookmarksPdfScreen.getThumbnailsPdfScreen().openRandomThumbnail());
    }

    @When("I open random chapter and save the number as {string} on pdf toc screen")
    public void openRandomChapter(String pageInfoKey){
        context.add(pageInfoKey, tocPdfScreen.openRandomChapter());
    }

    @When("I return to pdf reader screen from pdf toc screen")
    public void returnToReaderFromTOC() {
        tocBookmarksPdfScreen.returnToReaderPdfScreen();
    }

    @Then("PDF toc screen is closed")
    public void tocScreenIsClosed() {
        Assert.assertTrue("TOC is opened", tocBookmarksPdfScreen.isTocClosed());
    }

    @Then("Page number is equal to {string} on pdf reader screen")
    public void comparePageNumbers(String pageInfoKey) {
        int pageNumber = context.get(pageInfoKey);
        Assert.assertEquals("Page number is wrong", pageNumber, readerPdfScreen.getPageNumber());
    }

    @Then("I close pdf toc screen by back button")
    public void closeTOCByBackBtn() {
        readerPdfScreen.getNavigationBarScreen().tapBackButton();
    }

    @When("I open pdf settings screen on pdf reader screen")
    public void openSettings() {
        readerPdfScreen.getNavigationBarScreen().tapSettingsButton();
    }

    @Then("PDF settings screen is opened")
    public void isSettingsOpened() {
        settingsPdfScreen.isOpened();
    }

    @When("I save the number of the last page as {string} on pdf reader screen")
    public void saveLastPage(String lastPageInfoKey) {
        context.add(lastPageInfoKey, readerPdfScreen.getLastPageNumber());
    }

    @When("I tap Go to last page button on pdf settings screen")
    public void tapGoToLastPage(){
        settingsPdfScreen.tapGoToLastPage();
    }

    @When("I tap Go to first page button on pdf settings screen")
    public void tapGoToFirstPage() {
        settingsPdfScreen.tapGoToFirstPage();
    }

    @Then("The first page is opened on pdf reader screen")
    public void isFirstPageOpened(){
        Assert.assertEquals("The first page is not opened", 1, readerPdfScreen.getPageNumber());
    }

    @When("I tap Vertical scrolling on pdf settings screen")
    public void tapVerticalScrolling(){
        settingsPdfScreen.tapVerticalScrolling();
    }

    @Then("Vertical scrolling is chosen on settings screen")
    public void isVerticalScrollingChosen() {
        Assert.assertTrue("Vertical scrolling is not chosen", settingsPdfScreen.isVerticalScrollingChosen());
    }

    @Then("Spreads options are available on settings screen")
    public void areSpreadsAvailable() {
        Assert.assertTrue("No spreads option is not available", settingsPdfScreen.isNoSpreadsAvailable());
        Assert.assertTrue("Odd spreads option is not available", settingsPdfScreen.isOddSpreadsAvailable());
        Assert.assertTrue("Even spreads is not available", settingsPdfScreen.isEvenSpreadsAvailable());
    }

    @When("I save page number as {string} on pdf reader screen")
    public void savePageNumberOnReader(String pageInfoKey){
        context.add(pageInfoKey, readerPdfScreen.getPageNumber());
    }

    @When("I scroll page down on pdf reader screen")
    public void scrollPageDown() {
        SwipeElementUtils.swipeByCoordinatesOfWindow();
    }

    @Then("Page number is not equal to {string} on pdf reader screen")
    public void isPageNotEqual(String pageInfoKey) {
        int pageNumber = context.get(pageInfoKey);
        Assert.assertNotEquals("Page number is wrong", pageNumber, readerPdfScreen.getPageNumber());
    }

    @When("I tap Horizontal scrolling on pdf settings screen")
    public void tapHorizontalScrolling() {
        settingsPdfScreen.tapHorizontalScrolling();
    }

    @Then("Horizontal scrolling is chosen on settings screen")
    public void isHorizontalScrollingChosen(){
        Assert.assertTrue("Horizontal scrolling is not chosen", settingsPdfScreen.isHorizontalScrollingChosen());
    }

    @Then("Spreads options are not available on settings screen")
    public void areSpreadsUnavailable() {
        Assert.assertEquals("No spreads option is available", settingsPdfScreen.isNoSpreadsAvailable(), Boolean.FALSE);
        Assert.assertEquals("Odd spreads option is available", settingsPdfScreen.isOddSpreadsAvailable(), Boolean.FALSE);
        Assert.assertEquals("Even spreads is available", settingsPdfScreen.isEvenSpreadsAvailable(), Boolean.FALSE);
    }

    @When("I tap Wrapped scrolling on pdf settings screen")
    public void tapWrappedScrolling(){
        settingsPdfScreen.tapWrappedScrolling();
    }

    @Then("Wrapped scrolling is chosen on settings screen")
    public void isWrappedScrollingChosen(){
        Assert.assertTrue("Wrapped scrolling is not chosen", settingsPdfScreen.isWrappedScrollingChosen());
    }

    @Then("Vertical scrolling is chosen by default on settings screen")
    public void checkDefaultScrolling(){
        Assert.assertTrue("Vertical scrolling is not default", settingsPdfScreen.isVerticalScrollingChosen());
    }

    @When("I go to next page on reader pdf screen")
    public void goToNextPdfPage() {
        readerPdfScreen.goToNextPage();
    }

    @Then("Page number increased by 1 from {string} on pdf reader screen")
    public void isPageNumberIncreased(String pageInfoKey) {
        int numberBefore = context.get(pageInfoKey);
        Assert.assertEquals("Page number has not increased", numberBefore + 1, readerPdfScreen.getPageNumber());
    }

    @When("I go to previous page on reader pdf screen")
    public void goToPreviousPdfPage() {
        readerPdfScreen.goToPreviousPage();
    }

    @Then("Page number decreased by 1 from {string} on pdf reader screen")
    public void isPageNumberDecreased(String pageInfoKey) {
        int numberBefore = context.get(pageInfoKey);
        Assert.assertEquals("Page number has not decreased", numberBefore - 1, readerPdfScreen.getPageNumber());
    }

    @When("I open search pdf screen")
    public void openSearchPdfScreen() {
        readerPdfScreen.getNavigationBarScreen().tapSearchButton();
    }

    @Then("Search pdf screen is opened")
    public void checkSearchPdfScreenIsOpened() {
        Assert.assertTrue("Search screen is not opened", readerPdfScreen.getSearchPdfScreen().isSearchPdfScreenOpened());
    }

    @When("I close pdf search screen")
    public void closeSearchScreen() {
        readerPdfScreen.getSearchPdfScreen().closeSearchScreen();
    }

    @When("I enter {string} text on search pdf screen")
    public void enterTextOnSearchLine(String text) {
        readerPdfScreen.getSearchPdfScreen().enterText(text);
    }

    @When("I delete text in search line on search pdf screen")
    public void deleteTextFromSearchLine() {
        readerPdfScreen.getSearchPdfScreen().deleteText();
    }

    @Then("Search field is empty on search pdf screen")
    public void isSearchFieldEmpty() {
        Assert.assertTrue("Search field is not enpty", readerPdfScreen.getSearchPdfScreen().isSearchFieldEmpty());
    }

    @Then("Found lines should contain {string} in themselves on search pdf screen")
    public void checkThatPdfFoundLinesContainText(String text) {
        SoftAssertions softAssertions = new SoftAssertions();
        readerPdfScreen.getSearchPdfScreen().getListOfFoundTexts().forEach(foundText -> softAssertions.assertThat(foundText.toLowerCase().contains(text.toLowerCase())).
                as(String.format("Found text '%1$s' does not contain text '%2$s'. ", foundText, text)).isTrue());
        softAssertions.assertAll();
    }

    @When("I open random found text and save page number as {string} on search pdf screen")
    public void openRandomTextAndSavePageNumber(String pageNumberKey) {
        context.add(pageNumberKey, readerPdfScreen.getSearchPdfScreen().openRandomFoundText());
    }

    @When("Slide page slider {} on reader pdf screen")
    public void slidePdfPageSlider(EntireScreenDragDirection entireScreenDragDirection) {
        readerPdfScreen.slidePageSlider(entireScreenDragDirection);
    }

    @Then("The {string} saved page number is greater than the current page number on the reader pdf screen")
    public void checkThatSavedPdfPageNumberIsGreaterThanCurrentPdfPageNumber(String pageNumberKey) {
        int savedPageNumber = context.get(pageNumberKey);
        int currentPageNumber = readerPdfScreen.getPageNumber();
        Assert.assertTrue("Saved page number is less than current page number on reader pdf screen. SavedPageNumber - " +
                savedPageNumber + ", currentPageNumber - " + currentPageNumber, savedPageNumber > currentPageNumber);
    }

    @Then("The {string} saved page number is less than the current page number on the reader pdf screen")
    public void checkThatSavedPdfPageNumberIsLessThanCurrentPdfPageNumber(String pageNumberKey) {
        int savedPageNumber = context.get(pageNumberKey);
        int currentPageNumber = readerPdfScreen.getPageNumber();
        Assert.assertTrue("Saved page number is greater that current page number on reader pdf screen. SavedPageNumber - " +
                savedPageNumber + ", currentPageNumber - " + currentPageNumber, savedPageNumber < currentPageNumber);
    }

    @When("I open bookmarks pdf screen")
    public void openBookmarksPdfScreen() {
        readerPdfScreen.getNavigationBarScreen().openTOC();
        tocBookmarksPdfScreen.tapBookmarksButton();
    }

    @Then("Bookmarks pdf screen is opened")
    public void checkBookmarksPdfScreenIsOpened() {
        Assert.assertTrue("Bookmarks pdf screen is not opened", tocBookmarksPdfScreen.getBookmarksPdfScreen().state().waitForDisplayed());
    }

    @Then("Amount of bookmarks is {int} on bookmarks pdf screen")
    public void checkThatAmountOfBookmarksIsCorrect(int expectedAmountOfBookmarks) {
        int actualAmountOfBookmarks = tocBookmarksPdfScreen.getBookmarksPdfScreen().getCountOfBookmarks();
        Assert.assertEquals(String.format("Amount of bookmarks is not correct on bookmarks pdf screen. ExpectedAmountOfBookmarks-%d, actualAmountOfBookmarks-%d", expectedAmountOfBookmarks, actualAmountOfBookmarks), expectedAmountOfBookmarks, actualAmountOfBookmarks);
    }

    @When("Close tocBookmarksGallery pdf screen")
    public void closeTocBookmarksGalleryScreen() {
//        abstractPdfSteps.closeTocBookmarksGalleryScreen();
    }




//    @Then("{string} book is present on reader pdf screen")
//    @Override
//    public void checkPdfBookPresent(String bookInfoKey) {
//        abstractPdfSteps.checkPdfBookPresent(bookInfoKey);
//    }
//
//    @When("Bookmark is not displayed on reader pdf screen")
//    @Override
//    public void checkThatBookmarkIsNotDisplayed() {
//        abstractPdfSteps.checkThatBookmarkIsNotDisplayed();
//    }
//
//    @Then("Pdf page number is {int} on reader pdf screen")
//    @Override
//    public void checkPdfPageNumberIsEqualTo(int pageNumber) {
//        abstractPdfSteps.checkPdfPageNumberIsEqualTo(pageNumber);
//    }
//

//
//    @When("Open the {int} bookmark on bookmarks pdf screen")
//    @Override
//    public void openBookmark(int bookmarkNumber) {
//        abstractPdfSteps.openBookmark(bookmarkNumber);
//    }
//
//    @Then("Bookmark is displayed on reader pdf screen")
//    @Override
//    public void checkThatBookmarkIsDisplayed() {
//        abstractPdfSteps.checkThatBookmarkIsDisplayed();
//    }
//

//
//    @When("Add bookmark on reader pdf screen")
//    @Override
//    public void addBookmarkOnReaderPdfScreen() {
//        abstractPdfSteps.addBookmarkOnReaderPdfScreen();
//    }
//
//    @When("Delete bookmark on reader pdf screen")
//    @Override
//    public void deleteBookmarkOnReaderPdfScreen() {
//        abstractPdfSteps.deleteBookmarkOnReaderPdfScreen();
//    }

//    @And("Random chapter of pdf book can be opened from toc pdf screen")
//    @Override
//    public void checkThatRandomChapterOfPdfBookCanBeOpenedFromTocPdfScreen() {
//        abstractPdfSteps.checkThatRandomChapterOfPdfBookCanBeOpenedFromTocPdfScreen();
//    }
//
//    @And("I save pdf page number as {string} on reader pdf screen")
//    @Override
//    public void savePdfPageNumber(String pageNumberKey) {
//        abstractPdfSteps.savePdfPageNumber(pageNumberKey);
//    }
//
//    @When("I swipe pdf page forward from {int} to {int} times on reader pdf screen")
//    @Override
//    public void swipePdfPageForwardSeveralTimes(int minValue, int maxValue) {
//        abstractPdfSteps.swipePdfPageForwardSeveralTimes(minValue, maxValue);
//    }
//

//

//
//    @Then("The {string} saved page number is equal to the current page number on the reader pdf screen")
//    public void checkThatSavedPdfPageNumberIsEqualToCurrentPdfPageNumber(String pageNumberKey) {
//        abstractPdfSteps.checkThatSavedPdfPageNumberIsEqualToCurrentPdfPageNumber(pageNumberKey);
//    }
//

//
//    @When("I open gallery pdf screen")
//    @Override
//    public void openGalleryPdfScreen() {
//        abstractPdfSteps.openGalleryPdfScreen();
//    }
//
//    @When("I switch to Gallery on pdf screen")
//    @Override
//    public void switchToGalleryPdfScreen() {
//        abstractPdfSteps.switchToGalleryPdfScreen();
//    }
//

//
//    @When("I open table of contents")
//    @Override
//    public void openTOC() {
//        abstractPdfSteps.openTOC();
//    }
//
//    @When("I switch to List of contents on pdf screen")
//    @Override
//    public void switchToListOfContents() {
//        abstractPdfSteps.switchToListOfContents();
//    }
//
//    @Then("TOC pdf screen is opened")
//    @Override
//    public void checkTocPdfScreenIsOpened() {
//        abstractPdfSteps.checkTocPdfScreenIsOpened();
//    }
//
//    @When("I open random chapter on list of contents pdf screen and save pdf page number as {string}")
//    @Override
//    public void openRandomChapterAndSavePageNumber(String pageNumberKey) {
//        abstractPdfSteps.openRandomChapterAndSavePageNumber(pageNumberKey);
//    }
//
//    @Then("Chapter with {string} is opened on pdf screen")
//    @Override
//    public void isChapterOpened(String pageNumberKey) {
//        abstractPdfSteps.isChapterOpened(pageNumberKey);
//    }
//
//    @When("I open random pdf page on gallery pdf screen and save pdf page number as {string}")
//    @Override
//    public void openRandomPdfPageAndSavePageNumberOnGalleryScreen(String pageNumberKey) {
//        abstractPdfSteps.openRandomPdfPageAndSavePageNumberOnGalleryScreen(pageNumberKey);
//    }
//

//


//    @Then("Gallery pdf screen is opened")
//    @Override
//    public void checkGalleryPdfScreenIsOpened() {
//        abstractPdfSteps.checkGalleryPdfScreenIsOpened();
//    }
//


//    @When("I enter word {} and save as {string} on search pdf screen")
//    @Override
//    public void enterData(String word, String infoKey) {
//        abstractPdfSteps.enterData(word, infoKey);
//    }
//
//    @Then("Search result is empty on search pdf screen")
//    @Override
//    public void isSearchResultEmpty() {
//        abstractPdfSteps.isSearchResultEmpty();
//    }
//
//    @Then("Search result is shown on search pdf screen")
//    @Override
//    public void isSearchResultShown() {
//        abstractPdfSteps.isSearchResultShown();
//    }
//

//
//    @When("I open the first found text on search pdf screen")
//    @Override
//    public void openTheFirstFoundText() {
//        abstractPdfSteps.openTheFirstFoundText();
//    }
}
