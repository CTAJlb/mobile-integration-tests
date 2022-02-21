package screens.audiobook;

import aquality.appium.mobile.screens.Screen;
import constants.localization.application.catalog.TimerKeys;
import org.openqa.selenium.By;

import java.time.Duration;

public abstract class AudioPlayerScreen2 extends Screen {
    protected AudioPlayerScreen2(By locator) {
        super(locator, "Audio player");
    }

    public abstract String selectChapterAndGetText(int chapterNumber);

    public abstract int getCountOfChapters();

    public abstract void selectPlaybackSpeed(String playbackSpeed);

    public abstract void setTimer(TimerKeys timerSetting);
}
