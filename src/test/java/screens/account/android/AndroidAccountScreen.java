package screens.account.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.application.timeouts.BooksTimeouts;
import constants.localization.application.account.AccountScreenLoginStatus;
import framework.configuration.Credentials;
import org.openqa.selenium.By;
import screens.account.AccountScreen;

import java.time.Duration;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidAccountScreen extends AccountScreen {
    private static final String BTN_LOGIN_ID = "authBasicLogin";
    private static final String LOGIN_BTN_LOC_PATTERN = "//*[contains(@resource-id,\"" + BTN_LOGIN_ID + "\") and @text=\"%1$s\"]";

    private final IButton btnLogin = getElementFactory().getButton(
            By.xpath(String.format(LOGIN_BTN_LOC_PATTERN, AccountScreenLoginStatus.LOG_IN.i18n())),
            "Log in");
    private final IButton btnLoginAction = getElementFactory().getButton(By.id(BTN_LOGIN_ID), "Log ... action");
    private final IButton btnLogout = getElementFactory().getButton(
            By.xpath(String.format(LOGIN_BTN_LOC_PATTERN, AccountScreenLoginStatus.LOG_OUT.i18n())),
            "Log out");
    private final IButton btnLogInError = getElementFactory().getButton(By.id("accountLoginButtonErrorDetails"), "Error info");
    private final ITextBox txbCard = getElementFactory().getTextBox(By.id("authBasicUserField"), "Card");
    private final ITextBox txbPin = getElementFactory().getTextBox(By.id("authBasicPassField"), "Pin");
    private final ILabel lblLoading =
            getElementFactory().getLabel(By.id("accountLoginProgressBar"), "Login loading status bar");
    private final IButton btnContentLicenses = getElementFactory().getButton(By.xpath("//android.widget.TextView[@text=\"Content Licenses\"]"), "Content Licenses");
    private final ILabel lblLibrariesAndPalaces =
            getElementFactory().getLabel(By.xpath("//android.widget.TextView[contains(@text, \"Libraries are palaces\")]"), "\tLibraries are palaces for the people");

    public AndroidAccountScreen() {
        super(By.id("auth"));
    }

    @Override
    public void enterCredentialsAndLogin(Credentials credentials) {
        txbCard.clearAndType(credentials.getBarcode());
        txbPin.clearAndTypeSecret(credentials.getPin());
        btnLogin.click();
    }

    @Override
    public boolean isLoginSuccessful() {
        lblLoading.state().waitForDisplayed();
        lblLoading.state().waitForNotDisplayed();
        AqualityServices.getConditionalWait().waitFor(() ->
                btnLogout.state().isDisplayed() || btnLogInError.state().isDisplayed(), Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
        return getLoginButtonText().equals(AccountScreenLoginStatus.LOG_OUT.i18n());
    }

    @Override
    public boolean isLogoutSuccessful() {
        return AqualityServices.getConditionalWait().waitFor(() ->
                getLoginButtonText().equals(AccountScreenLoginStatus.LOG_IN.i18n()));
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
        //only for ios
    }

    @Override
    public boolean isLogoutRequired() {
        return btnLogout.state().isDisplayed();
    }

    @Override
    public void openLicenseAgreement() {
        //only for iOS
    }

    @Override
    public boolean isLinkOpened() {
        //only for iOS
        return true;
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
        //only for iOS
    }

    @Override
    public boolean isButtonDisplayed(String buttonName) {
        //only for iOS
        return true;
    }

    @Override
    public void clickDelete(String button) {
        //for iOS
    }

    private String getLoginButtonText() {
        return btnLoginAction.state().waitForDisplayed() ? btnLoginAction.getText() : "";
    }
}
