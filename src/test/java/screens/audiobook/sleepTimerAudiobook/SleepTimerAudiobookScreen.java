package screens.audiobook.sleepTimerAudiobook;

import aquality.appium.mobile.screens.Screen;
import constants.localization.application.catalog.TimerKeys;
import org.openqa.selenium.By;

public abstract class SleepTimerAudiobookScreen extends Screen {
    protected SleepTimerAudiobookScreen(By locator) {
        super(locator, "SleepTimerAudiobookScreen");
    }
    public abstract void setTimer(TimerKeys timerSetting);
}
