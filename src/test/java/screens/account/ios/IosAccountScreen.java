package screens.account.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.localization.spanish.SpanishIos;
import enums.localization.account.AccountScreenLoginStatus;
import framework.configuration.Credentials;
import org.openqa.selenium.By;
import screens.account.AccountScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosAccountScreen extends AccountScreen {
    private static final String LOGIN_BTN_LOC_PATTERN = "//XCUIElementTypeStaticText[@name=\"%1$s\"]\n";
    private static final String BUTTON_LOCATOR = "//XCUIElementTypeStaticText[@name=\"%s\"]";

    private final IButton btnLogin = getElementFactory().getButton(By.xpath(String.format(LOGIN_BTN_LOC_PATTERN, AccountScreenLoginStatus.SIGN_IN.getDefaultLocalizedValue())),"Log in");
    private final IButton btnSignInES = getElementFactory().getButton(By.xpath(String.format(LOGIN_BTN_LOC_PATTERN, SpanishIos.SIGN_IN)), "Sign in button in Spanish");
    private final IButton btnLogout = getElementFactory().getButton(By.xpath(String.format(LOGIN_BTN_LOC_PATTERN, AccountScreenLoginStatus.SIGN_OUT.getDefaultLocalizedValue())),"Log out");
    private final IButton btnSignOutES = getElementFactory().getButton(By.xpath(String.format(LOGIN_BTN_LOC_PATTERN, SpanishIos.SIGN_OUT)), "Sign out in Spanish");
    private final IButton btnApproveSignOut = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Sign out\"]"),"Log out approve");
    private final IButton btnApproveSignOutES = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Cerrar sesión\"]"),"Log out approve in Spanish");
    private final ITextBox txbCard = getElementFactory().getTextBox(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeTextField"), "Card");
    private final ITextBox txbPin = getElementFactory().getTextBox(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeSecureTextField"), "Pin");
    private final IButton btnLicAgreement = getElementFactory().getButton(By.xpath("//XCUIElementTypeTable/XCUIElementTypeOther[2]/XCUIElementTypeButton"), "User License Agreement");
    private final ILabel lblCodeConduct = getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@name=\"Code of Conduct\"]"), "Code of Conduct");
    private final IButton btnContentLicenses = getElementFactory().getButton(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[7]/XCUIElementTypeStaticText"), "Content Licenses");
    private final ILabel lblLibrariesAndPalaces =getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[contains(@name, \"Libraries are palaces\")]"), "\tLibraries are palaces for the people");
    private final IButton btnAdvanced = getElementFactory().getButton(By.xpath("//XCUIElementTypeStaticText[@name=\"Advanced\"]"), "Advanced button");
    private final IButton btnCancel = getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar//XCUIElementTypeButton[@name=\"Cancel\"]"), "Cancel button");
    private final IButton btnLibraries = getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton"), "Libraries button");
    private final ILabel lblAccountHeader = getElementFactory().getLabel(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText"), "Account header");
    private final ILabel lblForgetPassword = getElementFactory().getLabel(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[4]/XCUIElementTypeStaticText"), "Forget password label");
    private final ILabel lblNoAccount = getElementFactory().getLabel(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[5]/XCUIElementTypeStaticText[2]"), "Don't have an account label");
    private final IButton btnCreateCard = getElementFactory().getButton(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[5]/XCUIElementTypeStaticText[1]"), "Create card button");
    private final IButton btnReportAProblem = getElementFactory().getButton((By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[5]/XCUIElementTypeStaticText")), "Report a problem");

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
    public void enterCredentialsAndLoginES(Credentials credentials) {
        AqualityServices.getConditionalWait().waitFor(() -> btnSignInES.state().isDisplayed() || btnSignOutES.state().isDisplayed());
        if(!btnSignOutES.state().isDisplayed()) {
            txbCard.click();
            txbCard.clearAndType(credentials.getBarcode());
            txbPin.clearAndType(credentials.getPin());
            btnSignInES.click();
        }
    }

    @Override
    public boolean isLoginSuccessful() {
        return btnLogout.state().isDisplayed();
    }

    @Override
    public boolean isLoginSuccessfulES() {
        return btnSignOutES.state().isDisplayed();
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
    public void tapLogOutES() {
        btnSignOutES.click();
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
    public String getTextFromLogInButtonES() {
        return btnSignInES.getText();
    }

    @Override
    public void tapApproveSignOut() {
        btnApproveSignOut.click();
    }

    @Override
    public void tapApproveSignOutES() {
        btnApproveSignOutES.click();
    }

    @Override
    public boolean isLogoutRequired() {
        return btnLogout.state().isDisplayed();
    }

    @Override
    public boolean isLogoutRequiredES() {
        return btnSignOutES.state().isDisplayed();
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

    @Override
    public void closeAccountScreen() {
        btnCancel.click();
    }

    @Override
    public String getTextFromLibrariesBtn() {
        return btnLibraries.getText();
    }

    @Override
    public String getTextFromAccountHeader() {
        return lblAccountHeader.getText();
    }

    @Override
    public String getTextFromForgetPasswordLbl() {
        return lblForgetPassword.getText();
    }

    @Override
    public String getTextFromLicenseAgreementLink() {
        return btnLicAgreement.getText();
    }

    @Override
    public String getTextFromNoAccountLbl() {
        return lblNoAccount.getText();
    }

    @Override
    public String getTextFromCreateCardBtn() {
        return btnCreateCard.getText();
    }

    @Override
    public String getTextFromReportAboutProblemBtn() {
        return btnReportAProblem.getText();
    }

    @Override
    public String getTextFromContentLicensesLbl() {
        return btnContentLicenses.getText();
    }

    @Override
    public boolean isLogOutErrorDisplayed() {
        //only for android
        return false;
    }
}
