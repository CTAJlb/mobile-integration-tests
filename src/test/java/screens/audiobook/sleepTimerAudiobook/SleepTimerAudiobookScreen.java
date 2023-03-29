package screens.audiobook.sleepTimerAudiobook;

import aquality.appium.mobile.screens.Screen;
import enums.localization.catalog.TimerKeys;
import org.openqa.selenium.By;

public abstract class SleepTimerAudiobookScreen extends Screen {
    protected SleepTimerAudiobookScreen(By locator) {
        super(locator, "SleepTimerAudiobookScreen");
    }

    public abstract void setTimer(TimerKeys timerSetting);

    public abstract void closeSleepTimer();

    public abstract String getTextFromSleepTimerLabel();
    public abstract String getTextFromSleepTimerLabelES();

    public abstract String getTextFromOffBtn();
    public abstract String getTextFromOffBtnES();

    public abstract String getTextFromEndOfChapterBtn();
    public abstract String getTextFromEndOfChapterBtnES();

    public abstract String getTextFromCancelBtn();
    public abstract String getTextFromCancelBtnES();
}
