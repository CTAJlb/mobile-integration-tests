package screens.settings.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.settings.SettingsScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSettingsScreen extends SettingsScreen {
    private final IButton librariesBtn = getElementFactory().getButton(
            By.xpath("//android.widget.TextView[contains(@text, \"Libraries\")]"), "Accounts");
    private final IButton btnPrivacyPolicy = getElementFactory().getButton(
            By.xpath("//android.widget.TextView[@text=\"Privacy Policy\"]"), "Privacy Policy");
    private final IButton btnUserAgreement = getElementFactory().getButton(
            By.xpath("//android.widget.TextView[@text=\"User Agreement\"]"), "User Agreement");
    private final IButton btnSoftwareLic = getElementFactory().getButton(
            By.xpath("//android.widget.TextView[@text=\"Software Licenses\"]"), "Software Licenses");
    private final ILabel lblSettings = getElementFactory().getLabel(By.xpath("//android.widget.TextView"), "Settings");

    public AndroidSettingsScreen() {
        super(By.xpath("//android.widget.TextView[contains(@text, \"App info\")]"));
    }

    @Override
    public void openLibraries() {
        librariesBtn.click();
    }

    @Override
    public void openAboutPalace() {
        //only for iOS
    }

    @Override
    public void openPrivacyPolicy() {
        btnPrivacyPolicy.click();
    }

    @Override
    public void openUserAgreement() {
        btnUserAgreement.click();
    }

    @Override
    public void openSoftwareLic() {
        btnSoftwareLic.click();
    }

    @Override
    public boolean isSettingsScreenOpened() {
        return lblSettings.state().isDisplayed();
    }
}
