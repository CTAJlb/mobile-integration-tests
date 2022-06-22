package stepdefinitions.audiobookSteps;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.localization.application.catalog.TimerKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.When;
import screens.audiobook.audioPlayer.AudioPlayerScreen;

public class SleepTimerAudiobookSteps {
    private final ScenarioContext context;
    private final AudioPlayerScreen audioPlayerScreen;

    @Inject
    public SleepTimerAudiobookSteps(ScenarioContext context) {
        audioPlayerScreen = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen.class);
        this.context = context;
    }

    @When("Set {} sleep timer on sleep timer audiobook screen")
    public void setSleepTimerOnSleepTimerAudiobookScreen(TimerKeys timerSetting) {
        audioPlayerScreen.openSleepTimer();
        audioPlayerScreen.getSleepTimerAudiobookScreen().setTimer(timerSetting);
    }

    @When("Close sleep timer screen")
    public void closeSleepTimer() {
        audioPlayerScreen.getSleepTimerAudiobookScreen().closeSleepTimer();
    }
}
