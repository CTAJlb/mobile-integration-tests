package screens.audiobook.tocAudiobook.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.audiobook.tocAudiobook.TocAudiobookScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidTocAudiobookScreen extends TocAudiobookScreen {
    public AndroidTocAudiobookScreen() {
        super(By.xpath(""));
    }
}
