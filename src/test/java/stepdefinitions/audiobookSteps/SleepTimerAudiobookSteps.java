package stepdefinitions.audiobookSteps;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.localization.application.catalog.TimerKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.When;

public class SleepTimerAudiobookSteps {
    private final ScenarioContext context;
    private final AudioPlayerScreen2 audioPlayerScreen2;

    @Inject
    public SleepTimerAudiobookSteps(ScenarioContext context) {
        audioPlayerScreen2 = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen2.class);
        this.context = context;
    }

    @When("Set {} sleep timer on sleep timer audiobook screen")
    public void setSleepTimerOnSleepTimerAudiobookScreen(TimerKeys timerSetting) {
        audioPlayerScreen2.setTimer(timerSetting);
    }
}
