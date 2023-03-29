package stepdefinitions.audiobookSteps;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.localization.spanish.SpanishIos;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import screens.audiobook.tocAudiobook.TocAudiobookScreen;

public class TocAudiobookSteps {
    private final ScenarioContext context;
    private final TocAudiobookScreen tocAudiobookScreen;

    @Inject
    public TocAudiobookSteps(ScenarioContext context) {
        tocAudiobookScreen = AqualityServices.getScreenFactory().getScreen(TocAudiobookScreen.class);
        this.context = context;
    }

    @Then("The first chapter is loaded")
    public void isChapterLoaded(){
        Assert.assertTrue("The first chapter is not loaded", tocAudiobookScreen.isTheFirstChapterLoaded());
    }

    @Then("Elements on TOC audiobook screen are translated correctly")
    public void checkTranslationOnTOCAudiobookScreen(){
        Assert.assertEquals("Back button is not translated", tocAudiobookScreen.getTextFromBackBtn(), SpanishIos.BACK);
    }

    @When("Close toc audiobook screen")
    public void closeTocAudiobookScreen(){
        tocAudiobookScreen.clickBackBtn();
    }

    @When("Open random chapter on toc audiobook screen and save chapter name as {string}")
    public void openRandomChapterOnTocAudiobookScreenAndSaveChapterName(String keyChapterName) {
        int countOfChapters = tocAudiobookScreen.getCountOfChapters();
        String chapterName = tocAudiobookScreen.openChapterAndGetChapterName(RandomUtils.nextInt(1, countOfChapters));
        context.add(keyChapterName, chapterName);
    }

    @When("Open the {int} chapter on toc audiobook screen and save the chapter name as {string}")
    public void openSpecificChapterOnTocAudiobookScreenAndSaveChapterName(int chapterNumber, String keyChapterName) {
        String chapter = tocAudiobookScreen.openChapterAndGetChapterName(chapterNumber - 1);
        context.add(keyChapterName, chapter);
    }

    @When("Open the {int} chapter on toc audiobook screen and save the chapter name as {string} and chapter number as {string}")
    public void openChapterAndSaveNameAndNumber(int chapterNumber, String chapterNameKey, String chapterNumberKey) {
        String chapter = tocAudiobookScreen.openChapterAndGetChapterName(chapterNumber - 1);
        context.add(chapterNameKey, chapter);
        context.add(chapterNumberKey, chapterNumber);
    }

    @Then("Chapter name next to {string} on toc audiobook screen is equal to {string} saved chapter name")
    public void checkChapterNameOnToc(String chapterNumberKey, String chapterNameKey) {
        int chapterNumber = context.get(chapterNumberKey);
        String chapterNameFromToc = tocAudiobookScreen.getChapterName(chapterNumber);
        String chapterNameFromScreen = context.get(chapterNameKey);
        String cutChapterName = chapterNameFromScreen.substring(0, chapterNameFromScreen.indexOf('(') - 1);
        Assert.assertEquals("Chapter does not change to next. ", cutChapterName, chapterNameFromToc);
    }
}
