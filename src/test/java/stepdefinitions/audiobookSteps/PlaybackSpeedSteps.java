package stepdefinitions.audiobookSteps;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import screens.audiobook.audioPlayer.AudioPlayerScreen;

public class PlaybackSpeedSteps {
    private final ScenarioContext context;
    private final AudioPlayerScreen audioPlayerScreen;

    @Inject
    public PlaybackSpeedSteps(ScenarioContext context) {
        audioPlayerScreen = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen.class);
        this.context = context;
    }

    @And("Select {double}X playback speed on playback speed audiobook screen")
    public void selectPlaybackSpeedOnPlaybackSpeedAudiobookScreen(Double playbackSpeedDouble) {
        String playbackSpeed = String.valueOf(playbackSpeedDouble);
        audioPlayerScreen.openPlaybackSpeed();
        audioPlayerScreen.getPlaybackSpeedAudiobookScreen().selectPlaybackSpeed(playbackSpeed);
    }

    @When("Close playback speed screen")
    public void cancelPlaybackSpeed() {
        audioPlayerScreen.getPlaybackSpeedAudiobookScreen().closePlaybackScreen();
    }
}
