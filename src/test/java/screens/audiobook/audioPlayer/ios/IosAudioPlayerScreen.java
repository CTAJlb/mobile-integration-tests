package screens.audiobook.audioPlayer.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.audiobook.audioPlayer.AudioPlayerScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosAudioPlayerScreen extends AudioPlayerScreen {
    public IosAudioPlayerScreen() {
        super(By.xpath(""));
    }
}
