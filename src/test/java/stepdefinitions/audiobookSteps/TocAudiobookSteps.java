package stepdefinitions.audiobookSteps;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomUtils;
import screens.audiobook.AudioPlayerScreen2;

public class TocAudiobookSteps {
    private final ScenarioContext context;
    private final AudioPlayerScreen2 audioPlayerScreen2;

    @Inject
    public TocAudiobookSteps(ScenarioContext context) {
        audioPlayerScreen2 = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen2.class);
        this.context = context;
    }

    @When("Open random chapter on toc audiobook screen and save chapter name as {string}")
    public void openRandomChapterOnTocAudiobookScreenAndSaveChapterName(String keyChapterName) {
        String chapterName = audioPlayerScreen2.selectChapterAndGetText(RandomUtils.nextInt(1, audioPlayerScreen2.getCountOfChapters()));
        context.add(keyChapterName, chapterName);
    }

    @When("Open the {int} chapter on toc audiobook screen and save the chapter name as {string}")
    public void openSpecificChapterOnTocAudiobookScreenAndSaveChapterName(int chapterNumber, String keyChapterName) {
        String chapter = audioPlayerScreen2.selectChapterAndGetText(chapterNumber - 1);
        context.add(keyChapterName, chapter);
    }
}
