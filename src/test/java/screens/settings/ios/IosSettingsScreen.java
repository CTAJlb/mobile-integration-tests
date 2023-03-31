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
    private final IButton btnLibraries = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Libraries\"]"), "Libraries button");
    private final IButton btnLibrariesES = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Bibliotecas\"]"), "Libraries button in Spanish");
    private final IButton btnLibrariesIT = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Biblioteche\"]"), "Libraries button in Italian");
    private final IButton btnAboutPalace = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"About Palace\"]"), "About Palace");
    private final IButton btnAboutPalaceES = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Acerca de la aplicación\"]"), "About Palace in Spanish");
    private final IButton btnAboutPalaceIT = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Informazioni sull’app\"]"), "About Palace in Italian");
    private final IButton btnPrivacyPolicy = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Privacy Policy\"]"), "Privacy Policy");
    private final IButton btnPrivacyPolicyES = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Política de privacidad\"]"), "Privacy Policy in Spanish");
    private final IButton btnPrivacyPolicyIT = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Politica sulla privacy\"]"), "Privacy Policy in Italian");
    private final IButton btnUserAgreement = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"User Agreement\"]"), "User Agreement");
    private final IButton btnUserAgreementES = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Contrato de usuario\"]"), "User Agreement in Spanish");
    private final IButton btnUserAgreementIT = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Accordo per gli utenti\"]"), "User Agreement in Italian");
    private final IButton btnSoftwareLic = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Software Licenses\"]"), "Software Licenses");
    private final IButton btnSoftwareLicES = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Licencias de software\"]"), "Software Licenses in Spanish");
    private final IButton btnSoftwareLicIT = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Licenze software\"]"), "Software Licenses in Italian");
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
    public void openLibrariesES() {
        btnLibrariesES.click();
    }

    @Override
    public void openLibrariesIT() {
        btnLibrariesIT.click();
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
    public String getTextFromLibrariesBtnES() {
        return btnLibrariesES.getText();
    }

    @Override
    public String getTextFromLibrariesBtnIT() {
        return btnLibrariesIT.getText();
    }

    @Override
    public String getTextFromAboutAppBtnES() {
        return btnAboutPalaceES.getText();
    }

    @Override
    public String getTextFromAboutAppBtnIT() {
        return btnAboutPalaceIT.getText();
    }

    @Override
    public String getTextFromPrivacyPolicyBtnES() {
        return btnPrivacyPolicyES.getText();
    }

    @Override
    public String getTextFromPrivacyPolicyBtnIT() {
        return btnPrivacyPolicyIT.getText();
    }

    @Override
    public String getTextFromUserAgreementBtnES() {
        return btnUserAgreementES.getText();
    }

    @Override
    public String getTextFromUserAgreementBtnIT() {
        return btnUserAgreementIT.getText();
    }

    @Override
    public String getTextFromSoftwareLicensesBtnES() {
        return btnSoftwareLicES.getText();
    }

    @Override
    public String getTextFromSoftwareLicensesBtnIT() {
        return btnSoftwareLicIT.getText();
    }
}
