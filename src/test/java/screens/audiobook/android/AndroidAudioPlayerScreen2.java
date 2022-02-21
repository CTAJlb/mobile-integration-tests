package screens.audiobook.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import constants.application.attributes.AndroidAttributes;
import constants.application.timeouts.AudioBookTimeouts;
import constants.localization.application.catalog.TimerKeys;
import framework.utilities.DateUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import screens.audiobook.AudioPlayerScreen2;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidAudioPlayerScreen2 extends AudioPlayerScreen2 {
    private static final String UNIQUE_ELEMENT = "//android.widget.ImageView[contains(@resource-id,\"player_jump_forwards\")]";
    private static final String CHAPTERS_LOC = "//android.widget.RelativeLayout[.//*[contains(@resource-id, \"player_toc_item_view_title\")]]";
    private static final String CHAPTERS_TEXT = "//android.widget.RelativeLayout//android.widget.TextView[contains(@resource-id, \"player_toc_item_view_title\")]";
    private static final String SPEED_OPTION_XPATH_LOCATOR_PATTERN = "//*[contains(@resource-id, \"player_menu_playback_rate_text\") and @text=\"%sx\"]";
    private static final String TIMER_XPATH_PATTERN_LOCATOR = "//*[contains(@resource-id, \"player_sleep_item_view_name\") and @text=\"%s\"]";
    private static final String TIMER_SETTING_XPATH_LOCATOR_PATTERN = "//*[contains(@resource-id, \"player_menu_sleep\") and @content-desc=\"Set Your Sleep Timer. The Sleep Timer Is Currently Set To Sleep At %s\"]";
    private static final String AUDIOBOOK_NAME_LOCATOR = "//android.widget.TextView[@text=\"%s\"]";

    private final IButton btnMenu = getElementFactory().getButton(By.id("player_menu_toc"), "Menu");
    private final ILabel lblCurrentChapter = getElementFactory().getLabel(By.id("player_spine_element"), "Current chapter");
    private final ILabel lblCurrentTiming = getElementFactory().getLabel(By.id("player_time"), "Current time");
    private final ILabel lblChapterLength = getElementFactory().getLabel(By.id("player_time_maximum"), "Chapter length");
    private final IButton btnPlay =
            getElementFactory().getButton(By.xpath("//android.widget.ImageView[@content-desc=\"Play\"]"), "Play");
    private final IButton btnPause =
            getElementFactory().getButton(By.xpath("//android.widget.ImageView[@content-desc=\"Pause\"]"), "Pause");
    private final IButton btnSkipAhead = getElementFactory().getButton(By.id("player_jump_forwards"), "Move forwards");
    private final IButton btnSkipBehind = getElementFactory().getButton(By.id("player_jump_backwards"), "Move backwards");
    private final IButton btnProgressButton = getElementFactory().getButton(By.id("player_progress"), "Player progress");
    private final IButton btnPlaybackSpeed = getElementFactory().getButton(By.id("player_menu_playback_rate_image"), "Player speed");
    private final IButton btnSleep = getElementFactory().getButton(By.id("player_menu_sleep_image"), "Sleep");

    public AndroidAudioPlayerScreen2() {
        super(By.xpath(UNIQUE_ELEMENT));
    }

    public List<IElement> getChapters() {
        return getElementFactory().findElements(By.xpath(CHAPTERS_LOC), ElementType.LABEL).stream().limit(5).collect(Collectors.toList());
    }

    public List<IElement> getChaptersText() {
        return getElementFactory().findElements(By.xpath(CHAPTERS_TEXT), ElementType.LABEL).stream().limit(5).collect(Collectors.toList());
    }

    @Override
    public void openToc() {
        btnMenu.click();
    }

    @Override
    public void goBack() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }

    @Override
    public String selectChapterAndGetText(int chapterNumber) {
        IElement lblChapterText = getChaptersText().get(chapterNumber);
        String chapterText = lblChapterText.getAttribute(AndroidAttributes.TEXT);
        lblChapterText.click();
        return chapterText;
    }

    @Override
    public String getCurrentChapterInfo() {
        return lblCurrentChapter.getText();
    }

    @Override
    public int getCountOfChapters() {
        return getChapters().size();
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
    public boolean isPauseButtonPresent() {
        AqualityServices.getApplication().getDriver().getPageSource();
        return btnPause.state().waitForExist();
    }

    @Override
    public boolean isPlayButtonPresent() {
        return btnPlay.state().waitForDisplayed();
    }

    @Override
    public Duration getCurrentPlayTime() {
        return DateUtils.getDuration(lblCurrentTiming.getText());
    }

    @Override
    public void selectPlaybackSpeed(String playbackSpeed) {
        btnPlaybackSpeed.click();
        getElementFactory().getButton(By.xpath("//*[@text=\"" + playbackSpeed + "x\"]"), "Playback speed").click();
    }

    @Override
    public boolean isSpeedOptionSelected(String playbackSpeed) {
        return getElementFactory().getButton(By.xpath(String.format(SPEED_OPTION_XPATH_LOCATOR_PATTERN, playbackSpeed)), "Playback speed").state().waitForDisplayed();
    }

    @Override
    public void setTimer(TimerKeys timerSetting) {
        btnSleep.click();
        String timerSettingName = timerSetting.i18n().replace("Chapter", "chapter");
        getElementFactory().getButton(By.xpath(String.format(TIMER_XPATH_PATTERN_LOCATOR, timerSettingName)), timerSettingName).click();
    }

    @Override
    public boolean isTimerEqualTo(Duration chapterLength) {
        return false;
    }

    @Override
    public boolean isTimerSetTo(TimerKeys timerSetting) {
        String timerSettingName = timerSetting.i18n();
        timerSettingName = timerSettingName.replace("file", "File");
        return getElementFactory().getButton(By.xpath(String.format(TIMER_SETTING_XPATH_LOCATOR_PATTERN, timerSettingName)), timerSettingName, ElementState.EXISTS_IN_ANY_STATE).state().waitForDisplayed();
    }

    @Override
    public boolean isAudiobookNamePresent(String audiobookName) {
        boolean isAudiobookNameCorrect = getElementFactory().getLabel(By.xpath(String.format(AUDIOBOOK_NAME_LOCATOR, audiobookName)), "audiobookName").state().waitForDisplayed();
        return isAudiobookNameCorrect;
    }

    @Override
    public void skipAhead() {
        btnSkipAhead.click();
    }

    @Override
    public void skipBehind() {
        btnSkipBehind.click();
    }

    @Override
    public void moveChapterToMiddle() {
        btnProgressButton.click();
    }

    @Override
    public Duration getChapterLength() {
        return DateUtils.getDuration(lblChapterLength.getText());
    }
}
