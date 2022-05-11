package screens.useragreement.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.interfaces.ILabel;
import org.openqa.selenium.By;
import screens.useragreement.UserAgreementScreen;

public class IosUserAgreementScreen extends UserAgreementScreen {

    private final ILabel lblAgreement = getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[contains(@name, \"License Agreement\")]"), "Agreement");

    public IosUserAgreementScreen() {
        super(By.xpath("//XCUIElementTypeNavigationBar[@name=\"User Agreement\"]"));
    }

    @Override
    public boolean isOpened() {
        return lblAgreement.state().isDisplayed();
    }
}
