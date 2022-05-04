package screens.settings.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.settings.SettingsScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosSettingsScreen extends SettingsScreen {
    private static final String MAIN_ELEMENT = "//XCUIElementTypeNavigationBar[@name=\"Settings\"]";

    private final IButton btnLibraries = getElementFactory().getButton(
            By.xpath("//XCUIElementTypeButton[@name=\"Libraries\"]"), "btnLibraries");
    private final IButton btnAboutPalace = getElementFactory().getButton(
            By.xpath("//XCUIElementTypeButton[@name=\"About Palace\"]"), "About Palace");
    private final IButton btnPrivacyPolicy = getElementFactory().getButton(
            By.xpath("//XCUIElementTypeButton[@name=\"Privacy Policy\"]"), "Privacy Policy");
    private final IButton btnUserAgreement = getElementFactory().getButton(
            By.xpath("//XCUIElementTypeButton[@name=\"User Agreement\"]"), "User Agreement");
    private final IButton btnSoftwareLic = getElementFactory().getButton(
            By.xpath("//XCUIElementTypeButton[@name=\"Software Licenses\"]"), "Software Licenses");

    public IosSettingsScreen() {
        super(By.xpath(MAIN_ELEMENT));
    }

    @Override
    public void openLibraries() {
        btnLibraries.click();
    }

    @Override
    public void openAboutPalace() {
        btnAboutPalace.click();
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
}
