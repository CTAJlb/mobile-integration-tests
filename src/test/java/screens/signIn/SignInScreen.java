package screens.signIn;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class SignInScreen extends Screen {
    protected SignInScreen(By locator) {
        super(locator, "Sign in screen");
    }

    public abstract boolean isLibraryCardDisplayed();

    public abstract boolean isPasswordDisplayed();

    public abstract boolean isSignInBtnDisplayed();

    public abstract boolean isLinkToTheLicenseAgreementDisplayed();

    public abstract String getTextFromLibraryCard();

    public abstract String getTextFromPassword();

    public abstract void enterPassword(String password);

    public abstract void enterLibraryCard(String libCard);

    public abstract void tapSignInBtn();

    public abstract void deleteSomeDataInLibCard();

    public abstract void setTextInLibCard(String text);
}
