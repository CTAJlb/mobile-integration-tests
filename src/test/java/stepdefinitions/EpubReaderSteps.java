package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.localization.application.reader.BackgroundColorKeys;
import constants.localization.application.reader.FontNameKeys;
import constants.localization.application.reader.ReaderSettingKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.android.CatalogBookModel;
import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import screens.epubreader.EpubReaderScreen;
import screens.epubtableofcontents.EpubTableOfContentsScreen;
import screens.fontchoicesscreen.FontChoicesScreen;

import java.util.List;
import java.util.stream.IntStream;

public class EpubReaderSteps {
    private FontChoicesScreen fontChoicesScreen;
    private EpubTableOfContentsScreen epubTableOfContentsScreen;
    private ScenarioContext context;
    private EpubReaderScreen epubReaderScreen;

    @Inject
    public EpubReaderSteps(ScenarioContext context) {
        fontChoicesScreen = AqualityServices.getScreenFactory().getScreen(FontChoicesScreen.class);
        epubTableOfContentsScreen = AqualityServices.getScreenFactory().getScreen(EpubTableOfContentsScreen.class);
        epubReaderScreen = AqualityServices.getScreenFactory().getScreen(EpubReaderScreen.class);
        this.context = context;
    }


    @Then("{string} book is present on epub reader screen")
    public void isBookPresent(String bookInfoKey) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        String expectedBookName = catalogBookModel.getTitle();
        String actualBookName = epubReaderScreen.getBookName();
        Assert.assertTrue(String.format("Book is not present on epub reader screen. Expected bookName - '%1$s', actualName - '%2$s'", expectedBookName, actualBookName), actualBookName.contains(expectedBookName));
    }

    @When("I click on left book corner")
    public void clickOnLeftBookCorner() {
        epubReaderScreen.clickLeftCorner();
    }

    @When("I click on right book corner")
    public void clickOnRightBookCorner() {
        epubReaderScreen.clickRightCorner();
    }

    @When("I save pageNumber as {string} and chapterName as {string} on EpubReaderScreen")
    public void savePageNumberAndChapterNameForEpubReaderScreen(String pageNumberKey, String chapterNameKey) {
        context.add(pageNumberKey, epubReaderScreen.getPageNumber());
        context.add(chapterNameKey, epubReaderScreen.getChapterName());
    }

    @Then("Next page is open and old page has {string} pageNumber and {string} chapterName")
    public void checkThatNextPageIsOpen(String pageNumberInfo, String chapterNameInfo) {
        String actualPageNumberString = epubReaderScreen.getPageNumber();
        String expectedPageNumberString = context.get(pageNumberInfo);
        int actualPageNumber = Integer.parseInt(actualPageNumberString);
        int expectedPageNumber = Integer.parseInt(expectedPageNumberString) + 1;
        AqualityServices.getLogger().info("actualPageNumberOfNextPage" + actualPageNumber);
        AqualityServices.getLogger().info("expectedPageNumberOfNextPage" + expectedPageNumber);
        String actualChapterName = epubReaderScreen.getChapterName();
        String expectedChapterName = context.get(chapterNameInfo);
        AqualityServices.getLogger().info("actualChapterNameOfNextPage" + actualChapterName);
        AqualityServices.getLogger().info("expectedChapterNameOfNextPage" + expectedChapterName);
        Assert.assertTrue(String.format("Page number or chapter name is not correct (actualPageNumber - %d, expectedPageNumber - %d), (actualChapterName-%s, expectedChapterName-%s)", actualPageNumber, expectedPageNumber, actualChapterName, expectedChapterName), expectedPageNumber == actualPageNumber ||
                (actualPageNumber == 1 && !actualChapterName.toLowerCase().equals(expectedChapterName.toLowerCase())));
    }

    @Then("Previous page is open and old page has {string} pageNumber and {string} chapterName")
    public void checkThatPreviousPageIsOpen(String pageNumberInfo, String chapterNameInfo) {
        String actualPageNumberString = epubReaderScreen.getPageNumber();
        String expectedPageNumberString = context.get(pageNumberInfo);
        int actualPageNumber = Integer.parseInt(actualPageNumberString);
        int expectedPageNumber = Integer.parseInt(expectedPageNumberString) - 1;
        AqualityServices.getLogger().info("actualPageNumberOfPreviousPage" + actualPageNumber);
        AqualityServices.getLogger().info("expectedPageNumberOfPreviousPage" + expectedPageNumber);
        String actualChapterName = epubReaderScreen.getChapterName();
        String expectedChapterName = context.get(chapterNameInfo);
        AqualityServices.getLogger().info("actualChapterNameOfPreviousPage" + actualChapterName);
        AqualityServices.getLogger().info("expectedChapterNameOfPreviousPage" + expectedChapterName);
        Assert.assertTrue(String.format("Page number or chapterName is not correct (actualPageNumber - %d, expectedPageNumber - %d), (actualChapterName-%s, expectedChapterName-%s)", actualPageNumber, expectedPageNumber, actualChapterName, expectedChapterName), expectedPageNumber == actualPageNumber ||
                (actualPageNumber == 1 && !actualChapterName.toLowerCase().equals(expectedChapterName.toLowerCase())));
    }

    @And("Each chapter can be opened from Table of Contents")
    public void checkEachChapterCanBeOpenedFromTableOfContents() {
        SoftAssertions softAssertions = new SoftAssertions();
        List<String> chapters = epubReaderScreen.getListOfChapters();
        for (String chapter :
                chapters) {
            epubReaderScreen.openChapter(chapter);
            softAssertions.assertThat(chapter.toLowerCase().equals(epubReaderScreen.getChapterName().toLowerCase())).as("ChapterName is not correct. ExpectedChapterName-" + chapter.toLowerCase() + " , ActualChapterName-" + epubReaderScreen.getChapterName().toLowerCase()).isTrue();
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
        double newFontSize = epubReaderScreen.getFontSize();
        double oldFontSize = context.get(fontSizeKey);
        Assert.assertTrue("Font size is not increased newFontSize - " + newFontSize + ", oldFontSize - " + oldFontSize, newFontSize > oldFontSize);
    }

    @Then("Font size {string} is decreased")
    public void checkFontSizeIsDecreased(String fontSizeKey) {
        double newFontSize = epubReaderScreen.getFontSize();
        double oldFontSize = context.get(fontSizeKey);
        Assert.assertTrue("Font size is not decreased newFontSize - " + newFontSize + ", oldFontSize - " + oldFontSize, newFontSize < oldFontSize);
    }

    @When("I {} of text")
    @When("I change font style to {}")
    @When("I change contrast to {}")
    public void changeSettingsForFont(ReaderSettingKeys readerSettingKey) {
        epubReaderScreen.openFontSettings();
        fontChoicesScreen.setSetting(readerSettingKey);
        fontChoicesScreen.closeFontChoices();
    }

    @And("PageNumber {string} is correct")
    public void checkPageInfoPageInfoIsCorrect(String pageNumberKey) {
        String expectedPageNumber = context.get(pageNumberKey);
        String actualPageNumber = epubReaderScreen.getPageNumber();
        Assert.assertTrue(String.format("PageNumber is not correct. ExpectedPageNumber-%1$s but actualPageNumber-%2$s", expectedPageNumber, actualPageNumber), AqualityServices.getConditionalWait().waitFor(() -> expectedPageNumber.equals(actualPageNumber)));
    }

    @When("I scroll page forward from {int} to {int} times")
    public void scrollPageForward(int minValue, int maxValue) {
        int randomScrollsCount = RandomUtils.nextInt(minValue, maxValue);
        AqualityServices.getLogger().info("Scrolling " + randomScrollsCount + " times");
        IntStream.range(0, randomScrollsCount).forEachOrdered(i -> {
            epubReaderScreen.clickRightCorner();
        });
    }

    @Then("The {} background is correct")
    public void checkBackgroundHasSpecificColor(BackgroundColorKeys fontBackgroundKey) {
        String expectedBackgroundColor = fontBackgroundKey.i18n();
        String actualBackgroundColor = epubReaderScreen.getBackgroundColor();
        Assert.assertTrue("BackgroundColor is not correct, actualBackgroundColor-" + actualBackgroundColor + ", expectedBackgroundColor-" + expectedBackgroundColor, actualBackgroundColor.toLowerCase().equals(expectedBackgroundColor.toLowerCase()));

    }

    @Then("Book text displays in {} font")
    public void bookTextDisplaysInSerifFont(FontNameKeys fontNameKey) {
        String expectedFontName = fontNameKey.i18n();
        String actualFontName = epubReaderScreen.getFontName();
        Assert.assertTrue("Book fontName is not correct, actualFontName-" + actualFontName + ", expectedFontName-" + expectedFontName, actualFontName.toLowerCase().equals(expectedFontName.toLowerCase()));
    }
}
