package screens.settings;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class SettingsScreen extends Screen {
    protected SettingsScreen(By locator) {
        super(locator, "Settings");
    }

    public abstract void openLibraries();
    public abstract void openLibrariesES();
    public abstract void openLibrariesIT();

    public abstract void openAboutPalace();

    public abstract void openPrivacyPolicy();

    public abstract void openUserAgreement();

    public abstract void openSoftwareLic();

    public abstract boolean isSettingsScreenOpened();

    public abstract void openLibrary(String libraryName);

    public abstract void openTestMode();

    public abstract String getTextFromSettingsHeader();

    public abstract String getTextFromLibrariesBtn();

    public abstract String getTextFromLibrariesBtnES();

    public abstract String getTextFromLibrariesBtnIT();

    public abstract String getTextFromAboutAppBtnES();

    public abstract String getTextFromAboutAppBtnIT();

    public abstract String getTextFromPrivacyPolicyBtnES();

    public abstract String getTextFromPrivacyPolicyBtnIT();

    public abstract String getTextFromUserAgreementBtnES();

    public abstract String getTextFromUserAgreementBtnIT();

    public abstract String getTextFromSoftwareLicensesBtnES();

    public abstract String getTextFromSoftwareLicensesBtnIT();
}
