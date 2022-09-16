package stepdefinitions.pdf;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import framework.utilities.ScenarioContext;
import framework.utilities.swipe.directions.EntireScreenDragDirection;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.android.CatalogBookModel;
import org.junit.Assert;
import screens.pdf.readerPdf.ReaderPdfScreen;
import screens.pdf.tocBookmarksGalleryPdf.TocBookmarksGalleryPdfScreen;
import screens.pdf.tocPdf.TocPdfScreen;
import stepdefinitions.BaseSteps;
import stepdefinitions.pdf.components.AbstractPdfSteps;
import stepdefinitions.pdf.components.IPdfSteps;

public class PdfSteps {
    private final ReaderPdfScreen readerPdfScreen;
    private final TocPdfScreen tocPdfScreen;
    private final TocBookmarksGalleryPdfScreen tocBookmarksGalleryPdfScreen;
    private final ScenarioContext context;



    @Inject
    public PdfSteps(ScenarioContext context) {
        readerPdfScreen = AqualityServices.getScreenFactory().getScreen(ReaderPdfScreen.class);
        tocPdfScreen = AqualityServices.getScreenFactory().getScreen(TocPdfScreen.class);
        tocBookmarksGalleryPdfScreen = AqualityServices.getScreenFactory().getScreen(TocBookmarksGalleryPdfScreen.class);
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
        readerPdfScreen.getNavigationBarScreen().openTOC();
    }

    @Then("There are content list with thumbnails and chapter content on pdf toc screen")
    public void checkThePresenceOfTwoContentLists() {
        Assert.assertTrue("TOC is not opened", tocPdfScreen.isTOCOpened());
    }

    @When("I open content with thumbnails on pdf toc screen")
    public void openThumbnails() {
        tocPdfScreen.openThumbnails();
    }

    @Then("Thumbnails of the book pages are displayed")
    public void areThumbnailsDisplayed() {
        Assert.assertTrue("Thumbnails of the book are not displayed", tocPdfScreen.areThumbnailDisplayed());
    }

    @When("I open random thumbnail and save the number as {string} on pdf toc screen")
    public void openRandomThumbnail(String pageInfoKey){
        context.add(pageInfoKey, tocPdfScreen.openRandomThumbnail());
    }

    @When("I return to pdf reader screen from pdf toc screen")
    public void returnToReaderFromTOC() {
        tocPdfScreen.returnToReaderPdfScreen();
    }

    @Then("PDF toc screen is closed")
    public void tocScreenIsClosed() {
        Assert.assertTrue("TOC is opened", tocPdfScreen.isTOCClosed());
    }

    @Then("Page number is equal to {string} on pdf reader screen")
    public void comparePageNumbers(String pageInfoKey) {
        int pageNumber = context.get(pageInfoKey);
        Assert.assertTrue("Page number is wrong", tocPdfScreen.isThumbnailOpened(pageNumber));
    }

