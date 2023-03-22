package screens.signIn.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ICheckBox;
import aquality.appium.mobile.elements.interfaces.ILink;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import framework.utilities.keyboard.KeyboardUtils;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.openqa.selenium.By;
import screens.signIn.SignInScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSignInScreen extends SignInScreen {
    private final ITextBox txbLibraryCard = getElementFactory().getTextBox(By.xpath("//android.widget.LinearLayout[contains(@resource-id, \"authBasicUser\")]//android.widget.EditText"), "Library card");
    private final ITextBox txbPassword = getElementFactory().getTextBox(By.xpath("//android.widget.EditText[@text=\"Password\"]"), "Password");
    private final ICheckBox cbxShowPassword = getElementFactory().getCheckBox(By.xpath("//android.widget.CheckBox[@text=\"Show Password\"]"), "Show password checkbox");
    private final IButton btnSignIn = getElementFactory().getButton(By.xpath("//android.widget.Button[@text=\"Sign in\"]"), "Sign in button");
    private final ILink linkLicAgreement = getElementFactory().getLink(By.xpath("//android.widget.TextView[contains(@text, \"User License Agreement\")]"), "License Agreement link");


    public AndroidSignInScreen() {
        super(By.xpath("//android.widget.TextView[contains(@text, \"sign in\")]"));
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
        KeyboardUtils.pressKey(AndroidKey.DEL);
    }

    @Override
    public void setTextInLibCard(String text) {
        txbLibraryCard.sendKeys(text);
    }


}
