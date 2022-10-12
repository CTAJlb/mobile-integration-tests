package screens.aboutpalace.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.aboutpalace.AboutPalaceScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidAbPalaceScreen extends AboutPalaceScreen {

    public AndroidAbPalaceScreen() {
        super(By.xpath("only for ios"));
    }

    @Override
    public boolean isOpened() {
        //only for ios
        return false;
    }
}
