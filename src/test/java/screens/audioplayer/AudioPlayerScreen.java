package screens.audioplayer;

import aquality.appium.mobile.screens.Screen;
import constants.localization.application.catalog.TimerKeys;
import org.openqa.selenium.By;

import java.time.Duration;

public abstract class AudioPlayerScreen extends Screen {
    protected AudioPlayerScreen(By locator) {
        super(locator, "Audio player");
    }

    public abstract void checkThatChaptersVisible();

    public abstract void waitAndCheckAllChaptersLoaded();

    public abstract void openMenu();

    public abstract void goBack();

    public abstract Integer getPercentageValue();

    public abstract String selectChapterAndGetText(int chapterNumber);

    public abstract String getCurrentChapterInfo();

    public abstract int getCountOfChapters();

    public abstract void playBook();

    public abstract void pauseBook();

    public abstract boolean isPauseButtonPresent();

    public abstract boolean isPlayButtonPresent();

    public abstract Duration getCurrentPlayTime();

    public abstract String getLoadingStatus();

    public abstract void skipAhead();

    public abstract void skipBehind();

    public abstract void moveChapterToMiddle();

    public abstract Duration getChapterLength();

    public abstract void waitForBookLoading();

    public abstract void waitForLoadingDisappearing();

    public abstract void selectPlaybackSpeed(String playbackSpeed);

    public abstract boolean isSpeedOptionSelected(String playbackSpeed);

    public abstract void setTimer(TimerKeys timerSetting);

    public abstract boolean isTimerEqualTo(Duration chapterLength);

    public abstract boolean isTimerSetTo(TimerKeys timerSetting);

    public abstract boolean isAudiobookNamePresent(String audiobookName);
}
