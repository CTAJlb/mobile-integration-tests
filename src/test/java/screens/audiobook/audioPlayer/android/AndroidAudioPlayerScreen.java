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

import java.time.Duration;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidAudioPlayerScreen extends AudioPlayerScreen {
    private static final String SPEED_OPTION_XPATH_LOCATOR_PATTERN = "//*[contains(@resource-id, \"player_menu_playback_rate_text\") and @text=\"%sx\"]";
    private static final String AUDIOBOOK_NAME_LOCATOR = "//android.widget.TextView[@text=\"%s\"]";
    private static final String TIMER_SETTING_XPATH_LOCATOR_PATTERN = "//*[contains(@resource-id, \"player_menu_sleep\") and @content-desc=\"Set Your Sleep Timer. The Sleep Timer Is Currently Set To Sleep At %s\"]";

    private final IButton btnMenu = getElementFactory().getButton(By.id("player_menu_toc"), "Menu");
    private final IButton btnPause =
            getElementFactory().getButton(By.xpath("//android.widget.ImageView[@content-desc=\"Pause\"]"), "Pause");
    private final IButton btnPlay =
            getElementFactory().getButton(By.xpath("//android.widget.ImageView[@content-desc=\"Play\"]"), "Play");
    private final IButton btnSkipAhead = getElementFactory().getButton(By.id("player_jump_forwards"), "Move forwards");
    private final IButton btnSkipBehind = getElementFactory().getButton(By.id("player_jump_backwards"), "Move backwards");
    private final ILabel lblCurrentChapter = getElementFactory().getLabel(By.id("player_spine_element"), "Current chapter");
    private final ILabel lblCurrentTiming = getElementFactory().getLabel(By.id("player_time"), "Current time");
    private final IButton btnProgressButton = getElementFactory().getButton(By.id("player_progress"), "Player progress");
    private final ILabel lblChapterLength = getElementFactory().getLabel(By.id("player_time_maximum"), "Chapter length");


    public AndroidAudioPlayerScreen() {
        super(By.xpath(""));
    }

    @Override
    public void openToc() {
        btnMenu.click();
    }

    @Override
    public boolean isPlayButtonPresent() {
        return btnPlay.state().waitForDisplayed();
    }

    @Override
    public boolean isAudiobookNamePresent(String audiobookName) {
        boolean isAudiobookNameCorrect = getElementFactory().getLabel(By.xpath(String.format(AUDIOBOOK_NAME_LOCATOR, audiobookName)), "audiobookName").state().waitForDisplayed();
        return isAudiobookNameCorrect;
    }

    @Override
    public boolean isSpeedOptionSelected(String playbackSpeed) {
        return getElementFactory().getButton(By.xpath(String.format(SPEED_OPTION_XPATH_LOCATOR_PATTERN, playbackSpeed)), "Playback speed").state().waitForDisplayed();
    }
    @Override
    public Duration getChapterLength() {
        return DateUtils.getDuration(lblChapterLength.getText());
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
    public void moveChapterToMiddle() {
        btnProgressButton.click();
    }
    @Override
    public boolean isPauseButtonPresent() {
        AqualityServices.getApplication().getDriver().getPageSource();
        return btnPause.state().waitForExist();
    }

    @Override
    public Duration getCurrentPlayTime() {
        return DateUtils.getDuration(lblCurrentTiming.getText());
    }

    @Override
    public String getCurrentChapterInfo() {
        return lblCurrentChapter.getText();
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
    public void goBack() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }

    @Override
    public void playBook() {
        btnPlay.click();
    }

    @Override
    public void pauseBook() {
        btnPause.click();
    }
}
