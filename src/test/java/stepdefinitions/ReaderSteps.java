package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.RegEx;
import constants.application.ReaderType;
import constants.localization.application.reader.ColorKeys;
import constants.localization.application.reader.FontNameKeys;
import constants.localization.application.reader.ReaderSettingKeys;
import framework.utilities.RegExUtil;
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
import screens.epubtableofcontents.EpubTableOfContentsScreen;
import screens.fontchoicesscreen.FontChoicesScreen;
import screens.pdfreader.PdfReaderScreen;
import screens.pdfsearch.PdfSearchScreen;
import screens.pdftableofcontents.PdfTableOfContentsScreen;

import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReaderSteps {
    private final EpubReaderScreen epubReaderScreen;
    private final PdfReaderScreen pdfReaderScreen;
    private final ScenarioContext context;
    private final EpubTableOfContentsScreen epubTableOfContentsScreen;
    private final PdfTableOfContentsScreen pdfTableOfContentsScreen;
    private final PdfSearchScreen pdfSearchScreen;
    private final FontChoicesScreen fontChoicesScreen;
    private final AudioPlayerScreen audioPlayerScreen;

    @Inject
    public ReaderSteps(ScenarioContext context) {
        epubReaderScreen = AqualityServices.getScreenFactory().getScreen(EpubReaderScreen.class);
        pdfReaderScreen = AqualityServices.getScreenFactory().getScreen(PdfReaderScreen.class);
        epubTableOfContentsScreen = AqualityServices.getScreenFactory().getScreen(EpubTableOfContentsScreen.class);
        pdfTableOfContentsScreen = AqualityServices.getScreenFactory().getScreen(PdfTableOfContentsScreen.class);
        fontChoicesScreen = AqualityServices.getScreenFactory().getScreen(FontChoicesScreen.class);
        pdfSearchScreen = AqualityServices.getScreenFactory().getScreen(PdfSearchScreen.class);
        audioPlayerScreen = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen.class);
        this.context = context;
    }

    @Then("Book {string} is present on screen")
    public void checkBookInfoIsPresentOnScreen(String bookInfoKey) {
        assertBookName(context.get(bookInfoKey));
        epubReaderScreen.waitForBookLoading();
    }

    @Then("Book page number is {int}")
    public void checkBookPageNumberIs(int pageNumber) {
        Assert.assertEquals("Book page number is not correct", pageNumber, getPageNumber(epubReaderScreen.getPageNumberInfo()));
    }

    @When("I swipe from left to right book corner")
    public void swipeFromLeftToRightBookCorner() {
        epubReaderScreen.swipeFromLeftToRight();
    }

    @When("I swipe from right to left book corner")
    public void swipeFromRightToLeftBookCorner() {
        epubReaderScreen.swipeFromRightToLeft();
    }

    @When("I click on left book corner")
    public void clickOnLeftBookCorner() {
        epubReaderScreen.clickLeftCorner();
    }

    @When("I click on right book corner")
    public void clickOnRightBookCorner() {
        epubReaderScreen.clickRightCorner();
    }

    @When("I save page info as {string}")
    public void savePageInfoAsPageInfo(String pageNumberInfo) {
        context.add(pageNumberInfo, epubReaderScreen.getPageNumberInfo());
    }

    @Then("Book page number is bigger then previous {string}")
    public void checkBookPageNumberIsBiggerThenPreviousPageInfo(String pageNumberInfo) {
        String actualBookInfo = epubReaderScreen.getPageNumberInfo();
        String expectedBookInfo = context.get(pageNumberInfo);
        int expectedPageNumber = getPageNumber(expectedBookInfo) + 1;
        int actualPageNumber = getPageNumber(actualBookInfo);
        Assert.assertTrue(String.format("Page number is not correct (actual - %d, expected - %d)", actualPageNumber, expectedPageNumber), expectedPageNumber == actualPageNumber ||
                (actualPageNumber == 1 && !getChapterName(expectedBookInfo).equals(getChapterName(actualBookInfo))));
    }

    private int getPageNumber(String text) {
        return RegExUtil.getIntFromFirstGroup(text, RegEx.PAGE_NUMBER_REGEX);
    }

    private String getChapterName(String text) {
        Matcher matcher = getMatcher(text);
        return matcher.find() ? matcher.group(3) : "";
    }

    @Then("Book page number is smaller then previous {string}")
    public void bookPageNumberIsSmallerThenPreviousPageInfo(String pageNumberInfo) {
        String expectedBookInfo = context.get(pageNumberInfo);
        AqualityServices.getConditionalWait().waitFor(() -> !expectedBookInfo.equals(epubReaderScreen.getPageNumberInfo()));
        String actualBookInfo = epubReaderScreen.getPageNumberInfo();
        int expectedPageNumber = getPageNumber(expectedBookInfo) - 1;
        int actualPageNumber = getPageNumber(actualBookInfo);
        Assert.assertTrue(String.format("Page number is not correct (actual - %d, expected - %d)", actualPageNumber, expectedPageNumber), expectedPageNumber == actualPageNumber ||
                (!getChapterName(expectedBookInfo).equals(getChapterName(actualBookInfo))));
    }

    private Matcher getMatcher(String text) {
        return RegExUtil.getMatcher(text, RegEx.PAGE_NUMBER_REGEX);
    }

    @And("Each chapter can be opened from Table of Contents")
    public void checkEachChapterCanBeOpenedFromTableOfContents() {
        //todo softAssert
        SoftAssertions softAssertions = new SoftAssertions();
        Set<String> chapters = epubReaderScreen.getListOfChapters();
        for (String chapter :
                chapters) {
            epubReaderScreen.openChapter(chapter);
            softAssertions.assertThat(getChapterName(epubReaderScreen.getPageNumberInfo())).as("Chapter name is not correct").isEqualTo(chapter);
        }
        softAssertions.assertAll();
    }

    @When("I open font choices for book")
    public void openFontChoicesForBook() {
        epubReaderScreen.openFontSettings();
    }

    @And("I open Table of Contents")
    public void openTableOfContents() {
        epubReaderScreen.openTableOfContents();
    }

    @Then("Table of Contents is opened")
    public void checkTableOfContentsIsOpened() {
        Assert.assertTrue("Table of Contents is not opened", epubTableOfContentsScreen.state().waitForDisplayed());
    }

    @Then("Font choices screen is present")
    public void checkFontChoicesScreenIsPresent() {
        Assert.assertTrue("Font choices screen is not opened", fontChoicesScreen.state().waitForDisplayed());
    }

    @When("I close font choices")
    public void closeFontChoices() {
        fontChoicesScreen.closeFontChoices();
    }

    @When("I save font size as {string}")
    public void saveFontSize(String fontSizeKey) {
        context.add(fontSizeKey, epubReaderScreen.getFontSize());
    }

    @Then("Font size {string} is increased")
    public void checkFontSizeIsIncreased(String fontSizeKey) {
        double actualFontSize = epubReaderScreen.getFontSize();
        double expectedFontSize = context.get(fontSizeKey);
        Assert.assertTrue("Font size is not increased actual - " + actualFontSize + ", expected - " + expectedFontSize, actualFontSize > expectedFontSize);
    }

    @Then("Font size {string} is decreased")
    public void checkFontSizeIsDecreased(String fontSizeKey) {
        double actualFontSize = epubReaderScreen.getFontSize();
        double expectedFontSize = context.get(fontSizeKey);
        Assert.assertTrue("Font size is not decreased actual - " + actualFontSize + ", expected - " + expectedFontSize, actualFontSize < expectedFontSize);
    }

    @When("I {} of text")
    @When("I change font style to {}")
    @When("I change contrast to {}")
    public void changeSettingsForFont(ReaderSettingKeys readerSettingKey) {
        changeSetting(readerSettingKey);
    }

    @Then("Book text displays {} on {}")
    public void checkBookTextDisplaysWhiteTextOnBlack(ColorKeys text, ColorKeys background) {
        assertFontAndBackground(text, background);
    }

    @Then("Book text displays in {} font")
    public void bookTextDisplaysInSerifFont(FontNameKeys key) {
        assertFontName(key.i18n());
    }

    @And("Page info {string} is correct")
    public void checkPageInfoPageInfoIsCorrect(String pageNumberInfo) {
        String pageInfo = context.get(pageNumberInfo);

        Assert.assertTrue(String.format("Page info is not correct. Expected %1$s but actual %2$s", pageInfo, epubReaderScreen.getPageNumberInfo()), AqualityServices.getConditionalWait().waitFor(() -> isPageNumberEqual(pageInfo)));
    }

    @When("I scroll page forward from {int} to {int} times")
    public void scrollPageForward(int minValue, int maxValue) {
        int randomScrollsCount = RandomUtils.nextInt(minValue, maxValue);
        AqualityServices.getLogger().info("Scrolling " + randomScrollsCount + " times");
        IntStream.range(0, randomScrollsCount).forEachOrdered(i -> {
            String pageNumber = epubReaderScreen.getPageNumberInfo();
            epubReaderScreen.clickRightCorner();
            AqualityServices.getConditionalWait().waitFor(() -> !isPageNumberEqual(pageNumber));
        });
        //todo added waiting
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("Pdf book {string} is present on screen")
    public void checkPdfBookBookInfoIsPresentOnScreen(String bookInfoKey) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        assertPdfBookName(catalogBookModel);
    }

    private void assertPdfBookName(CatalogBookModel catalogBookModel) {
        Assert.assertTrue(String.format("Book name is not correct. Expected that name ['%1$s'] would contains in ['%2$s']",
                catalogBookModel.getTitle(), getTrimmedBookName()), AqualityServices.getConditionalWait().waitFor(() ->
                        getTrimmedBookName().replace(" ", "").contains(catalogBookModel.getTitle().replace(" ", ""))));
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
        //todo softAssert
        SoftAssertions softAssertions = new SoftAssertions();
        pdfReaderScreen.openTableOfContents();
        pdfTableOfContentsScreen.switchToChaptersListView();
        Set<String> chapters = pdfTableOfContentsScreen.getListOfBookChapters();
        AqualityServices.getApplication().getDriver().navigate().back();
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
        //todo softAssert
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

    @Then("Reader screen for {} type book {string} is present")
    public void readerScreenForEbookTypeIsPresent(ReaderType readerType, String bookInfoKey) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        switch (readerType) {
            case EBOOK:
                if (epubReaderScreen.isBookNamePresent()) {
                    assertBookName(catalogBookModel);
                } else {
                    assertPdfBookName(catalogBookModel);
                }
                break;
            case AUDIOBOOK:
                Assert.assertTrue("Audiobook screen is not present", audioPlayerScreen.state().waitForDisplayed());
                break;
        }
    }

    private void assertBookName(CatalogBookModel catalogBookModel) {
        // TODO: 29.04.2021 deleted ThreadSleep
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String expectedBookName = prepareBookName(catalogBookModel.getTitle());
        String actualBookName = prepareBookName(epubReaderScreen.getBookName());
        Assert.assertTrue(String.format("Book name is not correct. Expected name - '%1$s', actual name - '%2$s'", expectedBookName, actualBookName), actualBookName.contains(expectedBookName));
    }

    private String prepareBookName(String title) {
        return removeSpaces(title.toLowerCase().replace("women", "woman"));
    }

    private String removeSpaces(String text) {
        return text.replace("   ", " ");
    }

    private boolean isPageNumberEqual(String pageNumber) {
        return epubReaderScreen.getPageNumberInfo().equals(pageNumber);
    }

    private void checkPageNumberIsEqualTo(int pageNumber) {
        Assert.assertEquals("Page number is not correct", pageNumber, pdfReaderScreen.getPageNumber());
    }

    private void checkPageNumberIsNotEqualTo(int pageNumber) {
        Assert.assertNotEquals("Page number is not correct", pageNumber, pdfReaderScreen.getPageNumber());
    }

    private void changeSetting(ReaderSettingKeys settingName) {
        epubReaderScreen.openFontSettings();
        fontChoicesScreen.setSetting(settingName);
        fontChoicesScreen.closeFontChoices();
    }

    private void assertFontAndBackground(ColorKeys fontColor, ColorKeys backgroundColor) {
        //todo softAssert
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(epubReaderScreen.getFontColor()).as("Font color is not correct").isEqualTo(fontColor.i18n());
        softAssertions.assertThat(epubReaderScreen.getBackgroundColor()).as("Background color is not correct").isEqualTo(backgroundColor.i18n());
        softAssertions.assertAll();
    }

    private void assertFontName(String fontName) {
        Assert.assertEquals("Book font is not correct", fontName, epubReaderScreen.getFontName());
    }

    private String getTrimmedBookName() {
        return pdfReaderScreen.getBookName().trim().replaceAll("[\n\r]", "");
    }
}
