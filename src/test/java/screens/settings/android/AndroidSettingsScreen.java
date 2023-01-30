package screens.settings.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import screens.settings.SettingsScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSettingsScreen extends SettingsScreen {
    private static final String LIBRARY_LOCATOR = "//android.widget.LinearLayout//android.widget.TextView[@text=\"%s\"]";

    private final IButton librariesBtn = getElementFactory().getButton(By.xpath("//android.widget.TextView[contains(@text, \"Libraries\")]"), "Accounts");
    private final IButton btnPrivacyPolicy = getElementFactory().getButton(By.xpath("//android.widget.TextView[@text=\"Privacy Policy\"]"), "Privacy Policy");
    private final IButton btnUserAgreement = getElementFactory().getButton(By.xpath("//android.widget.TextView[@text=\"User Agreement\"]"), "User Agreement");
    private final IButton btnSoftwareLic = getElementFactory().getButton(By.xpath("//android.widget.TextView[@text=\"Software Licenses\"]"), "Software Licenses");
    private final ILabel lblSettings = getElementFactory().getLabel(By.xpath("//android.widget.TextView"), "Settings");
    private final IButton btnCommit = getElementFactory().getButton(By.xpath("//android.widget.TextView[@text=\"Commit\"]"), "Commit button");
    private final IButton btnDebugOptions = getElementFactory().getButton(By.xpath("//android.widget.TextView[@text=\"Debug options\"]"), "Debug options button");

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

    @Override
    public void openLibrary(String libraryName) {
        getElementFactory().getButton(By.xpath(String.format(LIBRARY_LOCATOR, libraryName)), "Library " + libraryName).click();
    }

    @Override
    public void openTestMode() {
        TouchAction action = new TouchAction(AqualityServices.getApplication().getDriver());
        action.tap(TapOptions.tapOptions().withTapsCount(8).withElement(ElementOption.element(btnCommit.getElement()))).
                perform();
        btnDebugOptions.click();
    }

    @Override
    public String getTextFromSettingsHeader() {
        return null;
    }

    @Override
    public String getTextFromLibrariesBtn() {
        return null;
    }

    @Override
    public String getTextFromAboutAppBtn() {
        return null;
    }

    @Override
    public String getTextFromPrivacyPolicyBtn() {
        return null;
    }

    @Override
    public String getTextFromUserAgreementBtn() {
        return null;
    }

    @Override
    public String getTextFromSoftwareLicensesBtn() {
        return null;
    }
}
