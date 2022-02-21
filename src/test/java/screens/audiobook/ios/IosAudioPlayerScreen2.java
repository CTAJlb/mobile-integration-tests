package screens.audiobook.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.application.attributes.IosAttributes;
import constants.localization.application.catalog.TimerKeys;
import org.openqa.selenium.By;
import screens.audiobook.AudioPlayerScreen2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.IOS)
public class IosAudioPlayerScreen2 extends AudioPlayerScreen2 {
    private static final String MAIN_ELEMENT = "//XCUIElementTypeImage[@name=\"cover_art\"]";
    private static final String CHAPTERS_LOCATOR = "//XCUIElementTypeTable//XCUIElementTypeCell";
    private static final String CHAPTERS_TEXT = "//XCUIElementTypeTable//XCUIElementTypeCell//XCUIElementTypeStaticText[1]";

    private final IButton btnPlaybackSpeed =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeToolbar//XCUIElementTypeButton"), "Playback speed");

    private final IButton btnTimer =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeToolbar//XCUIElementTypeButton[3]"), "Timer");

    private static Map<String, String> speedName = new HashMap<String, String>() {{
        put("2.0", "Two times normal speed. Fastest.");
        put("0.75", "Three quarters of normal speed. Slower.");
    }};

    public IosAudioPlayerScreen2() {
        super(By.xpath(MAIN_ELEMENT));
    }

    public List<IElement> getChapters() {
        return getElementFactory().findElements(By.xpath(CHAPTERS_LOCATOR), ElementType.LABEL).stream().limit(5).collect(Collectors.toList());
    }

    public List<IElement> getChaptersText() {
        return getElementFactory().findElements(By.xpath(CHAPTERS_TEXT), ElementType.LABEL).stream().limit(5).collect(Collectors.toList());
    }

    @Override
    public String selectChapterAndGetText(int chapterNumber) {
        IElement lblChapterText = getChaptersText().get(chapterNumber);
        String chapterText = lblChapterText.getAttribute(IosAttributes.NAME);
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
        String speedOptionName = speedName.get(playbackSpeed);
        getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"" + speedOptionName + "\"]"), speedOptionName).click();
    }

    @Override
    public void setTimer(TimerKeys timerSetting) {
        btnTimer.click();
        String buttonName = timerSetting.i18n();
        getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"" + buttonName + "\"]"), buttonName).click();
    }
}
