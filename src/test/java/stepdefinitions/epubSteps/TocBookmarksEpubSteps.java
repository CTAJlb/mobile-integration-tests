package stepdefinitions.epubSteps;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.localization.italian.ItalianIos;
import constants.localization.spanish.SpanishIos;
import enums.epub.EnumTabsTocAndBookmarksEpub;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import screens.epub.bookmarksEpub.BookmarksEpubScreen;
import screens.epub.readerEpub.ReaderEpubScreen;
import screens.epub.tocBookmarksEpub.TocBookmarksEpubScreen;

public class TocBookmarksEpubSteps {
    private final ScenarioContext context;
    private final ReaderEpubScreen readerEpubScreen;
    private final TocBookmarksEpubScreen tocBookmarksEpubScreen;

    @Inject
    public TocBookmarksEpubSteps(ScenarioContext context) {
        this.context = context;
        readerEpubScreen = AqualityServices.getScreenFactory().getScreen(ReaderEpubScreen.class);
        tocBookmarksEpubScreen = AqualityServices.getScreenFactory().getScreen(TocBookmarksEpubScreen.class);
    }

    @When("Open random bookmark and save chapter name as {string} on bookmarks epub screen")
    public void openRandomBookmarkAndSaveBookmarkChapterName(String chapterNameKey){
        BookmarksEpubScreen bookmarksEpubScreen = tocBookmarksEpubScreen.getBookmarksEpubScreen();
        int amountOfBookmarks = bookmarksEpubScreen.getListOfBookmarkTitles().size();
        int randomBookmarkNumber = RandomUtils.nextInt(0, amountOfBookmarks);
        context.add(chapterNameKey, bookmarksEpubScreen.getListOfBookmarkTitles().get(randomBookmarkNumber));
        bookmarksEpubScreen.openBookmark(randomBookmarkNumber);
    }

    @Then("Elements on TOC in reader epub are translated correctly")
    public void checkTranslationOfTOCScreen(){
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(tocBookmarksEpubScreen.getTextFromBackBtn()).as("Back button is not translated").isEqualTo(SpanishIos.BACK);
        softAssertions.assertThat(tocBookmarksEpubScreen.getTextFromTOCLabel()).as("TOC header is not translated").isEqualTo(SpanishIos.TOC);
        softAssertions.assertThat(tocBookmarksEpubScreen.getTextFromContentsBtn()).as("Contents button is not translated").isEqualTo(SpanishIos.CONTENTS);
        softAssertions.assertThat(tocBookmarksEpubScreen.getTextFromBookmarksBtn()).as("Bookmarks button is not translated").isEqualTo(SpanishIos.BOOKMARKS);
        softAssertions.assertAll();
    }

    @Then("Elements on TOC in reader epub are translated correctly in Italian")
    public void checkTranslationOfTOCScreenIT(){
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(tocBookmarksEpubScreen.getTextFromBackBtn()).as("Back button is not translated").isEqualTo(ItalianIos.BACK);
        softAssertions.assertThat(tocBookmarksEpubScreen.getTextFromTOCLabel()).as("TOC header is not translated").isEqualTo(ItalianIos.TOC);
        softAssertions.assertThat(tocBookmarksEpubScreen.getTextFromContentsBtn()).as("Contents button is not translated").isEqualTo(ItalianIos.CONTENTS);
        softAssertions.assertThat(tocBookmarksEpubScreen.getTextFromBookmarksBtn()).as("Bookmarks button is not translated").isEqualTo(ItalianIos.BOOKMARKS);
        softAssertions.assertAll();
    }

    @Then("Elements on Bookmarks epub screen are translated correctly")
    public void checkTranslationOfBookmarksScreen() {
        Assert.assertEquals("Info label is not translated", SpanishIos.BOOKMARKS_INFO, tocBookmarksEpubScreen.getBookmarksEpubScreen().getTextFromInformationLbl());
    }

    @Then("Elements on Bookmarks epub screen are translated correctly in Italian")
    public void checkTranslationOfBookmarksScreenIT() {
        Assert.assertEquals("Info label is not translated", ItalianIos.BOOKMARKS_INFO, tocBookmarksEpubScreen.getBookmarksEpubScreen().getTextFromInformationLbl());
    }

    @And("{string} chapter name is displayed on reader epub screen")
    public void checkPageInfoPageInfoIsCorrect(String chapterNameKey) {
        String expectedChapterName = context.get(chapterNameKey);
        String actualChapterName = readerEpubScreen.getChapterName();
        Assert.assertEquals(String.format("'%s Chapter name is not displayed. Actual chapter name-%s", expectedChapterName, actualChapterName), expectedChapterName.toLowerCase(), actualChapterName.toLowerCase());
    }


