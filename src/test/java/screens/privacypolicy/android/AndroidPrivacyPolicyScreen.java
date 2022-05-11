package screens.privacypolicy.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.privacypolicy.PrivacyPolicyScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidPrivacyPolicyScreen extends PrivacyPolicyScreen {

    private final ILabel lblPrivacyPolicy = getElementFactory().getLabel(By.xpath("//android.widget.TextView[@text=\"Privacy Policy\"]"), "Privacy Policy");

    public AndroidPrivacyPolicyScreen() {
        super(By.xpath("android.widget.TextView[@text=\"Privacy Policy\"]"));
    }
    @Override
    public boolean isOpened() {
        return lblPrivacyPolicy.state().isDisplayed();
    }
}
