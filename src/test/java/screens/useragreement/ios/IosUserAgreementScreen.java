package screens.useragreement.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.useragreement.UserAgreementScreen;

@ScreenType(platform = PlatformName.IOS)
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
