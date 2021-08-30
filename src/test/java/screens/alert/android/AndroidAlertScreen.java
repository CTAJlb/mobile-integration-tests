package screens.alert.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.alert.AlertScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidAlertScreen extends AlertScreen {

    public AndroidAlertScreen() {
        super(By.id("android:id/message"));
    }

    @Override
    public void closeNotNowModalIfDisplayed() {
        //only for ios
    }

    @Override
    public void closeDoNotAllowIfPresent() {
        //only for ios
    }
}
