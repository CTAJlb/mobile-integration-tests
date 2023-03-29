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
    private final ITextBox txbLibraryCard = getElementFactory().getTextBox(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeTextField"), "Library card");
    private final ITextBox txbPassword = getElementFactory().getTextBox(By.xpath("//XCUIElementTypeSecureTextField[@value=\"Password\"]"), "Password");
    private final IButton btnSignIn = getElementFactory().getButton(By.xpath("//XCUIElementTypeCell/XCUIElementTypeStaticText[@name=\"Sign in\"]"), "Sign in button");
    private final ILink linkLicAgreement = getElementFactory().getLink(By.xpath("//XCUIElementTypeButton[contains(@name, \"User License Agreement\")]"), "License Agreement link");
    private final IButton btnDelete = getElementFactory().getButton(By.xpath("//XCUIElementTypeKey[@name=\"delete\"]"), "Delete button");

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

    @Override
    public String getTextFromLibraryCard() {
        return txbLibraryCard.getText();
    }

    @Override
    public String getTextFromPassword() {
        return txbPassword.getText();
    }

    @Override
    public void enterPassword(String password) {
        txbPassword.clearAndType(password);
    }

    @Override
    public void enterLibraryCard(String libCard) {
        txbLibraryCard.clearAndType(libCard);
    }

    @Override
    public void tapSignInBtn() {
        btnSignIn.click();
    }

    @Override
    public void deleteSomeDataInLibCard() {
        btnDelete.click();
    }

    @Override
    public void setTextInLibCard(String text) {
        txbLibraryCard.sendKeys(text);
    }

    @Override
    public void tapSignIn() {
        btnSignIn.click();
    }

    @Override
    public String getErrorMessage() {
        //for android
        return null;
    }


}
