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
}
