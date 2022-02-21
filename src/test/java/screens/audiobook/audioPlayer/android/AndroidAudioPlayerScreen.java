package screens.audiobook.audioPlayer.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.audiobook.audioPlayer.AudioPlayerScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidAudioPlayerScreen extends AudioPlayerScreen {
    public AndroidAudioPlayerScreen() {
        super(By.xpath(""));
    }
}