    @Then("I close pdf toc screen by back button")
    public void closeTOCByBackBtn() {
        readerPdfScreen.getNavigationBarScreen().tapBackButton();
    }






//
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
//    @Then("Amount of bookmarks is {int} on bookmarks pdf screen")
//    @Override
//    public void checkThatAmountOfBookmarksIsCorrect(int expectedAmountOfBookmarks) {
//        abstractPdfSteps.checkThatAmountOfBookmarksIsCorrect(expectedAmountOfBookmarks);
//    }
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
//    @When("Close tocBookmarksGallery pdf screen")
//    @Override
//    public void closeTocBookmarksGalleryScreen() {
//        abstractPdfSteps.closeTocBookmarksGalleryScreen();
//    }
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
//
//    @When("I go to previous page on reader pdf screen")
//    @Override
//    public void goToPreviousPdfPage() {
//        abstractPdfSteps.goToPreviousPdfPage();
//    }
//
//    @When("I go to next page on reader pdf screen")
//    @Override
//    public void goToNextPdfPage() {
//        abstractPdfSteps.goToNextPdfPage();
//    }
//
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
//    @And("Slide page slider {} on reader pdf screen")
//    @Override
//    public void slidePdfPageSlider(EntireScreenDragDirection entireScreenDragDirection) {
//        abstractPdfSteps.slidePdfPageSlider(entireScreenDragDirection);
//    }
//
//    @Then("The {string} saved page number is less than the current page number on the reader pdf screen")
//    public void checkThatSavedPdfPageNumberIsLessThanCurrentPdfPageNumber(String pageNumberKey) {
//        abstractPdfSteps.checkThatSavedPdfPageNumberIsLessThanCurrentPdfPageNumber(pageNumberKey);
//    }
//
//    @Then("The {string} saved page number is equal to the current page number on the reader pdf screen")
//    public void checkThatSavedPdfPageNumberIsEqualToCurrentPdfPageNumber(String pageNumberKey) {
//        abstractPdfSteps.checkThatSavedPdfPageNumberIsEqualToCurrentPdfPageNumber(pageNumberKey);
//    }
//
//    @Then("The {string} saved page number is greater than the current page number on the reader pdf screen")
//    public void checkThatSavedPdfPageNumberIsGreaterThanCurrentPdfPageNumber(String pageNumberKey) {
//        abstractPdfSteps.checkThatSavedPdfPageNumberIsGreaterThanCurrentPdfPageNumber(pageNumberKey);
//    }
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
//    @When("I open bookmarks pdf screen")
//    @Override
//    public void openBookmarksPdfScreen() {
//        abstractPdfSteps.openBookmarksPdfScreen();
//    }
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
//    @When("I open search pdf screen")
//    @Override
//    public void openSearchPdfScreen() {
//        abstractPdfSteps.openSearchPdfScreen();
//    }
//
//    @Then("Bookmarks pdf screen is opened")
//    @Override
//    public void checkBookmarksPdfScreenIsOpened() {
//        abstractPdfSteps.checkBookmarksPdfScreenIsOpened();
//    }
//
//
//    @Then("Search pdf screen is opened")
//    @Override
//    public void checkSearchPdfScreenIsOpened() {
//        abstractPdfSteps.checkSearchPdfScreenIsOpened();
//    }
//
//    @When("I close pdf search screen")
//    @Override
//    public void closeSearchScreen() {
//        abstractPdfSteps.closeSearchScreen();
//    }
//
//    @Then("Gallery pdf screen is opened")
//    @Override
//    public void checkGalleryPdfScreenIsOpened() {
//        abstractPdfSteps.checkGalleryPdfScreenIsOpened();
//    }
//
//    @When("I search {string} text on search pdf screen")
//    @Override
//    public void searchTextOnSearchPdfScreen(String text) {
//        abstractPdfSteps.searchTextOnSearchPdfScreen(text);
//    }
//
//    @When("I enter {string} text on search pdf screen")
//    @Override
//    public void enterTextOnSearchLine(String text) {
//        abstractPdfSteps.enterTextOnSearchLine(text);
//    }
//
//    @When("I delete text in search line on search pdf screen")
//    @Override
//    public void deleteTextFromSearchLine() {
//        abstractPdfSteps.deleteTextFromSearchLine();
//    }
//
//    @Then("Search field is empty on search pdf screen")
//    @Override
//    public void isSearchFieldEmpty() {
//        abstractPdfSteps.isSearchFieldEmpty();
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
//    @Then("Found lines should contain {string} in themselves on search pdf screen")
//    @Override
//    public void checkThatPdfFoundLinesContainText(String textThatShouldBe) {
//        abstractPdfSteps.checkThatPdfFoundLinesContainText(textThatShouldBe);
//    }
//
//    @When("I open the first found text on search pdf screen")
//    @Override
//    public void openTheFirstFoundText() {
//        abstractPdfSteps.openTheFirstFoundText();
//    }
//
//    @When("I save page number as {string} of the first found text on search pdf screen")
//    @Override
//    public void savePageNumberOfTheFirstFoundText(String pageNumberKey) {
//        abstractPdfSteps.savePageNumberOfTheFirstFoundText(pageNumberKey);
//    }
}
