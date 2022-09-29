package screens.account.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import enums.localization.account.AccountScreenLoginStatus;
import framework.configuration.Credentials;
import org.openqa.selenium.By;
import screens.account.AccountScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosAccountScreen extends AccountScreen {
    private static final String LOGIN_BTN_LOC_PATTERN = "//XCUIElementTypeStaticText[@name=\"%1$s\"]\n";
    private static final String BUTTON_LOCATOR = "//XCUIElementTypeStaticText[@name=\"%s\"]";

    private final IButton btnLogin = getElementFactory().getButton(
            By.xpath(String.format(LOGIN_BTN_LOC_PATTERN, AccountScreenLoginStatus.LOG_IN.i18n())),
            "Log in");
    private final IButton btnLogout = getElementFactory().getButton(
            By.xpath(String.format(LOGIN_BTN_LOC_PATTERN, AccountScreenLoginStatus.LOG_OUT.i18n())),
            "Log out");
    private final IButton btnApproveSignOut = getElementFactory().getButton(
            By.xpath("//XCUIElementTypeButton[@name=\"Sign out\"]"),
            "Log out approve");
    private final ITextBox txbCard = getElementFactory().getTextBox(By.xpath("//XCUIElementTypeTextField[@value]"), "Card");
    private final ITextBox txbPin = getElementFactory().getTextBox(By.xpath("//XCUIElementTypeSecureTextField[@value]"), "Pin");
    private final IButton btnLicAgreement = getElementFactory().getButton(
            By.xpath("//XCUIElementTypeButton[contains(@name, \"User License Agreement\")]"), "User License Agreement");
    private final ILabel lblCodeConduct = getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@name=\"Code of Conduct\"]"), "Code of Conduct");
    private final IButton btnContentLicenses = getElementFactory().getButton(By.xpath("//XCUIElementTypeStaticText[@name=\"ContentLicenses\"]"), "Content Licenses");
    private final ILabel lblLibrariesAndPalaces =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[contains(@name, \"Libraries are palaces\")]"), "\tLibraries are palaces for the people");
    private final IButton btnAdvanced = getElementFactory().getButton(By.xpath("//XCUIElementTypeStaticText[@name=\"Advanced\"]"), "Advanced button");

    public IosAccountScreen() {
        super(By.xpath("//XCUIElementTypeNavigationBar[@name=\"Account\"]"));
    }

    @Override
    public void enterCredentialsAndLogin(Credentials credentials) {
        AqualityServices.getConditionalWait().waitFor(() -> btnLogin.state().isDisplayed() || btnLogout.state().isDisplayed());
        if (!btnLogout.state().isDisplayed()) {
            txbCard.click();
            txbCard.clearAndType(credentials.getBarcode());
            txbPin.clearAndTypeSecret(credentials.getPin());
            btnLogin.click();
        }
    }

    @Override
    public boolean isLoginSuccessful() {
        return btnLogout.state().isDisplayed();
    }

    @Override
    public boolean isLogoutSuccessful() {
        return AqualityServices.getConditionalWait().waitFor(() -> btnLogin.state().isDisplayed());
    }

    @Override
    public void tapLogOut() {
        btnLogout.click();
    }

    @Override
    public String getTextFromPinTxb() {
        return txbPin.getText();
    }

    @Override
    public String getTextFromCardTxb() {
        return txbCard.getText();
    }

    @Override
    public String getTextFromLogInButton() {
        return btnLogin.getText();
    }

    @Override
    public void tapApproveSignOut() {
        btnApproveSignOut.click();
    }

    @Override
    public boolean isLogoutRequired() {
        return btnLogout.state().isDisplayed();
    }

    @Override
    public void openLicenseAgreement() {
        btnLicAgreement.click();
    }

    @Override
    public boolean isLinkOpened() {
        return lblCodeConduct.state().waitForDisplayed();
    }

    @Override
    public void openContentLicenses() {
        btnContentLicenses.click();
    }

    @Override
    public boolean isContentLicOpened() {
        return lblLibrariesAndPalaces.state().waitForDisplayed();
    }

    @Override
    public void openAdvanced() {
        btnAdvanced.click();
    }

    @Override
    public boolean isButtonDisplayed(String buttonName) {
        return getElementFactory().getButton(By.xpath(String.format(BUTTON_LOCATOR, buttonName)), "Button " + buttonName).state().isDisplayed();
    }

    @Override
    public void clickDelete(String button) {
        getElementFactory().getButton(By.xpath(String.format(BUTTON_LOCATOR, button)), "Button " + button).click();
    }
}
