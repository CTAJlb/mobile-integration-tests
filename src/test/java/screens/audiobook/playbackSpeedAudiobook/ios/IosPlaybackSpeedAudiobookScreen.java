package screens.audiobook.playbackSpeedAudiobook.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.audiobook.playbackSpeedAudiobook.PlaybackSpeedAudiobookScreen;

import java.util.HashMap;
import java.util.Map;

@ScreenType(platform = PlatformName.IOS)
public class IosPlaybackSpeedAudiobookScreen extends PlaybackSpeedAudiobookScreen {
    private static final String PLAYBACK_SPEED = "//XCUIElementTypeButton[@name=\"%s\"]";

    private final IButton btnCancel = AqualityServices.getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Cancel\"]"), "cancel button");

    private static Map<String, String> speedName = new HashMap<String, String>() {{
        put("2.0", "Two times normal speed. Fastest.");
        put("0.75", "Three quarters of normal speed. Slower.");
        put("1.25", "One and one quarter faster than normal speed.");
        put("1.50", "One and a half times faster than normal speed.");

    }};
    public IosPlaybackSpeedAudiobookScreen() {
        super(By.xpath("//XCUIElementTypeStaticText[@name=\"Playback Speed\"]"));
    }

    @Override
    public void selectPlaybackSpeed(String playbackSpeed) {
        String speedOptionName = speedName.get(playbackSpeed);
        getElementFactory().getButton(By.xpath(String.format(PLAYBACK_SPEED, speedOptionName)), speedOptionName).click();
    }

    @Override
    public void closePlaybackScreen() {
        btnCancel.click();
    }
}
