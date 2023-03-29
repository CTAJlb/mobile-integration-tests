package screens.alert.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import org.openqa.selenium.By;
import screens.alert.AlertScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidAlertScreen extends AlertScreen {
    //todo I should check a unique element of screen for android
    public AndroidAlertScreen() {
        super(By.id("android:id/message"));
    }

    @Override
    public void waitAndPerformAlertActionIfDisplayed(EnumActionButtonsForBooksAndAlertsKeys actionButtonNamesAlertKeys) {
        //only ios
    }

    @Override
    public void performAlertActionIfDisplayedInSpanish(String actionBtnKey) {
        //for ios
    }

    @Override
    public String getTextFromAlertHeader() {
        //for android
        return null;
    }
}
