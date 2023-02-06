package screens.settings.ios;

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

@ScreenType(platform = PlatformName.IOS)
public class IosSettingsScreen extends SettingsScreen {
    private static final int NUMBER_OF_CLICKS_FOR_OPENING_TEST_MODE = 7;
    private static final String MAIN_ELEMENT = "//XCUIElementTypeNavigationBar[@name=\"Settings\"]";

    private  final ILabel lblSettings = getElementFactory().getLabel(By.xpath("//XCUIElementTypeNavigationBar"), "Settings");
    private final IButton btnLibraries = getElementFactory().getButton(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]//XCUIElementTypeButton"), "Libraries button");
    private final IButton btnAboutPalace = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"About Palace\"]"), "About Palace");
    private final IButton btnPrivacyPolicy = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Privacy Policy\"]"), "Privacy Policy");
    private final IButton btnUserAgreement = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"User Agreement\"]"), "User Agreement");
    private final IButton btnSoftwareLic = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Software Licenses\"]"), "Software Licenses");
    private final IButton lblPalaceVersion = getElementFactory().getButton(By.xpath("//XCUIElementTypeStaticText[contains(@name, \"Palace version\")]"), "Palace version");
    private final IButton btnTesting = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Testing\"]"), "Testing button");

    private final String libraryLocator = "//XCUIElementTypeTable//XCUIElementTypeStaticText[@name=\"%s\"]";

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

    @Override
    public boolean isSettingsScreenOpened() {
        return lblSettings.state().isDisplayed();
    }

    @Override
    public void openLibrary(String libraryName) {
        getElementFactory().getButton(By.xpath(String.format(libraryLocator, libraryName)), "Library " + libraryName).click();
    }

    @Override
    public void openTestMode() {
        TouchAction action = new TouchAction(AqualityServices.getApplication().getDriver());
        action.tap(TapOptions.tapOptions().withTapsCount(NUMBER_OF_CLICKS_FOR_OPENING_TEST_MODE).withElement(ElementOption.element(lblPalaceVersion.getElement()))).
                perform();
        btnTesting.click();
    }

    @Override
    public String getTextFromSettingsHeader() {
        return lblSettings.getText();
    }

    @Override
    public String getTextFromLibrariesBtn() {
        return btnLibraries.getText();
    }

    @Override
    public String getTextFromAboutAppBtn() {
        return btnAboutPalace.getText();
    }

    @Override
    public String getTextFromPrivacyPolicyBtn() {
        return btnPrivacyPolicy.getText();
    }

    @Override
    public String getTextFromUserAgreementBtn() {
        return btnUserAgreement.getText();
    }

    @Override
    public String getTextFromSoftwareLicensesBtn() {
        return btnSoftwareLic.getText();
    }
}
