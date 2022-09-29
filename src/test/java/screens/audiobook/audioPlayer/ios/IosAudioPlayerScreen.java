package screens.audiobook.audioPlayer.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.applicationattributes.IosAttributes;
import enums.localization.catalog.TimerKeys;
import framework.utilities.DateUtils;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import screens.audiobook.audioPlayer.AudioPlayerScreen;
import screens.audiobook.playbackSpeedAudiobook.PlaybackSpeedAudiobookScreen;
import screens.audiobook.sleepTimerAudiobook.SleepTimerAudiobookScreen;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@ScreenType(platform = PlatformName.IOS)
public class IosAudioPlayerScreen extends AudioPlayerScreen {
    private static final String PLAYBACK_SPEED_LOC = "//XCUIElementTypeToolbar//XCUIElementTypeButton[contains(@name, \"%s\")]";
    private static final String AUDIOBOOK_NAME_LOC = "//XCUIElementTypeStaticText[@name=\"%s\"]";
    private static final String TIME_IN_HOURS_LEFT_XPATH_LOCATOR = "//XCUIElementTypeToolbar//XCUIElementTypeButton[@name=\"%d hour and %d minutes until playback pauses\"]";
    private static final String TIME_IN_MINUTES_LEFT_XPATH_LOCATOR = "//XCUIElementTypeToolbar//XCUIElementTypeButton[@name=\"%d minutes and %d seconds until playback pauses\"]";
    private static final String TIME_IN_SECONDS_LEFT_XPATH_LOCATOR = "//XCUIElementTypeToolbar//XCUIElementTypeButton[@name=\"%d seconds until playback pauses\"]";

