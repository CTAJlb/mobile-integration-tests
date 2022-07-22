package screens.privacypolicy.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.privacypolicy.PrivacyPolicyScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosPrivacyPolicyScreen extends PrivacyPolicyScreen {

    private final ILabel lblPrivacyPolicy = getElementFactory().getLabel(By.xpath("//XCUIElementTypeOther[@name=\"Privacy Policy\"]"), "Privacy Policy");

    public IosPrivacyPolicyScreen() {
        super(By.xpath("//XCUIElementTypeNavigationBar[@name=\"Privacy Policy\"]"));
    }


    @Override
    public boolean isOpened() {
        return lblPrivacyPolicy.state().waitForDisplayed();
    }
}
