package screens.audiobook.playbackSpeedAudiobook.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.audiobook.sleepTimerAudiobook.SleepTimerAudiobookScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSleepTimerAudiobookScreen extends SleepTimerAudiobookScreen {
    public AndroidSleepTimerAudiobookScreen() {
        super(By.xpath(""));
    }
}
