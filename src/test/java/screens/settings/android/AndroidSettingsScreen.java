package screens.settings.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.settings.SettingsScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSettingsScreen extends SettingsScreen {
    private final IButton librariesBtn =
            getElementFactory().getButton(By.xpath("//android.widget.TextView[contains(@text, \"Libraries\")]"), "Accounts");

    public AndroidSettingsScreen() {
        super(By.xpath("//android.widget.TextView[contains(@text, \"App info\")]"));
    }

    @Override
    public void openLibraries() {
        librariesBtn.click();
    }
}
