package screens.findyourlibraryscreen.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.findyourlibraryscreen.FindYourLibScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidFindYourLibScreen extends FindYourLibScreen {

    public AndroidFindYourLibScreen() {
        super(By.xpath(""));
    }

    @Override
    public void tapAddLibrary() {

    }
}
