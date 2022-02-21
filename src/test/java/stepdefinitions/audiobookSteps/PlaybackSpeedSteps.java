package stepdefinitions.audiobookSteps;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import screens.audiobook.AudioPlayerScreen2;

public class PlaybackSpeedSteps {
    private final ScenarioContext context;
    private final AudioPlayerScreen2 audioPlayerScreen2;

    @Inject
    public PlaybackSpeedSteps(ScenarioContext context) {
        audioPlayerScreen2 = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen2.class);
        this.context = context;
    }

    @And("Select {double}X playback speed on playback speed audiobook screen")
    public void selectPlaybackSpeedOnPlaybackSpeedAudiobookScreen(Double playbackSpeedDouble) {
        String playbackSpeed = String.valueOf(playbackSpeedDouble);
        audioPlayerScreen2.selectPlaybackSpeed(playbackSpeed);
    }
}
