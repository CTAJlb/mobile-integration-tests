package screens.settings;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class SettingsScreen extends Screen {
    protected SettingsScreen(By locator) {
        super(locator, "Settings");
    }

    public abstract void openLibraries();

    public abstract void openAboutPalace();

    public abstract void openPrivacyPolicy();

    public abstract void openUserAgreement();

    public abstract void openSoftwareLic();

    public abstract boolean isSettingsScreenOpened();

    public abstract void openLibrary(String libraryName);

    public abstract void openTestMode();

    public abstract String getTextFromSettingsHeader();

    public abstract String getTextFromLibrariesBtn();

    public abstract String getTextFromAboutAppBtn();

    public abstract String getTextFromPrivacyPolicyBtn();

    public abstract String getTextFromUserAgreementBtn();

    public abstract String getTextFromSoftwareLicensesBtn();
}
