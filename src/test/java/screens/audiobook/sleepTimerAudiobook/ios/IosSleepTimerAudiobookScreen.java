package screens.audiobook.sleepTimerAudiobook.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.audiobook.sleepTimerAudiobook.SleepTimerAudiobookScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosSleepTimerAudiobookScreen extends SleepTimerAudiobookScreen {
    public IosSleepTimerAudiobookScreen() {
        super(By.xpath(""));
    }
}
