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
import screens.epub.readerEpub.ReaderEpubScreen;
import screens.pdf.readerPdf.ReaderPdfScreen;
import screens.pdf.searchPdf.SearchPdfScreen;
import screens.pdf.tocPdf.TocPdfScreen;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReaderSteps {
    private final ReaderEpubScreen readerEpubScreen;
    private final ReaderPdfScreen readerPdfScreen;
    private final ScenarioContext context;
    private final TocPdfScreen tocPdfScreen;
    private final SearchPdfScreen searchPdfScreen;
    private final AudioPlayerScreen audioPlayerScreen;

    @Inject
    public ReaderSteps(ScenarioContext context) {
        readerEpubScreen = AqualityServices.getScreenFactory().getScreen(ReaderEpubScreen.class);
        readerPdfScreen = AqualityServices.getScreenFactory().getScreen(ReaderPdfScreen.class);
        tocPdfScreen = AqualityServices.getScreenFactory().getScreen(TocPdfScreen.class);
        searchPdfScreen = AqualityServices.getScreenFactory().getScreen(SearchPdfScreen.class);
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
        String actualBookName = readerPdfScreen.getBookName();
        Assert.assertTrue(String.format("BookName(pdf) is not correct. Expected bookName - '%1$s', actualName - '%2$s'", expectedBookName, actualBookName), actualBookName.contains(expectedBookName));
    }

    @Then("Pdf book page number is {int}")
    public void checkPdfBookPageNumberIs(int pageNumber) {
        checkPageNumberIsEqualTo(pageNumber);
    }

    @When("I go to previous page in pdf book")
    public void goToPreviousPage() {
        readerPdfScreen.goToPreviousPage();
    }

    @When("I go to next page in pdf book")
    public void goToNextPage() {
        readerPdfScreen.goToNextPage();
    }

    @And("Each chapter of pdf book can be opened from Table of Contents")
    public void checkEachChapterOfPdfBookCanBeOpenedFromTableOfContents() {
        SoftAssertions softAssertions = new SoftAssertions();
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.ANDROID) {
            readerPdfScreen.clickToc();
        } else if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
            readerPdfScreen.openAdditionalButtonsAndLabels();
            readerPdfScreen.getNavigationBarPdfScreen().clickTocAndBookmarksAndGalleryButton();
        }
        //readerPdfScreen.openTableOfContents();
        tocPdfScreen.switchToChaptersListView();
        Set<String> chapters = tocPdfScreen.getListOfBookChapters();
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.ANDROID) {
            AqualityServices.getApplication().getDriver().navigate().back();
        } else if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
            tocPdfScreen.clickResumeButton();
        }
        for (String chapter : chapters.stream().limit(5).collect(Collectors.toList())) {
            if (AqualityServices.getApplication().getPlatformName() == PlatformName.ANDROID) {
                readerPdfScreen.clickToc();
            } else if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
                readerPdfScreen.openAdditionalButtonsAndLabels();
                readerPdfScreen.getNavigationBarPdfScreen().clickTocAndBookmarksAndGalleryButton();
            }
            //readerPdfScreen.openTableOfContents();
            tocPdfScreen.state().waitForDisplayed();
            int pageNumber = tocPdfScreen.getChapterPageNumber(chapter);
            tocPdfScreen.openChapter(chapter);
            softAssertions.assertThat(readerPdfScreen.getPageNumber()).as("Chapter name is not correct").isEqualTo(pageNumber);
        }
        softAssertions.assertAll();
    }

    @And("Pdf page number {string} is correct")
    public void checkPdfPageNumberIsCorrect(String pageNumberKey) {
        checkPageNumberIsEqualTo(context.get(pageNumberKey));
    }

    @And("I save pdf page number as {string}")
    public void savePdfPageNumberAs(String pageNumberKey) {
        context.add(pageNumberKey, readerPdfScreen.getPageNumber());
    }

    @When("I scroll pdf page forward from {int} to {int} times")
    public void scrollPdfPageForward(int minValue, int maxValue) {
        IntStream.range(0, RandomUtils.nextInt(minValue, maxValue)).forEachOrdered(i -> readerPdfScreen.goToNextPage());
    }

    @And("Slide page slider {}")
    public void slidePageSlider(EntireScreenDragDirection entireScreenDragDirection) {
        readerPdfScreen.slidePageSlider(entireScreenDragDirection);
    }

    @Then("Pdf saved page number {string} should not be equal to current")
    public void checkThatSavedPageNumberDoesNotEqualToCurrent(String pageNumberKey) {
        checkPageNumberIsNotEqualTo(context.get(pageNumberKey));
    }

    @When("I open gallery menu")
    public void openChaptersGallery() {
        readerPdfScreen.openAdditionalButtonsAndLabels();
        readerPdfScreen.getNavigationBarPdfScreen().clickTocAndBookmarksAndGalleryButton();
        //readerPdfScreen.openGallery();
    }

    @Then("Gallery is opened")
    public void isGalleryPagesLoad() {
        tocPdfScreen.isGalleryPagesLoaded();
    }

    @And("I save number of page on the gallery as {string}")
    public void saveNumberOfPage(String pageNumberKey) {
        context.add(pageNumberKey, readerPdfScreen.getPageNumber());
    }

    @When("I scroll the gallery page {}")
    public void scrollGalleryPageDown(EntireElementSwipeDirection entireElementSwipeDirection) {
        tocPdfScreen.scrollGallery(entireElementSwipeDirection);
    }

    @Then("Current page number is bigger than number {string}")
    public void checkThatNumberOfPageMoreThanAnotherNumberOfPage(String firstNumberOfPageKey) {
        int firstNumberOfPage = context.get(firstNumberOfPageKey);
        int currentNumberOfPage = readerPdfScreen.getPageNumber();
        Assert.assertTrue(String.format("Current page: %d should be more than first open page: %d.", currentNumberOfPage, firstNumberOfPage), currentNumberOfPage > firstNumberOfPage);
    }

    @When("I open random page on the gallery")
    public void openAnyPageOnGalleryScreen() {
        int countOfPages = tocPdfScreen.getCountOfBookPages();
        tocPdfScreen.openGalleryPage(RandomUtils.nextInt(0, countOfPages));
    }

    @When("I open last page on the gallery")
    public void openLastPageOnGalleryScreen() {
        int countOfPages = tocPdfScreen.getCountOfBookPages();
        tocPdfScreen.openGalleryPage(countOfPages - 1);
    }

    @When("I click the search in the pdf button")
    public void openSearchPdf() {
        readerPdfScreen.openAdditionalButtonsAndLabels();
        readerPdfScreen.getNavigationBarPdfScreen().clickSearchButton();
        //readerPdfScreen.openSearchPdf();
    }

    @Then("The search in the pdf page opened")
    public void checkThatPdfSearchScreenVisible() {
        Assert.assertTrue("Pdf search screen was not opened", searchPdfScreen.state().isDisplayed());
    }

    @When("I am typing {string} to the search field and apply search")
    public void checkThatPdfSearchScreenVisible(String textToBeFound) {
        searchPdfScreen.findTextInDocument(textToBeFound);
    }

    @Then("Found lines should contain {string} in themselves")
    public void checkThatPdfFoundLinesContainText(String textToBeContained) {
        SoftAssertions softAssertions = new SoftAssertions();
        searchPdfScreen.getListOfFoundItems()
                .forEach(line -> softAssertions.assertThat(line.toLowerCase(Locale.ROOT).contains(textToBeContained.toLowerCase(Locale.ROOT))).as(String.format("Line '%1$s' does not contain text '%2$s'", line, textToBeContained)).isTrue());
        softAssertions.assertAll();
    }

    @When("I open the first found item")
    public void openFirstFoundItem() {
        searchPdfScreen.openSearchedItemByName(searchPdfScreen.getListOfFoundItems().get(0));
    }

    @When("I save page number as {string} of the first item")
    public void savePageNumberOfFirstItem(String pageKey) {
        context.add(pageKey, searchPdfScreen.getSearchedItemPageNumber(0));
    }

    @Then("Book {string} with {} type is present on epub or pdf or audiobook screen")
    public void readerScreenForEbookTypeIsPresent(String bookInfoKey, EnumBookType bookType) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        String bookName = catalogBookModel.getTitle();
        switch (bookType) {
            case EBOOK:
                if (readerEpubScreen.state().waitForDisplayed()) {
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
        String actualBookName = readerEpubScreen.getBookName();
        Assert.assertTrue(String.format("BookName(epub) is not correct. Expected bookName - '%1$s', actualName - '%2$s'", expectedBookName, actualBookName), actualBookName.contains(expectedBookName));
    }

    private void checkPageNumberIsEqualTo(int pageNumber) {
        Assert.assertEquals("Page number is not correct", pageNumber, readerPdfScreen.getPageNumber());
    }

    private void checkPageNumberIsNotEqualTo(int pageNumber) {
        Assert.assertNotEquals("Page number is not correct", pageNumber, readerPdfScreen.getPageNumber());
    }
}
