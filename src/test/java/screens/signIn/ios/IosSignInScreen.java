package screens.signIn.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILink;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.signIn.SignInScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosSignInScreen extends SignInScreen {
    private final ITextBox txbLibraryCard = getElementFactory().getTextBox(By.xpath("//XCUIElementTypeTextField[@value=\"Library Card\"]"), "Library card");
    private final ITextBox txbPassword = getElementFactory().getTextBox(By.xpath("//XCUIElementTypeSecureTextField[@value=\"Password\"]"), "Password");
    private final IButton btnSignIn = getElementFactory().getButton(By.xpath("//XCUIElementTypeStaticText[@name=\"Sign in\"]"), "Sign in button");
    private final ILink linkLicAgreement = getElementFactory().getLink(By.xpath("//XCUIElementTypeButton[contains(@name, \"User License Agreement\")]"), "License Agreement link");

    public IosSignInScreen() {
        super(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[@name=\"Sign in\"]"));
    }

    @Override
    public boolean isLibraryCardDisplayed() {
        return txbLibraryCard.state().waitForDisplayed();
    }

    @Override
    public boolean isPasswordDisplayed() {
        return txbPassword.state().waitForDisplayed();
    }

    @Override
    public boolean isSignInBtnDisplayed() {
        return btnSignIn.state().waitForDisplayed();
    }

    @Override
    public boolean isLinkToTheLicenseAgreementDisplayed() {
        return linkLicAgreement.state().waitForDisplayed();
    }
}
