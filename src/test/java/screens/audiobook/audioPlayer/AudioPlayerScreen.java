package screens.audiobook.audioPlayer;

import aquality.appium.mobile.screens.Screen;
import constants.localization.application.catalog.TimerKeys;
import org.openqa.selenium.By;
import screens.audiobook.playbackSpeedAudiobook.PlaybackSpeedAudiobookScreen;
import screens.audiobook.sleepTimerAudiobook.SleepTimerAudiobookScreen;

import java.time.Duration;

public abstract class AudioPlayerScreen extends Screen {
    protected SleepTimerAudiobookScreen sleepTimerAudiobookScreen;
    protected PlaybackSpeedAudiobookScreen playbackSpeedAudiobookScreen;

    protected AudioPlayerScreen(By locator) {
        super(locator, "AudioPlayerScreen");
    }

    public abstract void openToc();

    public abstract void openSleepTimer();

    public abstract void openPlaybackSpeed();

    public abstract SleepTimerAudiobookScreen getSleepTimerAudiobookScreen();

    public abstract PlaybackSpeedAudiobookScreen getPlaybackSpeedAudiobookScreen();

    public abstract void returnToPreviousScreen();

    public abstract void tapPlayBtn();

    public abstract String getChapterName();

    public abstract void skipAhead();

    public abstract void skipBehind();

    public abstract void tapPauseBtn();

    public abstract boolean isPauseButtonPresent();

    public abstract boolean isPlayButtonPresent();

    public abstract Duration getRightTime();

    public abstract boolean isPlaybackSpeedPresent(String playbackSpeed);

    public abstract boolean isAudiobookNamePresent(String audiobookName);

    public abstract boolean isTimerEqualTo(Duration chapterLength);

    public abstract boolean isTimerSetTo(TimerKeys timerSetting);

    public abstract Duration getLeftTime();
}
