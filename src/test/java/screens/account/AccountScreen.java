package screens.account;

import aquality.appium.mobile.screens.Screen;
import framework.configuration.Credentials;
import org.openqa.selenium.By;

public abstract class AccountScreen extends Screen {
    public AccountScreen(By locator) {
        super(locator, "Account");
    }

    public abstract void enterCredentialsAndLogin(Credentials credentials);
    public abstract void enterCredentialsAndLoginES(Credentials credentials);
    public abstract void enterCredentialsAndLoginIT(Credentials credentials);

    public abstract boolean isLoginSuccessful();
    public abstract boolean isLoginSuccessfulES();
    public abstract boolean isLoginSuccessfulIT();

    public abstract boolean isLogoutSuccessful();

    public abstract void tapLogOut();
    public abstract void tapLogOutES();
    public abstract void tapLogOutIT();

    public abstract String getTextFromPinTxb();

    public abstract String getTextFromCardTxb();

    public abstract String getTextFromLogInButton();
    public abstract String getTextFromLogInButtonES();
    public abstract String getTextFromLogInButtonIT();

    public abstract void tapApproveSignOut();
    public abstract void tapApproveSignOutES();
    public abstract void tapApproveSignOutIT();

    public abstract boolean isLogoutRequired();
    public abstract boolean isLogoutRequiredES();
    public abstract boolean isLogoutRequiredIT();

    public abstract void openLicenseAgreement();

    public abstract boolean isLinkOpened();

    public abstract void openContentLicenses();

    public abstract boolean isContentLicOpened();

    public abstract void openAdvanced();

    public abstract boolean isButtonDisplayed(String buttonName);

    public abstract void clickDelete(String button);

    public abstract void closeAccountScreen();

    public abstract String getTextFromLibrariesBtn();

    public abstract String getTextFromAccountHeader();

    public abstract String getTextFromForgetPasswordLbl();

    public abstract String getTextFromLicenseAgreementLink();

    public abstract String getTextFromNoAccountLbl();

    public abstract String getTextFromCreateCardBtn();

    public abstract String getTextFromReportAboutProblemBtn();

    public abstract String getTextFromContentLicensesLbl();

    public abstract boolean isLogOutErrorDisplayed();
}
