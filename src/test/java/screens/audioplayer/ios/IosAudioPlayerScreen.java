package screens.audioplayer.ios;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import constants.application.attributes.IosAttributes;
import constants.application.timeouts.AudioBookTimeouts;
import constants.application.timeouts.BooksTimeouts;
import constants.application.timeouts.CategoriesTimeouts;
import constants.localization.application.catalog.TimerKeys;
import framework.utilities.DateUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import screens.audioplayer.AudioPlayerScreen;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ScreenType(platform = PlatformName.IOS)
public class IosAudioPlayerScreen extends AudioPlayerScreen {
    private static final String MAIN_ELEMENT = "//XCUIElementTypeImage[@name=\"cover_art\"]";
    private static final String CHECKING_DOWNLOAD = "//XCUIElementTypeAny";
    private static final String CHAPTERS_LOCATOR = "//XCUIElementTypeTable//XCUIElementTypeCell";
    private static final String CHAPTERS_TEXT = "//XCUIElementTypeTable//XCUIElementTypeCell//XCUIElementTypeStaticText[1]";
    private static final String LOADED_CHAPTERS_LOCATOR = "//XCUIElementTypeTable//XCUIElementTypeCell//XCUIElementTypeOther[@visible=\"false\"]";
    private static final int COUNT_OF_CHAPTERS_TO_WAIT_FOR = 3;
    private static final String PLAYBACK_OPTION_XPATH_LOCATOR = "//XCUIElementTypeToolbar//XCUIElementTypeButton[@name=\"%s\"]";
    private static final String TIME_IN_HOURS_LEFT_XPATH_LOCATOR = "//XCUIElementTypeToolbar//XCUIElementTypeButton[@name=\"%d hour and %d minutes until playback pauses\"]";
    private static final String TIME_IN_MINUTES_LEFT_XPATH_LOCATOR = "//XCUIElementTypeToolbar//XCUIElementTypeButton[@name=\"%d minutes and %d seconds until playback pauses\"]";
    private static final String AUDIOBOOK_NAME_LOCATOR = "//XCUIElementTypeStaticText[@name=\"%s\"]";

