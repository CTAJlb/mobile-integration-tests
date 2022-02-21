package screens.audiobook.playbackSpeedAudiobook.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.audiobook.playbackSpeedAudiobook.PlaybackSpeedAudiobookScreen;

import java.util.HashMap;
import java.util.Map;

@ScreenType(platform = PlatformName.IOS)
public class IosPlaybackSpeedAudiobookScreen extends PlaybackSpeedAudiobookScreen {
    private final IButton btnPlaybackSpeed =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeToolbar//XCUIElementTypeButton"), "Playback speed");
    private static Map<String, String> speedName = new HashMap<String, String>() {{
        put("2.0", "Two times normal speed. Fastest.");
        put("0.75", "Three quarters of normal speed. Slower.");
    }};
    public IosPlaybackSpeedAudiobookScreen() {
        super(By.xpath(""));
    }

    @Override
    public void selectPlaybackSpeed(String playbackSpeed) {
        btnPlaybackSpeed.click();
        String speedOptionName = speedName.get(playbackSpeed);
        getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"" + speedOptionName + "\"]"), speedOptionName).click();
    }
}
