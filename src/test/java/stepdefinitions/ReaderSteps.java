package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import constants.application.EnumBookType;
import framework.utilities.ScenarioContext;
import framework.utilities.swipe.directions.EntireElementSwipeDirection;
import framework.utilities.swipe.directions.EntireScreenDragDirection;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.android.CatalogBookModel;
import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import screens.audioplayer.AudioPlayerScreen;
import screens.epubreader.EpubReaderScreen;
import screens.pdfreader.PdfReaderScreen;
import screens.pdfsearch.PdfSearchScreen;
import screens.pdftableofcontents.PdfTableOfContentsScreen;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReaderSteps {
    private final EpubReaderScreen epubReaderScreen;
    private final PdfReaderScreen pdfReaderScreen;
    private final ScenarioContext context;
    private final PdfTableOfContentsScreen pdfTableOfContentsScreen;
    private final PdfSearchScreen pdfSearchScreen;
    private final AudioPlayerScreen audioPlayerScreen;

    @Inject
    public ReaderSteps(ScenarioContext context) {
        epubReaderScreen = AqualityServices.getScreenFactory().getScreen(EpubReaderScreen.class);
        pdfReaderScreen = AqualityServices.getScreenFactory().getScreen(PdfReaderScreen.class);
        pdfTableOfContentsScreen = AqualityServices.getScreenFactory().getScreen(PdfTableOfContentsScreen.class);
        pdfSearchScreen = AqualityServices.getScreenFactory().getScreen(PdfSearchScreen.class);
        audioPlayerScreen = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen.class);
        this.context = context;
    }

    @Then("Pdf book {string} is present on screen")
    public void checkPdfBookBookInfoIsPresentOnScreen(String bookInfoKey) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        assertBookNameForPdf(catalogBookModel);
    }

    private void assertBookNameForPdf(CatalogBookModel catalogBookModel) {
        String expectedBookName = catalogBookModel.getTitle();
        String actualBookName = pdfReaderScreen.getBookName();
        Assert.assertTrue(String.format("BookName(pdf) is not correct. Expected bookName - '%1$s', actualName - '%2$s'", expectedBookName, actualBookName), actualBookName.contains(expectedBookName));
    }

    @Then("Pdf book page number is {int}")
    public void checkPdfBookPageNumberIs(int pageNumber) {
        checkPageNumberIsEqualTo(pageNumber);
    }

    @When("I go to previous page in pdf book")
    public void goToPreviousPage() {
        pdfReaderScreen.goToPreviousPage();
    }

    @When("I go to next page in pdf book")
    public void goToNextPage() {
        pdfReaderScreen.goToNextPage();
    }

    @And("Each chapter of pdf book can be opened from Table of Contents")
    public void checkEachChapterOfPdfBookCanBeOpenedFromTableOfContents() {
        SoftAssertions softAssertions = new SoftAssertions();
        pdfReaderScreen.openTableOfContents();
        pdfTableOfContentsScreen.switchToChaptersListView();
        Set<String> chapters = pdfTableOfContentsScreen.getListOfBookChapters();
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.ANDROID) {
            AqualityServices.getApplication().getDriver().navigate().back();
        } else if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
            pdfTableOfContentsScreen.clickResumeButton();
        }
        for (String chapter : chapters.stream().limit(5).collect(Collectors.toList())) {
            pdfReaderScreen.openTableOfContents();
            pdfTableOfContentsScreen.state().waitForDisplayed();
            int pageNumber = pdfTableOfContentsScreen.getChapterPageNumber(chapter);
            pdfTableOfContentsScreen.openChapter(chapter);
            softAssertions.assertThat(pdfReaderScreen.getPageNumber()).as("Chapter name is not correct").isEqualTo(pageNumber);
        }
        softAssertions.assertAll();
    }

    @And("Pdf page number {string} is correct")
    public void checkPdfPageNumberIsCorrect(String pageNumberKey) {
        checkPageNumberIsEqualTo(context.get(pageNumberKey));
    }

    @And("I save pdf page number as {string}")
    public void savePdfPageNumberAs(String pageNumberKey) {
        context.add(pageNumberKey, pdfReaderScreen.getPageNumber());
    }

    @When("I scroll pdf page forward from {int} to {int} times")
    public void scrollPdfPageForward(int minValue, int maxValue) {
        IntStream.range(0, RandomUtils.nextInt(minValue, maxValue)).forEachOrdered(i -> pdfReaderScreen.goToNextPage());
    }

    @And("Slide page slider {}")
    public void slidePageSlider(EntireScreenDragDirection entireScreenDragDirection) {
        pdfReaderScreen.slidePageSlider(entireScreenDragDirection);
    }

    @Then("Pdf saved page number {string} should not be equal to current")
    public void checkThatSavedPageNumberDoesNotEqualToCurrent(String pageNumberKey) {
        checkPageNumberIsNotEqualTo(context.get(pageNumberKey));
    }

    @When("I open gallery menu")
    public void openChaptersGallery() {
        pdfReaderScreen.openChaptersGallery();
    }

    @Then("Gallery is opened")
    public void isGalleryPagesLoad() {
        pdfTableOfContentsScreen.isGalleryPagesLoaded();
    }

    @And("I save number of page on the gallery as {string}")
    public void saveNumberOfPage(String pageNumberKey) {
        context.add(pageNumberKey, pdfReaderScreen.getPageNumber());
    }

    @When("I scroll the gallery page {}")
    public void scrollGalleryPageDown(EntireElementSwipeDirection entireElementSwipeDirection) {
        pdfTableOfContentsScreen.scrollGallery(entireElementSwipeDirection);
    }

    @Then("Current page number is bigger than number {string}")
    public void checkThatNumberOfPageMoreThanAnotherNumberOfPage(String firstNumberOfPageKey) {
        int firstNumberOfPage = context.get(firstNumberOfPageKey);
        int currentNumberOfPage = pdfReaderScreen.getPageNumber();
        Assert.assertTrue(String.format("Current page: %d should be more than first open page: %d.", currentNumberOfPage, firstNumberOfPage), currentNumberOfPage > firstNumberOfPage);
    }

    @When("I open random page on the gallery")
    public void openAnyPageOnGalleryScreen() {
        int countOfPages = pdfTableOfContentsScreen.getCountOfBookPages();
        pdfTableOfContentsScreen.openGalleryPage(RandomUtils.nextInt(0, countOfPages));
    }

    @When("I open last page on the gallery")
    public void openLastPageOnGalleryScreen() {
        int countOfPages = pdfTableOfContentsScreen.getCountOfBookPages();
        pdfTableOfContentsScreen.openGalleryPage(countOfPages - 1);
    }

    @When("I click the search in the pdf button")
    public void openSearchPdf() {
        pdfReaderScreen.openSearchPdf();
    }

    @Then("The search in the pdf page opened")
    public void checkThatPdfSearchScreenVisible() {
        Assert.assertTrue("Pdf search screen was not opened", pdfSearchScreen.state().isDisplayed());
    }

    @When("I am typing {string} to the search field and apply search")
    public void checkThatPdfSearchScreenVisible(String textToBeFound) {
        pdfSearchScreen.findTextInDocument(textToBeFound);
    }

    @Then("Found lines should contain {string} in themselves")
    public void checkThatPdfFoundLinesContainText(String textToBeContained) {
        SoftAssertions softAssertions = new SoftAssertions();
        pdfSearchScreen.getListOfFoundItems()
                .forEach(line -> softAssertions.assertThat(line.toLowerCase(Locale.ROOT).contains(textToBeContained.toLowerCase(Locale.ROOT))).as(String.format("Line '%1$s' does not contain text '%2$s'", line, textToBeContained)).isTrue());
        softAssertions.assertAll();
    }

    @When("I open the first found item")
    public void openFirstFoundItem() {
        pdfSearchScreen.openSearchedItemByName(pdfSearchScreen.getListOfFoundItems().get(0));
    }

    @When("I save page number as {string} of the first item")
    public void savePageNumberOfFirstItem(String pageKey) {
        context.add(pageKey, pdfSearchScreen.getSearchedItemPageNumber(0));
    }

    @Then("Book {string} with {} type is present on epub or pdf or audiobook screen")
    public void readerScreenForEbookTypeIsPresent(String bookInfoKey, EnumBookType bookType) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        String bookName = catalogBookModel.getTitle();
        switch (bookType) {
            case EBOOK:
                if (epubReaderScreen.state().waitForDisplayed()) {
                    assertBookNameForEpub(catalogBookModel);
                } else {
                    assertBookNameForPdf(catalogBookModel);
                }
                break;
            case AUDIOBOOK:
                Assert.assertTrue("AudiobookName is not present on audiobook screen", audioPlayerScreen.isAudiobookNamePresent(bookName));
                break;
        }
    }

    private void assertBookNameForEpub(CatalogBookModel catalogBookModel) {
        String expectedBookName = catalogBookModel.getTitle();
        String actualBookName = epubReaderScreen.getBookName();
        Assert.assertTrue(String.format("BookName(epub) is not correct. Expected bookName - '%1$s', actualName - '%2$s'", expectedBookName, actualBookName), actualBookName.contains(expectedBookName));
    }

    private void checkPageNumberIsEqualTo(int pageNumber) {
        Assert.assertEquals("Page number is not correct", pageNumber, pdfReaderScreen.getPageNumber());
    }

    private void checkPageNumberIsNotEqualTo(int pageNumber) {
        Assert.assertNotEquals("Page number is not correct", pageNumber, pdfReaderScreen.getPageNumber());
    }
}
