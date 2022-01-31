package stepdefinitions.pdf.components;

import aquality.appium.mobile.application.AqualityServices;
import framework.utilities.ScenarioContext;
import framework.utilities.swipe.directions.EntireScreenDragDirection;
import models.android.CatalogBookModel;
import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import screens.pdf.galleryPdf.GalleryPdfScreen;
import screens.pdf.readerPdf.ReaderPdfScreen;
import screens.pdf.tocBookmarksGalleryPdf.TocBookmarksGalleryPdfScreen;
import screens.pdf.tocPdf.TocPdfScreen;
import stepdefinitions.BaseSteps;

import java.util.List;
import java.util.stream.IntStream;

public abstract class AbstractPdfSteps extends BaseSteps implements IPdfSteps {
    private final ReaderPdfScreen readerPdfScreen;
    private final TocPdfScreen tocPdfScreen;
    private final TocBookmarksGalleryPdfScreen tocBookmarksGalleryPdfScreen;
    private final ScenarioContext context;

    public AbstractPdfSteps(ScenarioContext context) {
        this.context = context;
        readerPdfScreen = screenFactory.getScreen(ReaderPdfScreen.class);
        tocPdfScreen = screenFactory.getScreen(TocPdfScreen.class);
        tocBookmarksGalleryPdfScreen = screenFactory.getScreen(TocBookmarksGalleryPdfScreen.class);
    }

