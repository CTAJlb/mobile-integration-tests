package screens.audiobook.audioPlayer.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import constants.localization.application.catalog.TimerKeys;
import framework.utilities.DateUtils;
import org.openqa.selenium.By;
import screens.audiobook.audioPlayer.AudioPlayerScreen;
import screens.audiobook.playbackSpeedAudiobook.PlaybackSpeedAudiobookScreen;
import screens.audiobook.sleepTimerAudiobook.SleepTimerAudiobookScreen;

import java.time.Duration;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidAudioPlayerScreen extends AudioPlayerScreen {
    private static final String PLAYBACK_SPEED_LOC = "//*[contains(@resource-id, \"player_menu_playback_rate_text\") and @text=\"%sx\"]";
    private static final String AUDIOBOOK_NAME_LOC = "//android.widget.TextView[@text=\"%s\"]";
    private static final String SLEEP_TIMER_LOC = "//*[contains(@resource-id, \"player_menu_sleep\") and @content-desc=\"Set Your Sleep Timer. The Sleep Timer Is Currently Set To Sleep At %s\"]";

    private final IButton btnPlaybackSpeed =
            getElementFactory().getButton(By.id("player_menu_playback_rate_image"), "btnPlaybackSpeed");
    private final IButton btnToc =
            getElementFactory().getButton(By.id("player_menu_toc"), "btnToc");
    private final IButton btnPause =
            getElementFactory().getButton(By.xpath("//android.widget.ImageView[@content-desc=\"Pause\"]"), "btnPause");
    private final IButton btnPlay =
            getElementFactory().getButton(By.xpath("//android.widget.ImageView[@content-desc=\"Play\"]"), "btnPlay");
    private final IButton btnSkipAhead =
            getElementFactory().getButton(By.id("player_jump_forwards"), "btnSkipAhead");
    private final IButton btnSkipBehind =
            getElementFactory().getButton(By.id("player_jump_backwards"), "btnSkipBehind");
    private final ILabel lblChapterName =
            getElementFactory().getLabel(By.id("player_spine_element"), "lblChapterName");
    private final ILabel lblLeftTime =
            getElementFactory().getLabel(By.id("player_time"), "lblLeftTime");
    private final ILabel lblRightTime =
            getElementFactory().getLabel(By.id("player_time_maximum"), "lblRightTime");
    private final IButton btnSleepTimer =
            getElementFactory().getButton(By.id("player_menu_sleep_image"), "btnSleepTimer");

    public AndroidAudioPlayerScreen() {
        super(By.xpath("//android.widget.ImageView[@content-desc=\"Play\"]"));
        sleepTimerAudiobookScreen = AqualityServices.getScreenFactory().getScreen(SleepTimerAudiobookScreen.class);
        playbackSpeedAudiobookScreen = AqualityServices.getScreenFactory().getScreen(PlaybackSpeedAudiobookScreen.class);
    }

    @Override
    public void openToc() {
        btnToc.click();
    }

    @Override
    public void openSleepTimer() {
        btnSleepTimer.click();
    }

    @Override
    public void openPlaybackSpeed() {
        btnPlaybackSpeed.click();
    }

    @Override
    public SleepTimerAudiobookScreen getSleepTimerAudiobookScreen() {
        return sleepTimerAudiobookScreen;
    }

    @Override
    public PlaybackSpeedAudiobookScreen getPlaybackSpeedAudiobookScreen() {
        return playbackSpeedAudiobookScreen;
    }

    @Override
    public boolean isPlayButtonPresent() {
        return btnPlay.state().waitForDisplayed();
    }

    @Override
    public boolean isAudiobookNamePresent(String audiobookName) {
        boolean isAudiobookNameCorrect = getElementFactory().getLabel(By.xpath(String.format(AUDIOBOOK_NAME_LOC, audiobookName)), "audiobookName").state().waitForDisplayed();
        return isAudiobookNameCorrect;
    }

    @Override
    public boolean isPlaybackSpeedPresent(String playbackSpeed) {
        return getElementFactory().getButton(By.xpath(String.format(PLAYBACK_SPEED_LOC, playbackSpeed)), "Playback speed").state().waitForDisplayed();
    }
    @Override
    public Duration getRightTime() {
        return DateUtils.getDuration(lblRightTime.getText());
    }

    @Override
    public boolean isTimerEqualTo(Duration chapterLength) {
        return false;
    }

    @Override
    public boolean isTimerSetTo(TimerKeys timerSetting) {
        String timerSettingName = timerSetting.i18n();
        timerSettingName = timerSettingName.replace("file", "File");
        return getElementFactory().getButton(By.xpath(String.format(SLEEP_TIMER_LOC, timerSettingName)), timerSettingName, ElementState.EXISTS_IN_ANY_STATE).state().waitForDisplayed();
    }
    @Override
    public boolean isPauseButtonPresent() {
        AqualityServices.getApplication().getDriver().getPageSource();
        return btnPause.state().waitForExist();
    }

    @Override
    public Duration getLeftTime() {
        return DateUtils.getDuration(lblLeftTime.getText());
    }

    @Override
    public String getChapterName() {
        return lblChapterName.getText();
    }
    @Override
    public void skipBehind() {
        btnSkipBehind.click();
    }

    @Override
    public void skipAhead() {
        btnSkipAhead.click();
    }

    @Override
    public void returnToPreviousScreen() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }

    @Override
    public void tapPlayBtn() {
        btnPlay.click();
    }

    @Override
    public void tapPauseBtn() {
        btnPause.click();
    }
}
