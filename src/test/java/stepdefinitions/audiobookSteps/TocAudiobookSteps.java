package stepdefinitions.audiobookSteps;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomUtils;
import screens.audiobook.tocAudiobook.TocAudiobookScreen;

public class TocAudiobookSteps {
    private final ScenarioContext context;
    private final TocAudiobookScreen tocAudiobookScreen;

    @Inject
    public TocAudiobookSteps(ScenarioContext context) {
        tocAudiobookScreen = AqualityServices.getScreenFactory().getScreen(TocAudiobookScreen.class);
        this.context = context;
    }

    @When("Open random chapter on toc audiobook screen and save chapter name as {string}")
    public void openRandomChapterOnTocAudiobookScreenAndSaveChapterName(String keyChapterName) {
        String chapterName = tocAudiobookScreen.openChapterAndGetChapterName(RandomUtils.nextInt(1, tocAudiobookScreen.getCountOfChapters()));
        context.add(keyChapterName, chapterName);
    }

    @When("Open the {int} chapter on toc audiobook screen and save the chapter name as {string}")
    public void openSpecificChapterOnTocAudiobookScreenAndSaveChapterName(int chapterNumber, String keyChapterName) {
        String chapter = tocAudiobookScreen.openChapterAndGetChapterName(chapterNumber - 1);
        context.add(keyChapterName, chapter);
    }
}
