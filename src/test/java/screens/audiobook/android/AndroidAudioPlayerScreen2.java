package screens.audiobook.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.application.attributes.AndroidAttributes;
import constants.localization.application.catalog.TimerKeys;
import org.openqa.selenium.By;
import screens.audiobook.AudioPlayerScreen2;

import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidAudioPlayerScreen2 extends AudioPlayerScreen2 {
    private static final String UNIQUE_ELEMENT = "//android.widget.ImageView[contains(@resource-id,\"player_jump_forwards\")]";
    private static final String CHAPTERS_LOC = "//android.widget.RelativeLayout[.//*[contains(@resource-id, \"player_toc_item_view_title\")]]";
    private static final String CHAPTERS_TEXT = "//android.widget.RelativeLayout//android.widget.TextView[contains(@resource-id, \"player_toc_item_view_title\")]";
    private static final String TIMER_XPATH_PATTERN_LOCATOR = "//*[contains(@resource-id, \"player_sleep_item_view_name\") and @text=\"%s\"]";

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
    public String selectChapterAndGetText(int chapterNumber) {
        IElement lblChapterText = getChaptersText().get(chapterNumber);
        String chapterText = lblChapterText.getAttribute(AndroidAttributes.TEXT);
        lblChapterText.click();
        return chapterText;
    }

    @Override
    public int getCountOfChapters() {
        return getChapters().size();
    }

    @Override
    public void selectPlaybackSpeed(String playbackSpeed) {
        btnPlaybackSpeed.click();
        getElementFactory().getButton(By.xpath("//*[@text=\"" + playbackSpeed + "x\"]"), "Playback speed").click();
    }

    @Override
    public void setTimer(TimerKeys timerSetting) {
        btnSleep.click();
        String timerSettingName = timerSetting.i18n().replace("Chapter", "chapter");
        getElementFactory().getButton(By.xpath(String.format(TIMER_XPATH_PATTERN_LOCATOR, timerSettingName)), timerSettingName).click();
    }
}
