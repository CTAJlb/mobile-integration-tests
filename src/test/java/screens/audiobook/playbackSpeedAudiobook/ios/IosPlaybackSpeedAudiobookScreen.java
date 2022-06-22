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
    private final IButton btnCancel = AqualityServices.getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Cancel\"]"), "cancel button");

    private static Map<String, String> speedName = new HashMap<String, String>() {{
        put("2.0", "Two times normal speed. Fastest.");
        put("0.75", "Three quarters of normal speed. Slower.");
    }};
    public IosPlaybackSpeedAudiobookScreen() {
        super(By.xpath("//XCUIElementTypeStaticText[@name=\"Playback Speed\"]"));
    }

    @Override
    public void selectPlaybackSpeed(String playbackSpeed) {
        String speedOptionName = speedName.get(playbackSpeed);
        getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"" + speedOptionName + "\"]"), speedOptionName).click();
    }

    @Override
    public void closePlaybackScreen() {
        btnCancel.click();
    }
}