    @When("Save device time and date as {string}")
    public void saveDeviceTimeDate(String deviceTimeDateKey){
        String deviceTimeDate = AqualityServices.getApplication().getDriver().getDeviceTime();
        context.add(deviceTimeDateKey, deviceTimeDate);
    }

    @When("Open bookmarks epub screen")
    public void openBookmarksEpubScreen(){
        readerEpubScreen.openNavigationBar();
        readerEpubScreen.getNavigationBarEpubScreen().tapTOCBookmarksButton();
        tocBookmarksEpubScreen.openTab(EnumTabsTocAndBookmarksEpub.BOOKMARKS);
    }

    @When("Open bookmarks epub screen in Spanish")
    public void openBookmarksEpubScreenES(){
        readerEpubScreen.openNavigationBar();
        readerEpubScreen.getNavigationBarEpubScreen().tapTOCBookmarksButton();
        tocBookmarksEpubScreen.openTab(EnumTabsTocAndBookmarksEpub.BOOKMARKS_ES);
    }

    @When("Open bookmarks epub screen in Italian")
    public void openBookmarksEpubScreenIT(){
        readerEpubScreen.openNavigationBar();
        readerEpubScreen.getNavigationBarEpubScreen().tapTOCBookmarksButton();
        tocBookmarksEpubScreen.openTab(EnumTabsTocAndBookmarksEpub.BOOKMARKS_IT);
    }

    @When("Delete bookmark on bookmarks epub screen")
    public void deleteBookmarkOnBookmarksEpubScreen(){
        tocBookmarksEpubScreen.getBookmarksEpubScreen().deleteBookmark(0);
    }

    @When("Return to reader epub screen from toc bookmarks epub screen")
    public void returnToReaderEpubScreen(){
        tocBookmarksEpubScreen.returnToPreviousScreen();
    }

    @When("Add bookmark on reader epub screen")
    public void addBookmark(){
        readerEpubScreen.openNavigationBar();
        readerEpubScreen.getNavigationBarEpubScreen().tapAddBookmarkButton();
    }

    @When("Delete bookmark on reader epub screen")
    public void deleteBookmarkOnReaderEpubScreen(){
        readerEpubScreen.openNavigationBar();
        readerEpubScreen.getNavigationBarEpubScreen().tapDeleteBookmarkButton();
    }

    @Then("Bookmark is displayed on reader epub screen")
    public void checkBookmarkIsDisplayedOnReaderEpubScreen(){
        readerEpubScreen.openNavigationBar();
        Assert.assertTrue("Bookmark is not displayed on reader epub screen", readerEpubScreen.getNavigationBarEpubScreen().isBookmarkDisplayed());
    }

    @Then("Bookmark is not displayed on reader epub screen")
    public void checkBookmarkIsNotDisplayedOnReaderEpubScreen(){
        readerEpubScreen.openNavigationBar();
        Assert.assertFalse("Bookmark is displayed on reader epub screen", readerEpubScreen.getNavigationBarEpubScreen().isBookmarkDisplayed());
    }

    @Then("Bookmark with {string} and {string} is displayed on bookmarks epub screen")
    public void checkBookmarkIsDisplayedOnBookmarksEpubScreen(String chapterNameKey, String deviceTimeDateKey){
        String bookmarkTitle = context.get(chapterNameKey);
        String deviceTimeDate = context.get(deviceTimeDateKey);
        Assert.assertTrue(String.format("Bookmark with '%s' bookmarkTitle and '%s' deviceTimeDate is not displayed on bookmarks epub screen", bookmarkTitle, deviceTimeDate),
                tocBookmarksEpubScreen.getBookmarksEpubScreen().isBookmarkPresent(bookmarkTitle, deviceTimeDate));
    }

    @Then("Bookmark with {string} and {string} is not displayed on bookmarks epub screen")
    public void checkBookmarkIsNotDisplayedOnBookmarksEpubScreen(String chapterNameKey, String deviceTimeDateKey){
        String bookmarkTitle = context.get(chapterNameKey);
        String deviceTimeDate = context.get(deviceTimeDateKey);
        Assert.assertFalse(String.format("Bookmark with '%s' bookmarkTitle and '%s' deviceTimeDate is displayed on bookmarks epub screen", bookmarkTitle, deviceTimeDate),
                tocBookmarksEpubScreen.getBookmarksEpubScreen().isBookmarkPresent(bookmarkTitle, deviceTimeDate));
    }
}
