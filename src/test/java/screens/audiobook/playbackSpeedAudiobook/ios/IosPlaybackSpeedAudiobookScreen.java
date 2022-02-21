package screens.audiobook.playbackSpeedAudiobook.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.audiobook.playbackSpeedAudiobook.PlaybackSpeedAudiobookScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosPlaybackSpeedAudiobookScreen extends PlaybackSpeedAudiobookScreen {
    public IosPlaybackSpeedAudiobookScreen() {
        super(By.xpath(""));
    }
}
