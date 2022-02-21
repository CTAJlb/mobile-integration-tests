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

    public abstract SleepTimerAudiobookScreen getSleepTimerAudiobookScreen();

    public abstract PlaybackSpeedAudiobookScreen getPlaybackSpeedAudiobookScreen();

    public abstract void goBack();

    public abstract void playBook();

    public abstract String getCurrentChapterInfo();


    public abstract void skipAhead();

    public abstract void skipBehind();

    public abstract void pauseBook();

    public abstract boolean isPauseButtonPresent();

    public abstract boolean isPlayButtonPresent();

    public abstract void moveChapterToMiddle();

    public abstract Duration getChapterLength();

    public abstract boolean isSpeedOptionSelected(String playbackSpeed);

    public abstract boolean isAudiobookNamePresent(String audiobookName);

    public abstract boolean isTimerEqualTo(Duration chapterLength);

    public abstract boolean isTimerSetTo(TimerKeys timerSetting);


    public abstract Duration getCurrentPlayTime();
}