    @Override
    public void checkPdfBookPresent(String bookInfoKey) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        String expectedBookName = catalogBookModel.getTitle();
        String actualBookName = readerPdfScreen.getBookName();
        Assert.assertTrue(String.format("Pdf book is not present on reader pdf screen. Expected bookName - '%1$s', actualName - '%2$s'", expectedBookName, actualBookName), actualBookName.toLowerCase().contains(expectedBookName.toLowerCase()));
    }

    @Override
    public void checkThatAmountOfBookmarksIsCorrect(int expectedAmountOfBookmarks) {
        int actualAmountOfBookmarks = tocBookmarksGalleryPdfScreen.getBookmarksPdfScreen().getCountOfBookmarks();
        Assert.assertTrue(String.format("Amount of bookmarks is not correct on bookmarks pdf screen. ExpectedAmountOfBookmarks-%d, actualAmountOfBookmarks-%d", expectedAmountOfBookmarks, actualAmountOfBookmarks), expectedAmountOfBookmarks == actualAmountOfBookmarks);
    }

    @Override
    public void checkThatBookmarkIsNotDisplayed() {
        readerPdfScreen.openNavigationBar();
        readerPdfScreen.openNavigationBar();
        Assert.assertTrue("Bookmark is displayed on reader pdf screen", !readerPdfScreen.getNavigationBarScreen().isBookmarkDisplayed());
    }

    @Override
    public void openBookmark(int bookmarkNumber) {
        tocBookmarksGalleryPdfScreen.getBookmarksPdfScreen().openBookmark(bookmarkNumber);
    }

    @Override
    public void checkThatBookmarkIsDisplayed() {
        readerPdfScreen.openNavigationBar();
        readerPdfScreen.openNavigationBar();
        Assert.assertTrue("Bookmark is not displayed on reader pdf screen", readerPdfScreen.getNavigationBarScreen().isBookmarkDisplayed());
    }

    @Override
    public void closeTocBookmarksGalleryScreen() {
        tocBookmarksGalleryPdfScreen.tapResumeButton();
    }

    @Override
    public void addBookmarkOnReaderPdfScreen() {
        readerPdfScreen.openNavigationBar();
        readerPdfScreen.openNavigationBar();
        readerPdfScreen.getNavigationBarScreen().tapAddBookmarkButton();
    }

    @Override
    public void deleteBookmarkOnReaderPdfScreen() {
        readerPdfScreen.openNavigationBar();
        readerPdfScreen.openNavigationBar();
        readerPdfScreen.getNavigationBarScreen().tapDeleteBookmarkButton();
    }

    @Override
    public void checkPdfPageNumberIsEqualTo(int pageNumber) {
        int expectedPdfPageNumber = pageNumber;
        int actualPdfPageNumber = readerPdfScreen.getPageNumber();
        Assert.assertEquals("Pdf page number is not correct on pdf reader screen. Expected - " + expectedPdfPageNumber + ", actual - " + actualPdfPageNumber, expectedPdfPageNumber, actualPdfPageNumber);
    }

    @Override
    public void goToPreviousPdfPage() {
        readerPdfScreen.goToPreviousPage();
    }

    @Override
    public void goToNextPdfPage() {
        readerPdfScreen.goToNextPage();
    }

    @Override
    public void checkThatRandomChapterOfPdfBookCanBeOpenedFromTocPdfScreen() {
        readerPdfScreen.openToc();
        List<String> chapters = tocPdfScreen.getListOfBookChapters();
        if (chapters.size() == 0) {
            throw new RuntimeException("size of the listOfBookChapters for toc pdf == 0");
        }
        int randomChapterNumber = RandomUtils.nextInt(2, (int) chapters.stream().limit(5).count());
        int expectedChapterNumber = tocPdfScreen.getChapterNumber(chapters.get(randomChapterNumber));
        tocPdfScreen.openChapter(chapters.get(randomChapterNumber));
        int actualChapterNumber = readerPdfScreen.getPageNumber();
        Assert.assertTrue("Pdf chapter number is not correct on reader pdf screen. Expected - " + expectedChapterNumber
                + ", actual - " + actualChapterNumber, actualChapterNumber == expectedChapterNumber);
    }

    @Override
    public void savePdfPageNumber(String pageNumberKey) {
        readerPdfScreen.openNavigationBar();
        readerPdfScreen.openNavigationBar();
        context.add(pageNumberKey, readerPdfScreen.getPageNumber());
    }

    @Override
    public void swipePdfPageForwardSeveralTimes(int minValue, int maxValue) {
        int randomSwipeCount = RandomUtils.nextInt(minValue, maxValue);
        AqualityServices.getLogger().info("Swipe " + randomSwipeCount + " times on reader pdf screen");
        IntStream.range(0, randomSwipeCount).forEach(i -> readerPdfScreen.goToNextPage());
    }

    @Override
    public void slidePdfPageSlider(EntireScreenDragDirection entireScreenDragDirection) {
        readerPdfScreen.slidePageSlider(entireScreenDragDirection);
    }

    @Override
    public void checkThatSavedPdfPageNumberIsLessThanCurrentPdfPageNumber(String pageNumberKey) {
        int savedPageNumber = context.get(pageNumberKey);
        int currentPageNumber = readerPdfScreen.getPageNumber();
        Assert.assertTrue("Saved page number is greater that current page number on reader pdf screen. SavedPageNumber - " +
                savedPageNumber + ", currentPageNumber - " + currentPageNumber, savedPageNumber < currentPageNumber);
    }

    @Override
    public void checkThatSavedPdfPageNumberIsEqualToCurrentPdfPageNumber(String pageNumberKey) {
        readerPdfScreen.openNavigationBar();
        readerPdfScreen.openNavigationBar();
        int savedPageNumber = context.get(pageNumberKey);
        int currentPageNumber = readerPdfScreen.getPageNumber();
        Assert.assertTrue("Saved page number is not equal to current page number on reader pdf screen. SavedPageNumber - " +
                savedPageNumber + ", currentPageNumber - " + currentPageNumber, savedPageNumber == currentPageNumber);

    }

    @Override
    public void checkThatSavedPdfPageNumberIsGreaterThanCurrentPdfPageNumber(String pageNumberKey) {
        int savedPageNumber = context.get(pageNumberKey);
        int currentPageNumber = readerPdfScreen.getPageNumber();
        Assert.assertTrue("Saved page number is less than current page number on reader pdf screen. SavedPageNumber - " +
                savedPageNumber + ", currentPageNumber - " + currentPageNumber, savedPageNumber > currentPageNumber);
    }

    @Override
    public void openGalleryPdfScreen() {
        readerPdfScreen.openNavigationBar();
        readerPdfScreen.getNavigationBarScreen().openTocBookmarksGallery();
        tocBookmarksGalleryPdfScreen.tapGalleryButton();
    }

    @Override
    public void openBookmarksPdfScreen() {
        readerPdfScreen.openNavigationBar();
        readerPdfScreen.openNavigationBar();
        readerPdfScreen.getNavigationBarScreen().openTocBookmarksGallery();
        tocBookmarksGalleryPdfScreen.tapBookmarksButton();
    }

    @Override
    public void checkBookmarksPdfScreenIsOpened() {
        Assert.assertTrue("Bookmarks pdf screen is not opened", tocBookmarksGalleryPdfScreen.getBookmarksPdfScreen().state().isDisplayed());
    }

    @Override
    public void openRandomPdfPageAndSavePageNumberOnGalleryScreen(String pageNumberKey) {
        GalleryPdfScreen galleryPdfScreen = tocBookmarksGalleryPdfScreen.getGalleryPdfScreen();
        int countOfPdfPages = galleryPdfScreen.getCountOfBookPages();
        int randomPageNumber = RandomUtils.nextInt(0, countOfPdfPages);
        galleryPdfScreen.openGalleryPage(randomPageNumber);
        context.add(pageNumberKey, randomPageNumber + 1);
    }

    @Override
    public void openSearchPdfScreen() {
        readerPdfScreen.openNavigationBar();
        readerPdfScreen.getNavigationBarScreen().tapSearchButton();
    }

    @Override
    public void checkSearchPdfScreenIsOpened() {
        Assert.assertTrue("search pdf screen is not opened", readerPdfScreen.getSearchPdfScreen().state().isDisplayed());
    }

    @Override
    public void checkGalleryPdfScreenIsOpened() {
        Assert.assertTrue("gallery pdf screen is not opened", tocBookmarksGalleryPdfScreen.getGalleryPdfScreen().state().isDisplayed());
    }

    @Override
    public void searchTextOnSearchPdfScreen(String text) {
        readerPdfScreen.getSearchPdfScreen().findTextInDocument(text);
    }

    @Override
    public void checkThatPdfFoundLinesContainText(String textThatShouldBe) {
        SoftAssertions softAssertions = new SoftAssertions();
        readerPdfScreen.getSearchPdfScreen().getListOfFoundTexts().forEach(foundText -> System.out.println("foundText-" + foundText));
        readerPdfScreen.getSearchPdfScreen().getListOfFoundTexts().forEach(foundText -> softAssertions.assertThat(foundText.toLowerCase().contains(textThatShouldBe.toLowerCase())).
                as(String.format("Found text '%1$s' does not contain text '%2$s'. ", foundText, textThatShouldBe)).isTrue());
        softAssertions.assertAll();
    }

    @Override
    public void openTheFirstFoundText() {
        readerPdfScreen.getSearchPdfScreen().openTheFirstFoundText();
    }

    @Override
    public void savePageNumberOfTheFirstFoundText(String pageNumberKey) {
        context.add(pageNumberKey, readerPdfScreen.getSearchPdfScreen().getNumberOfTheFirstFoundText());
    }
}
