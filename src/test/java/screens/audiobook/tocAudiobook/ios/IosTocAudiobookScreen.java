package screens.audiobook.tocAudiobook.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.audiobook.tocAudiobook.TocAudiobookScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosTocAudiobookScreen extends TocAudiobookScreen {
    public IosTocAudiobookScreen() {
        super(By.xpath(""));
    }
}
