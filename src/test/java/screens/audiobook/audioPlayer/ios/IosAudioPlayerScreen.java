package screens.audiobook.audioPlayer.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import constants.application.attributes.IosAttributes;
import constants.localization.application.catalog.TimerKeys;
import framework.utilities.DateUtils;
import org.openqa.selenium.By;
import screens.audiobook.audioPlayer.AudioPlayerScreen;
import screens.audiobook.playbackSpeedAudiobook.PlaybackSpeedAudiobookScreen;
import screens.audiobook.sleepTimerAudiobook.SleepTimerAudiobookScreen;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@ScreenType(platform = PlatformName.IOS)
public class IosAudioPlayerScreen extends AudioPlayerScreen {
    private static final String PLAYBACK_OPTION_XPATH_LOCATOR = "//XCUIElementTypeToolbar//XCUIElementTypeButton[@name=\"%s\"]";
    private static final String AUDIOBOOK_NAME_LOCATOR = "//XCUIElementTypeStaticText[@name=\"%s\"]";
    private static final String TIME_IN_HOURS_LEFT_XPATH_LOCATOR = "//XCUIElementTypeToolbar//XCUIElementTypeButton[@name=\"%d hour and %d minutes until playback pauses\"]";
    private static final String TIME_IN_MINUTES_LEFT_XPATH_LOCATOR = "//XCUIElementTypeToolbar//XCUIElementTypeButton[@name=\"%d minutes and %d seconds until playback pauses\"]";
    private static final String TIME_IN_SECONDS_LEFT_XPATH_LOCATOR = "//XCUIElementTypeToolbar//XCUIElementTypeButton[@name=\"%d seconds until playback pauses\"]";

    private final IButton btnMenu =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Table of Contents\"]"), "Menu");
    private final IButton btnPlay =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@label=\"Play\"]"), "Play");
    private final IButton btnPause =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@label=\"Pause\"]"), "Pause");
    private final IButton btnGoBack =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar//XCUIElementTypeButton[1]"), "Go Back");
    private final IButton btnBehind = getElementFactory().getButton(By.name("skip_back"), "Behind");
    private final IButton btnAhead = getElementFactory().getButton(By.name("skip_forward"), "Ahead");
    private final ILabel lblCurrentChapter =
            getElementFactory().getLabel(By.xpath("(//XCUIElementTypeStaticText[@name=\"progress_rightLabel\"])[1]"), "Current chapter");
    private final ILabel lblCurrentTime =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@name=\"progress_leftLabel\"]"), "Current time", ElementState.EXISTS_IN_ANY_STATE);
    private final IButton btnProgress = getElementFactory().getButton(By.name("progress_background"), "Progress bar");
    private final ILabel lblChapterTime =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@name=\"progress_rightLabel\" and contains(@value,\":\")]"), "Chapter time", ElementState.EXISTS_IN_ANY_STATE);


    public IosAudioPlayerScreen() {
        super(By.xpath(""));
        sleepTimerAudiobookScreen = AqualityServices.getScreenFactory().getScreen(SleepTimerAudiobookScreen.class);
        playbackSpeedAudiobookScreen = AqualityServices.getScreenFactory().getScreen(PlaybackSpeedAudiobookScreen.class);
    }

    @Override
    public void openToc() {
        btnMenu.click();
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
    public boolean isTimerSetTo(TimerKeys timerSetting) {
        return false;
    }

    @Override
    public boolean isAudiobookNamePresent(String audiobookName) {
        boolean isAudiobookNamePresent = getElementFactory().getLabel(By.xpath(String.format(AUDIOBOOK_NAME_LOCATOR, audiobookName)), "audiobookName").state().waitForDisplayed();
        return isAudiobookNamePresent;
    }


    private static Map<String, String> speedName = new HashMap<String, String>() {{
        put("2.0", "Two times normal speed. Fastest.");
        put("0.75", "Three quarters of normal speed. Slower.");
    }};

    @Override
    public boolean isSpeedOptionSelected(String playbackSpeed) {
        String speedOptionName = speedName.get(playbackSpeed);
        return getElementFactory().getButton(By.xpath(String.format(PLAYBACK_OPTION_XPATH_LOCATOR, speedOptionName)), speedOptionName).state().waitForDisplayed();
    }

    @Override
    public boolean isTimerEqualTo(Duration chapterLength) {
        int seconds = (int) chapterLength.getSeconds() % 60;
        int minutes = (int) (chapterLength.toMinutes() >= 60 ? chapterLength.toMinutes() % 60 : chapterLength.toMinutes());
        return getElementFactory().getButton(By.xpath(String.format(TIME_IN_HOURS_LEFT_XPATH_LOCATOR, (int) chapterLength.toHours(), minutes)), "Timer").state().isDisplayed() || getElementFactory().getButton(By.xpath(String.format(TIME_IN_MINUTES_LEFT_XPATH_LOCATOR, minutes, seconds)), "Timer").state().isDisplayed() || getElementFactory().getButton(By.xpath(String.format(TIME_IN_SECONDS_LEFT_XPATH_LOCATOR, seconds)), "Timer").state().isDisplayed();
    }

    @Override
    public boolean isPlayButtonPresent() {
        return btnPlay.state().isDisplayed();
    }

    @Override
    public Duration getChapterLength() {
        return DateUtils.getDuration(lblChapterTime.getAttribute(IosAttributes.VALUE));
    }

    @Override
    public boolean isPauseButtonPresent() {
        return btnPause.state().isDisplayed();
    }

    @Override
    public Duration getCurrentPlayTime() {
        return DateUtils.getDuration(lblCurrentTime.getAttribute(IosAttributes.VALUE));
    }

    @Override
    public void moveChapterToMiddle() {
        btnProgress.click();
    }

    @Override
    public String getCurrentChapterInfo() {
        return lblCurrentChapter.getAttribute(IosAttributes.VALUE);
    }

    @Override
    public void skipAhead() {
        btnAhead.click();
    }
    @Override
    public void goBack() {
        btnGoBack.click();
    }

    @Override
    public void playBook() {
        btnPlay.click();
    }

    @Override
    public void pauseBook() {
        btnPause.click();
    }

    @Override
    public void skipBehind() {
        btnBehind.click();
    }
}