    private final IButton btnSleepTimer =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeToolbar//XCUIElementTypeButton[3]"), "btnSleepTimer");
    private final IButton btnToc =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Table of Contents\"]"), "btnToc");
    private final IButton btnPlay =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@label=\"Play\"]"), "btnPlay");
    private final IButton btnPause =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@label=\"Pause\"]"), "btnPause");
    private final IButton btnBack =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[1]"), "btnBack");
    private final IButton btnSkipBehind =
            getElementFactory().getButton(By.name("skip_back"), "btnSkipBehind");
    private final IButton btnSkipAhead =
            getElementFactory().getButton(By.name("skip_forward"), "btnSkipAhead");
    private final ILabel lblChapterName =
            getElementFactory().getLabel(By.xpath("(//XCUIElementTypeStaticText[@name=\"progress_rightLabel\"])[1]"), "lblChapterName");
    private final ILabel lblLeftTime =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@name=\"progress_leftLabel\"]"), "lblLeftTime");
    private final ILabel lblRightTime =
            getElementFactory().getLabel(By.xpath("(//XCUIElementTypeStaticText[@name=\"progress_rightLabel\"])[2]"), "lblRightTime");
    private final IButton btnPlaybackSpeed =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeToolbar//XCUIElementTypeButton"), "btnPlaybackSpeed");
    private final ILabel lblPlaybackProgress =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeOther[@name = \"progress_background\"]"), "Playback progress");
    private final ILabel lblLineRemaining =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[contains(@name, \"remaining in the book\")]"), "Line remaining");
    private final IButton btnPlaySpeed =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[contains(@name, \"speed\")]"), "Button play speed");
    private final IButton btnSlider =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeOther[@name=\"progress_grip\"]"), "Slider");


    public IosAudioPlayerScreen() {
        super(By.xpath("//XCUIElementTypeButton[@label=\"Play\"]"));
        sleepTimerAudiobookScreen = AqualityServices.getScreenFactory().getScreen(SleepTimerAudiobookScreen.class);
        playbackSpeedAudiobookScreen = AqualityServices.getScreenFactory().getScreen(PlaybackSpeedAudiobookScreen.class);
    }

    @Override
    public boolean isPlayerOpened(String bookName) {
        return getElementFactory().getLabel(By.xpath(String.format(AUDIOBOOK_NAME_LOC, bookName)), "Book name").state().waitForDisplayed();
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
    public boolean isTimerSetTo(TimerKeys timerSetting) {
        return false;
    }

    @Override
    public boolean isAudiobookNamePresent(String audiobookName) {
        return getElementFactory().getLabel(By.xpath(String.format(AUDIOBOOK_NAME_LOC, audiobookName)), "audiobookName").state().waitForDisplayed();
    }

    private static final Map<String, String> speedName = new HashMap<String, String>() {{
        put("2.0", "Two times normal speed. Fastest.");
        put("0.75", "Three quarters of normal speed. Slower.");
        put("1.25", "One and one quarter faster than normal speed.");
        put("1.50", "One and a half times faster than normal speed.");
    }};

    @Override
    public boolean isPlaybackSpeedPresent(String playbackSpeed) {
        String speedOptionName = speedName.get(playbackSpeed);
        return getElementFactory().getButton(By.xpath(String.format(PLAYBACK_SPEED_LOC, speedOptionName)), speedOptionName).state().waitForDisplayed();
    }

    @Override
    public boolean isTimerEqualTo(Duration chapterLength) {
        int seconds = (int) chapterLength.getSeconds() % 60;
        int minutes = (int) (chapterLength.toMinutes() >= 60 ? chapterLength.toMinutes() % 60 : chapterLength.toMinutes());
        return getElementFactory().getButton(By.xpath(String.format(TIME_IN_HOURS_LEFT_XPATH_LOCATOR,
                (int) chapterLength.toHours(), minutes)), "Timer").state().isDisplayed() ||
                getElementFactory().getButton(By.xpath(String.format(TIME_IN_MINUTES_LEFT_XPATH_LOCATOR, minutes, seconds)), "Timer").state().isDisplayed() ||
                getElementFactory().getButton(By.xpath(String.format(TIME_IN_SECONDS_LEFT_XPATH_LOCATOR, seconds)), "Timer").state().isDisplayed();
    }

    @Override
    public boolean isPlayButtonPresent() {
        return btnPlay.state().waitForDisplayed();
    }

    @Override
    public Duration getRightTime() {
        return DateUtils.getDuration(lblRightTime.getAttribute(IosAttributes.VALUE));
    }

    @Override
    public boolean isPauseButtonPresent() {
        return btnPause.state().waitForDisplayed();
    }

    @Override
    public Duration getLeftTime() {
        return DateUtils.getDuration(lblLeftTime.getAttribute(IosAttributes.VALUE));
    }

    @Override
    public void tapOnPlayBarForward() {
        double xPositionFurtherFromCenter = lblPlaybackProgress.getElement().getCenter().x * 1.25;
        double yPosition = lblPlaybackProgress.getElement().getCenter().y;
        TouchAction action = new TouchAction(AqualityServices.getApplication().getDriver());
        action.tap(PointOption.point((int) xPositionFurtherFromCenter, (int) yPosition)).perform();
    }

    @Override
    public void tapOnPlayBarBackward() {
        double xPositionCloserThanCenter = lblPlaybackProgress.getElement().getCenter().x * 0.2;
        double yPosition = lblPlaybackProgress.getElement().getCenter().y;
        TouchAction action = new TouchAction(AqualityServices.getApplication().getDriver());
        action.tap(PointOption.point((int) xPositionCloserThanCenter, (int) yPosition)).perform();
    }

    @Override
    public boolean isLineRemainingDisplayed() {
        return lblLineRemaining.state().isDisplayed();
    }

    @Override
    public String getPlaySpeedValue() {
        return btnPlaySpeed.getText();
    }

    @Override
    public void stretchPlaySliderForward() {
        int startX = AqualityServices.getApplication().getDriver().findElement(btnSlider.getLocator()).getLocation().getX();
        int startY = AqualityServices.getApplication().getDriver().findElement(btnSlider.getLocator()).getLocation().getY();
        int endX = (startX + 100);
        TouchAction action = new TouchAction(AqualityServices.getApplication().getDriver());
        action.press(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, startY)).release().perform();
    }

    @Override
    public void stretchPlaySliderBack() {
        double startX = AqualityServices.getApplication().getDriver().findElement(btnSlider.getLocator()).getLocation().getX() * 8;
        double startY = AqualityServices.getApplication().getDriver().findElement(btnSlider.getLocator()).getLocation().getY();
        double endX = AqualityServices.getApplication().getDriver().findElement(lblPlaybackProgress.getLocator()).getLocation().getX() * 0.15;
        TouchAction action = new TouchAction(AqualityServices.getApplication().getDriver());
        action.longPress(PointOption.point((int) startX, (int) startY)).moveTo(PointOption.point((int) endX, (int) startY)).release().perform();
    }

    @Override
    public String getChapterName() {
        return lblChapterName.getAttribute(IosAttributes.VALUE);
    }

    @Override
    public void skipAhead() {
        btnSkipAhead.click();
    }
    @Override
    public void returnToPreviousScreen() {
        btnBack.click();
    }

    @Override
    public void tapPlayBtn() {
        btnPlay.click();
    }

    @Override
    public void tapPauseBtn() {
        btnPause.click();
    }

    @Override
    public void skipBehind() {
        btnSkipBehind.click();
    }
}
