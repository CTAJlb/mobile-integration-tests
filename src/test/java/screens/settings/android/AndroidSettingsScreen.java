package screens.settings.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.application.timeouts.AuthorizationTimeouts;
import org.openqa.selenium.By;
import screens.settings.SettingsScreen;

import java.time.Duration;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSettingsScreen extends SettingsScreen {
    private final IButton accountsBtn =
            getElementFactory().getButton(By.xpath("//android.widget.RelativeLayout/android.widget.TextView[contains(@text, \"Accounts\")]"), "Accounts");

    public AndroidSettingsScreen() {
        super(By.xpath("//android.widget.TextView[contains(@text, \"App info\")]"));
    }

    @Override
    public void openAccounts() {
        accountsBtn.click();
    }
}