    private final IButton btnMenu =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Table of Contents\"]"), "Menu");
    private final IButton btnPlay =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@label=\"Play\"]"), "Play");
    private final IButton btnPause =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@label=\"Pause\"]"), "Pause");
    private final IButton btnProgress = getElementFactory().getButton(By.name("progress_background"), "Progress bar");
    private final IButton btnBehind = getElementFactory().getButton(By.name("skip_back"), "Behind");
    private final IButton btnAhead = getElementFactory().getButton(By.name("skip_forward"), "Ahead");
    private final IButton btnPlaybackSpeed =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeToolbar//XCUIElementTypeButton"), "Playback speed");
    private final IButton btnGoBack =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar//XCUIElementTypeButton[1]"), "Go Back");
    private final IButton btnTimer =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeToolbar//XCUIElementTypeButton[3]"), "Timer");
    private final ILabel lblCurrentChapter =
            getElementFactory().getLabel(By.xpath("(//XCUIElementTypeStaticText[@name=\"progress_rightLabel\"])[1]"), "Current chapter");
    private final ILabel lblPercentageValue =
            getElementFactory().getLabel(By.xpath("(//XCUIElementTypeProgressIndicator/following-sibling::XCUIElementTypeStaticText"), "Percentage Value");
    private final ILabel lblChapterTime =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@name=\"progress_rightLabel\" and contains(@value,\":\")]"), "Chapter time", ElementState.EXISTS_IN_ANY_STATE);
    private final ILabel lblCurrentTime =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@name=\"progress_leftLabel\"]"), "Current time", ElementState.EXISTS_IN_ANY_STATE);
    private final ILabel lblDownloadingStatus =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@value=\"Downloading\"]"), "Downloading");

    private static Map<String, String> speedName = new HashMap<String, String>() {{
        put("2.0", "Two times normal speed. Fastest.");
        put("0.75", "Three quarters of normal speed. Slower.");
    }};

    public IosAudioPlayerScreen() {
        super(By.xpath(MAIN_ELEMENT));
    }

    public List<ILabel> getChapters() {
        return getElementFactory().findElements(By.xpath(CHAPTERS_LOCATOR), ElementType.LABEL);
    }

    public List<ILabel> getChaptersText() {
        return getElementFactory().findElements(By.xpath(CHAPTERS_TEXT), ElementType.LABEL);
    }

    @Override
    public void checkThatChaptersVisible() {
        Assert.assertTrue("Checking that count of chapters greater than zero", AqualityServices.getConditionalWait().waitFor(() -> getChapters().size() > 0));
    }

    @Override
    public void waitAndCheckAllChaptersLoaded() {
        checkThatChaptersVisible();

        boolean isAllLoadersDisappeared = AqualityServices.getConditionalWait().waitFor(() -> getLabelsForCheckingDownload().size() == 0, Duration.ofMillis(AudioBookTimeouts.TIMEOUT_AUDIO_BOOK_LOADER_DISAPPEAR.getTimeoutMillis()));
        Assert.assertTrue("Not all chapters loaded", isAllLoadersDisappeared);
    }

    private List<ILabel> getLabelsForCheckingDownload() {
        return getElementFactory().findElements(By.xpath(CHECKING_DOWNLOAD), ElementType.LABEL);
    }

    @Override
    public void openMenu() {
        btnMenu.click();
    }

    @Override
    public void goBack() {
        btnGoBack.click();
    }

    @Override
    public Integer getPercentageValue() {

        lblPercentageValue.state().waitForDisplayed(Duration.ofMillis(40000));
        String percentageValueString = lblPercentageValue.getAttribute(IosAttributes.NAME);
        percentageValueString = percentageValueString.replace("%", "");
        return Integer.valueOf(percentageValueString);
    }

    @Override
    public String selectChapterAndGetText(int chapterNumber) {
        AqualityServices.getConditionalWait().waitFor(() -> getElementFactory().findElements(By.xpath(LOADED_CHAPTERS_LOCATOR), ElementType.LABEL).size() >= chapterNumber, Duration.ofMillis(CategoriesTimeouts.TIMEOUT_WAIT_UNTIL_CATEGORY_PAGE_LOAD.getTimeoutMillis()));
        ILabel chapter = getChaptersText().get(chapterNumber - 1);
        String chapterText = chapter.getAttribute(IosAttributes.NAME);
        chapter.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        chapter.click();
        return chapterText;
    }

    @Override
    public String getCurrentChapterInfo() {
        return lblCurrentChapter.getAttribute(IosAttributes.VALUE);
    }

    @Override
    public int getCountOfChapters() {
        AqualityServices.getConditionalWait().waitFor(() -> getChapters().size() > COUNT_OF_CHAPTERS_TO_WAIT_FOR, Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
        return getChapters().size();
    }

    @Override
    public void playBook() {
        waitForLoadingDisappearing();
        btnPlay.click();
    }

    @Override
    public void pauseBook() {
        btnPause.click();
    }

    @Override
    public boolean isPauseButtonPresent() {
        return btnPause.state().isDisplayed();
    }

    @Override
    public boolean isPlayButtonPresent() {
        return btnPlay.state().isDisplayed();
    }

    @Override
    public Duration getCurrentPlayTime() {
        return DateUtils.getDuration(lblCurrentTime.getAttribute(IosAttributes.VALUE));
    }

    @Override
    public String getLoadingStatus() {
        return "";
    }

    @Override
    public void skipAhead() {
        btnAhead.click();
    }

    @Override
    public void skipBehind() {
        btnBehind.click();
    }

    @Override
    public void moveChapterToMiddle() {
        btnProgress.click();
    }

    @Override
    public Duration getChapterLength() {
        return DateUtils.getDuration(lblChapterTime.getAttribute(IosAttributes.VALUE));
    }

    @Override
    public void waitForBookLoading() {
        lblCurrentChapter.state().waitForDisplayed();
        waitForLoadingDisappearing();
    }

    @Override
    public void waitForLoadingDisappearing() {
        lblDownloadingStatus.state().waitForDisplayed();
        lblDownloadingStatus.state().waitForNotExist();
    }

    @Override
    public void selectPlaybackSpeed(String playbackSpeed) {
        btnPlaybackSpeed.click();
        String speedOptionName = speedName.get(playbackSpeed);
        getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"" + speedOptionName + "\"]"), speedOptionName).click();
    }

    @Override
    public boolean isSpeedOptionSelected(String playbackSpeed) {
        String speedOptionName = speedName.get(playbackSpeed);
        return getElementFactory().getButton(By.xpath(String.format(PLAYBACK_OPTION_XPATH_LOCATOR, speedOptionName)), speedOptionName).state().waitForDisplayed();
    }

    @Override
    public void setTimer(TimerKeys timerSetting) {
        btnTimer.click();
        String buttonName = timerSetting.i18n();
        getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"" + buttonName + "\"]"), buttonName).click();
    }

    @Override
    public boolean isTimerEqualTo(Duration chapterLength) {
        int seconds = (int) chapterLength.getSeconds() % 60;
        int minutes = (int) (chapterLength.toMinutes() >= 60 ? chapterLength.toMinutes() % 60 : chapterLength.toMinutes());
        return getElementFactory().getButton(By.xpath(String.format(TIME_IN_HOURS_LEFT_XPATH_LOCATOR, (int) chapterLength.toHours(), minutes)), "Timer").state().isDisplayed() || getElementFactory().getButton(By.xpath(String.format(TIME_IN_MINUTES_LEFT_XPATH_LOCATOR, minutes, seconds)), "Timer").state().isDisplayed();
    }

    @Override
    public boolean isTimerSetTo(TimerKeys timerSetting) {
        return false;
    }

    @Override
    public boolean isAudiobookNameCorrect(String audiobookName) {
        boolean isAudiobookNameCorrect = getElementFactory().getLabel(By.xpath(String.format(AUDIOBOOK_NAME_LOCATOR, audiobookName)), "audiobookName").state().isDisplayed();
        return isAudiobookNameCorrect;
    }
}
